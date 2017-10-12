package com.demo.lastminute.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "goods")
@Inheritance(strategy = InheritanceType.JOINED)
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "goods_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "exempt")
    private boolean exempt;

    @Column(name = "duty")
    private boolean duty;

    @ManyToOne(targetEntity = Category.class, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "category_id")
    private Category category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public boolean isExempt() {
        return exempt;
    }

    public void setExempt(boolean exempt) {
        this.exempt = exempt;
    }

    public boolean isDuty() {
        return duty;
    }

    public void setDuty(boolean duty) {
        this.duty = duty;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", exempt=" + exempt +
                ", duty=" + duty +
                '}';
    }
}
