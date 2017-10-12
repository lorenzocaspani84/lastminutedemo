package com.demo.lastminute.controllers;

import com.demo.lastminute.domain.Category;
import com.demo.lastminute.domain.GoodsOutput;
import com.demo.lastminute.domain.ReturnObject;
import com.demo.lastminute.repository.CategoryRepository;
import com.demo.lastminute.service.GoodsService;
import com.demo.lastminute.service.ReceiptService;
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
    ReceiptService receiptService;
    
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String home() throws Exception {

        LOG.info("START home");

        receiptService.generatePdf();

        LOG.info("FINISH home");
        return "home";
    }

    @RequestMapping(value = "/download")
    public @ResponseBody
    ResponseEntity<byte[]> download() throws Exception {
        LOG.info("START download");

        ResponseEntity<byte[]> responseByte = receiptService.downloadPdf();

        LOG.info("FINISH download");
        return responseByte;
    }

}
