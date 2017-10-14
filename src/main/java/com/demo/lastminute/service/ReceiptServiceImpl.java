package com.demo.lastminute.service;

import com.demo.lastminute.domain.Basket;
import com.demo.lastminute.domain.Goods;
import com.demo.lastminute.dto.GoodsOutput;
import com.demo.lastminute.dto.ReturnObject;
import com.demo.lastminute.exception.CustomException;
import com.demo.lastminute.repository.BasketRepository;
import com.demo.lastminute.utils.PdfGeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("receiptService")
public class ReceiptServiceImpl implements ReceiptService{

    @Autowired
    PrecisionService precisionService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    PdfGeneratorUtil pdfGenaratorUtil;

    @Autowired
    BasketRepository basketRepository;

    @Value("${pdf.template.filename}")
    private String pdfTemplateFilename;

    @Value("${pdf.result.filename}")
    private String resultPdfFilename;


    @Override
    public List<ReturnObject> createOutput(){

        List<ReturnObject> returnObjects = new ArrayList<ReturnObject>();
        List<Basket> categories = basketRepository.findAllByOrderByIdAsc();
        for(Basket basket : categories){

            List<GoodsOutput> goodsOutputs = new ArrayList<GoodsOutput>();
            BigDecimal totalTaxes = BigDecimal.ZERO;
            BigDecimal totalPrice = BigDecimal.ZERO;
            for (Goods good : basket.getGoods()) {
                int taxes = goodsService.getTaxes(good);

                BigDecimal goodTaxValue = goodsService.totalTaxOnSingleGood(good.getPrice(), taxes);
                BigDecimal roundedTax = precisionService.roundValue(goodTaxValue);

                BigDecimal singleGoodOutputPrice = good.getPrice().add(roundedTax);
                GoodsOutput go = goodsService.populateGoodsOutput(good, goodTaxValue, roundedTax);
                goodsOutputs.add(go);

                totalTaxes = totalTaxes.add(roundedTax);
                totalPrice = totalPrice.add(singleGoodOutputPrice);
            }

            ReturnObject returnObject = goodsService.populateReturnObject(goodsOutputs, totalTaxes, totalPrice);

            returnObjects.add(returnObject);
        }

        return returnObjects;
    }


    @Override
    public ResponseEntity<byte[]> downloadPdf() throws CustomException {
        Path pdfPreview = Paths.get( System.getProperty("java.io.tmpdir") + "/" + pdfTemplateFilename + ".pdf" );
        try{
            byte[] data = Files.readAllBytes(pdfPreview);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/pdf"));
            String filename = resultPdfFilename + ".pdf";
            headers.setContentDispositionFormData(filename, filename);
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
            ResponseEntity<byte[]> responseByte = new ResponseEntity<byte[]>(data, headers, HttpStatus.OK);

            return responseByte;
        }catch (IOException ex){
            throw new CustomException("Error during pdf download", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void generatePdf() throws CustomException {

        List<ReturnObject> output = createOutput();

        Map<String,List<ReturnObject> > data = new HashMap<String, List<ReturnObject> >();
        data.put("data",output);
        try {
            pdfGenaratorUtil.createPdf(pdfTemplateFilename, data);
        }catch (Exception ex){
            throw new CustomException("Error during pdf creation", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
