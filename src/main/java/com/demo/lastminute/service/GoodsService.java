package com.demo.lastminute.service;

import com.demo.lastminute.domain.Goods;
import com.demo.lastminute.domain.GoodsOutput;
import com.demo.lastminute.domain.ReturnObject;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface GoodsService {

    ReturnObject populateReturnObject(Map.Entry<Long, List<Goods>> entry, List<GoodsOutput> goodsOutputs, BigDecimal totalTaxes, BigDecimal totalPrice);

    GoodsOutput populateGoodsOutput(Goods good, BigDecimal goodTaxValue, BigDecimal roundedTax);

    Map<Long, List<Goods>> populateWithInputs();

    BigDecimal totalTaxOnSingleGood(BigDecimal price, int taxes);

    int getTaxes(Goods good);
}
