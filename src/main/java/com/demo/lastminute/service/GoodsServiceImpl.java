package com.demo.lastminute.service;

import com.demo.lastminute.domain.Goods;
import com.demo.lastminute.dto.GoodsOutput;
import com.demo.lastminute.dto.ReturnObject;
import com.demo.lastminute.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    PrecisionService precisionService;

    @Value("${tax.goods:10}")
    private Integer goodsTax;

    @Value("${tax.import:5}")
    private Integer importTax;


    @Override
    public ReturnObject populateReturnObject(List<GoodsOutput> goodsOutputs, BigDecimal totalTaxes, BigDecimal totalPrice) {
        ReturnObject returnObject = new ReturnObject();
        returnObject.setGoodsOutput(goodsOutputs);
        returnObject.setTotalPrice(totalPrice.setScale(2, BigDecimal.ROUND_HALF_UP));
        returnObject.setTotalTax(totalTaxes.setScale(2, BigDecimal.ROUND_HALF_UP));

        return returnObject;
    }

    @Override
    public GoodsOutput populateGoodsOutput(Goods good, BigDecimal goodTaxValue, BigDecimal roundedTax){
        GoodsOutput go = new GoodsOutput();
        go.setName(good.getName());
        go.setQuantity(good.getQuantity());
        go.setTax(goodTaxValue);
        go.setRoundedTax(roundedTax);
        go.setPrice(good.getPrice().add(go.getRoundedTax()));
        
        return go;
    }


    @Override
    public BigDecimal totalTaxOnSingleGood(BigDecimal price, int taxes){
        BigDecimal tax = BigDecimal.ZERO;
        if(taxes > 0){
            //Double doubleValue = (price.doubleValue() * (0.01 * taxes));
            Double doubleValue = (price.doubleValue() *taxes) / 100;
            tax = BigDecimal.valueOf(doubleValue);
            tax = precisionService.roundValue(tax);
        }

        return tax;
    }

    @Override
    public int getTaxes(Goods good){
        int taxPercentual = 0;
        if(!good.isExempt()){
            taxPercentual += goodsTax;
        }
        if(good.isDuty()){
            taxPercentual += importTax;
        }

        return taxPercentual;
    }
}
