package com.demo.lastminute.controllers;

import com.demo.lastminute.dto.ReturnObject;
import com.demo.lastminute.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeRestController {

    @Autowired
    ReceiptService receiptService;

    @RequestMapping(value = "/rest", method = RequestMethod.GET)
    public ResponseEntity<List<ReturnObject>> restResult() {
        List<ReturnObject> output = receiptService.createOutput();

        return new ResponseEntity<List<ReturnObject>>(output, HttpStatus.OK);
    }

}