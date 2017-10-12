package com.demo.lastminute.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service("precisionService")
public class PrecisionServiceImpl implements PrecisionService {

    @Override
    public BigDecimal roundValue(BigDecimal value) {
        BigDecimal result = BigDecimal.valueOf((Math.ceil(value.doubleValue() * 20) / 20));
        result.setScale(2, RoundingMode.HALF_UP);

        return result;
    }
}
