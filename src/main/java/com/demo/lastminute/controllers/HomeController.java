package com.demo.lastminute.controllers;

import com.demo.lastminute.domain.Category;
import com.demo.lastminute.domain.GoodsOutput;
import com.demo.lastminute.domain.ReturnObject;
import com.demo.lastminute.repository.CategoryRepository;
import com.demo.lastminute.service.GoodsService;
import com.demo.lastminute.utils.PdfGeneratorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("homeController")
public class HomeController {

    private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    GoodsService goodsService;

    @Autowired
    PdfGeneratorUtil pdfGenaratorUtil;

    @Value("${pdf.template.filename}")
    private String pdfTemplateFilename;

    @Value("${pdf.result.filename}")
    private String resultPdfFilename;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String showHome() throws Exception {

        LOG.info("START showHome");

        List<ReturnObject> output = goodsService.getOutput();

        Map<String,List<ReturnObject> > data = new HashMap<String, List<ReturnObject> >();
        data.put("data",output);
        pdfGenaratorUtil.createPdf(pdfTemplateFilename, data);

        LOG.info("FINISH showHome");
        return "download";
    }

    @RequestMapping(value = "/download")
    public @ResponseBody
    ResponseEntity<byte[]> download() throws Exception {

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

}
