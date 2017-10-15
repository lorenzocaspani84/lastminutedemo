package com.demo.lastminute.service;

import com.demo.lastminute.domain.Goods;
import com.demo.lastminute.dto.GoodsOutput;
import com.demo.lastminute.dto.ReturnObject;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface GoodsService {

    ReturnObject populateReturnObject(List<GoodsOutput> goodsOutputs, BigDecimal totalTaxes, BigDecimal totalPrice);

    GoodsOutput populateGoodsOutput(Goods good, BigDecimal goodTaxValue, BigDecimal roundedTax);

    BigDecimal totalTaxOnSingleGood(BigDecimal price, int taxes);

    int getTaxes(Goods good);

    Goods findGoodsByName(String name);
}
