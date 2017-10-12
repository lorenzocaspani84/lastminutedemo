package com.demo.lastminute.domain;

import com.sun.javafx.beans.IDProperty;
import jdk.nashorn.internal.runtime.logging.Logger;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
@Inheritance(strategy = InheritanceType.JOINED)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(targetEntity = Goods.class, orphanRemoval = true, mappedBy = "category", cascade = {
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
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
