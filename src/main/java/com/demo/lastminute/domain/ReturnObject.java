package com.demo.lastminute.domain;

import java.math.BigDecimal;
import java.util.List;

public class ReturnObject {

    private String categoryName;

    private BigDecimal totalTax;

    private BigDecimal totalPrice;

    private List<GoodsOutput> goodsOutput;

    public BigDecimal getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(BigDecimal totalTax) {
        this.totalTax = totalTax;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<GoodsOutput> getGoodsOutput() {
        return goodsOutput;
    }

    public void setGoodsOutput(List<GoodsOutput> goodsOutput) {
        this.goodsOutput = goodsOutput;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
