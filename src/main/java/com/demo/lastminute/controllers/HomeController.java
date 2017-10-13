package com.demo.lastminute.controllers;

import com.demo.lastminute.repository.CategoryRepository;
import com.demo.lastminute.service.ReceiptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller("homeController")
public class HomeController {

    private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    ReceiptService receiptService;

    @Autowired
    CategoryRepository categoryRepository;
    
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView home() throws Exception {

        receiptService.generatePdf();

        ModelAndView mav = new ModelAndView("home");
        mav.addObject("records", categoryRepository.findAllByOrderByIdAsc());

        return mav;
    }

    @RequestMapping(value = "/download")
    public @ResponseBody
    ResponseEntity<byte[]> download() throws Exception {

        ResponseEntity<byte[]> responseByte = receiptService.downloadPdf();

        return responseByte;
    }

}
