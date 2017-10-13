package com.demo.lastminute.service;

import com.demo.lastminute.dto.ReturnObject;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReceiptService {

    List<ReturnObject> createOutput();

    ResponseEntity<byte[]> downloadPdf() throws Exception;

    void generatePdf() throws Exception;
}
