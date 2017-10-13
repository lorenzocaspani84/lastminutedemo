package com.demo.lastminute.dto;

import java.math.BigDecimal;

public class GoodsOutput {

    private String name;

    private Integer quantity;

    private BigDecimal price;

    private BigDecimal tax;

    private BigDecimal roundedTax;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getRoundedTax() {
        return roundedTax;
    }

    public void setRoundedTax(BigDecimal roundedTax) {
        this.roundedTax = roundedTax;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", tax=" + tax +
                ", roundedTax=" + roundedTax +
                '}';
    }
}
