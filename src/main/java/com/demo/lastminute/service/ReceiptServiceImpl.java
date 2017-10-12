package com.demo.lastminute.service;

import com.demo.lastminute.domain.Goods;
import com.demo.lastminute.domain.GoodsOutput;
import com.demo.lastminute.domain.ReturnObject;
import com.demo.lastminute.utils.PdfGeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    @Value("${pdf.template.filename}")
    private String pdfTemplateFilename;

    @Value("${pdf.result.filename}")
    private String resultPdfFilename;


    private List<ReturnObject> createOutput(){

        Map<Long, List<Goods>> goodsMap = goodsService.populateWithInputs();

        List<ReturnObject> returnObjects = new ArrayList<ReturnObject>();
        for (Map.Entry<Long, List<Goods>> entry : goodsMap.entrySet())
        {
            List<Goods> goods = goodsMap.get(entry.getKey());

            List<GoodsOutput> goodsOutputs = new ArrayList<GoodsOutput>();
            BigDecimal totalTaxes = BigDecimal.ZERO;
            BigDecimal totalPrice = BigDecimal.ZERO;
            for (Goods good : goods) {
                int taxes = goodsService.getTaxes(good);

                BigDecimal goodTaxValue = goodsService.totalTaxOnSingleGood(good.getPrice(), taxes);
                BigDecimal roundedTax = precisionService.roundValue(goodTaxValue);

                BigDecimal singleGoodOutputPrice = good.getPrice().add(roundedTax);
                GoodsOutput go = goodsService.populateGoodsOutput(good, goodTaxValue, roundedTax);
                goodsOutputs.add(go);

                totalTaxes = totalTaxes.add(roundedTax);
                totalPrice = totalPrice.add(singleGoodOutputPrice);
            }

            ReturnObject returnObject = goodsService.populateReturnObject(entry, goodsOutputs, totalTaxes, totalPrice);

            returnObjects.add(returnObject);
        }

        return returnObjects;
    }


    @Override
    public ResponseEntity<byte[]> downloadPdf() throws Exception{
        Path pdfPreview = Paths.get( System.getProperty("java.io.tmpdir") + "/" + pdfTemplateFilename + ".pdf" );
        byte[] data = Files.readAllBytes(pdfPreview);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        String filename = resultPdfFilename + ".pdf";
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]> responseByte = new ResponseEntity<byte[]>(data, headers, HttpStatus.OK);

        return responseByte;
    }

    @Override
    public void generatePdf() throws Exception {

        List<ReturnObject> output = createOutput();

        Map<String,List<ReturnObject> > data = new HashMap<String, List<ReturnObject> >();
        data.put("data",output);
        pdfGenaratorUtil.createPdf(pdfTemplateFilename, data);

    }
}
