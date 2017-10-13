package com.demo.lastminute.controllers;

import com.demo.lastminute.dto.ReturnObject;
import com.demo.lastminute.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeRestController {

    @Autowired
    ReceiptService receiptService;

    @RequestMapping("/rest")
    public List<ReturnObject> restResult() {
        List<ReturnObject> output = receiptService.createOutput();

        return output;
    }
}