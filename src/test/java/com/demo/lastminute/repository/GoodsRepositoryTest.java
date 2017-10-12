package com.demo.lastminute.repository;

import com.demo.lastminute.domain.Category;
import com.demo.lastminute.domain.Goods;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GoodsRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    GoodsRepository goodsRepository;

    @Test
    public void prova(){
        assertThat("pippo").isEqualTo("pippo");
    }

    /*@Test
    public void findGoodsInput1(){
        Category c = new Category();
        c.setName("Input1");

        Goods g = new Goods();
        g.setExempt(false);
        g.setDuty(false);
        g.setPrice(new BigDecimal("10"));
        g.setQuantity(1);
        g.setCategory(c);
        g.setName("book");


        List<Goods> goods = goodsRepository.findAll();
        //assertThat(goods.size()).isEqualTo(5);
    }*/
}