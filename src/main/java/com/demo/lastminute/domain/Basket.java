package com.demo.lastminute.domain;

import com.sun.javafx.beans.IDProperty;
import jdk.nashorn.internal.runtime.logging.Logger;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "basket")
@Inheritance(strategy = InheritanceType.JOINED)
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "basket_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(targetEntity = Goods.class, orphanRemoval = true, mappedBy = "basket", cascade = {
            CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
    private List<Goods> goods;

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

    public List<Goods> getGoods() {
        return goods;
    }

    public void setGoods(List<Goods> goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "Basket{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
