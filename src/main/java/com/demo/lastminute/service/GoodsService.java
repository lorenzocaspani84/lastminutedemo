package com.demo.lastminute.service;

import com.demo.lastminute.domain.GoodsOutput;
import com.demo.lastminute.domain.ReturnObject;
import org.springframework.stereotype.Service;

import java.util.List;

public interface GoodsService {
    List<ReturnObject> getOutput();
}
