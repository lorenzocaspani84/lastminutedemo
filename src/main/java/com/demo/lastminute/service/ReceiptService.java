package com.demo.lastminute.service;

import com.demo.lastminute.domain.ReturnObject;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReceiptService {

    ResponseEntity<byte[]> downloadPdf() throws Exception;

    void generatePdf() throws Exception;
}
