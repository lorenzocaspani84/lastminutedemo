package com.demo.lastminute.service;

import com.demo.lastminute.domain.Category;
import com.demo.lastminute.domain.Goods;
import com.demo.lastminute.domain.GoodsOutput;
import com.demo.lastminute.domain.ReturnObject;
import com.demo.lastminute.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    CategoryRepository categoryRepository;

    @Value("${tax.goods}")
    private Integer goodsTax;

    @Value("${tax.import}")
    private Integer importTax;


    @Override
    public List<ReturnObject> getOutput(){

        Map<String, List<Goods>> goodsMap = populate();

        List<ReturnObject> returnObjects = new ArrayList<ReturnObject>();
        for (Map.Entry<String, List<Goods>> entry : goodsMap.entrySet())
        {
            List<Goods> goods = goodsMap.get(entry.getKey());

            List<GoodsOutput> goodsOutputs = new ArrayList<GoodsOutput>();
            BigDecimal totalTaxes = BigDecimal.ZERO;
            BigDecimal totalPrice = BigDecimal.ZERO;
            for (Goods good : goods) {
                int taxes = getTaxes(good);

                BigDecimal goodTaxValue = totalTaxOnSingleGood(good.getPrice(), taxes);
                BigDecimal roundedTax = roundValue(goodTaxValue);

                BigDecimal singleGoodOutputPrice = good.getPrice().add(roundedTax);
                GoodsOutput go = goodsOutputPopulate(good, goodTaxValue, roundedTax);
                goodsOutputs.add(go);

                totalTaxes = totalTaxes.add(roundedTax);
                totalPrice = totalPrice.add(singleGoodOutputPrice);
            }

            ReturnObject returnObject = getReturnObject(entry, goodsOutputs, totalTaxes, totalPrice);

            returnObjects.add(returnObject);
        }

        return returnObjects;
    }

    private ReturnObject getReturnObject(Map.Entry<String, List<Goods>> entry, List<GoodsOutput> goodsOutputs, BigDecimal totalTaxes, BigDecimal totalPrice) {
        ReturnObject returnObject = new ReturnObject();
        returnObject.setGoodsOutput(goodsOutputs);
        returnObject.setTotalPrice(totalPrice);
        returnObject.setTotalTax(totalTaxes);
        returnObject.setCategoryName(entry.getKey());
        return returnObject;
    }

    private GoodsOutput goodsOutputPopulate(Goods good, BigDecimal goodTaxValue, BigDecimal roundedTax){
        GoodsOutput go = new GoodsOutput();
        go.setName(good.getName());
        go.setQuantity(good.getQuantity());
        go.setTax(goodTaxValue);
        go.setRoundedTax(roundedTax);
        go.setPrice(good.getPrice().add(go.getRoundedTax()));
        
        return go;
    }

    private Map<String, List<Goods>> populate(){

        Map<String, List<Goods>> ret = new HashMap<String, List<Goods>>();

        List<Category> categories = categoryRepository.findAllByOrderByIdAsc();

        for (Category category : categories) {
            ret.put(category.getName(), category.getGoods());
        }

        return ret;
    }


    public BigDecimal roundValue(BigDecimal value) {
        BigDecimal result = BigDecimal.valueOf((Math.ceil(value.doubleValue() * 20) / 20));
        result.setScale(2, RoundingMode.HALF_UP);

        return result;
    }


    private BigDecimal totalTaxOnSingleGood(BigDecimal price, int taxes){
        BigDecimal tax = BigDecimal.ZERO;
        if(taxes > 0){
            Double doubleValue = (price.doubleValue() * (0.01 * taxes));
            tax = BigDecimal.valueOf(doubleValue);
            tax = roundValue(tax);
        }

        return tax;
    }

    private int getTaxes(Goods good){
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
