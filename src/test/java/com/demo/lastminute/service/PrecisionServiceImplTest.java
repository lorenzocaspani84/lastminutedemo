package com.demo.lastminute.service;

import com.demo.lastminute.domain.Goods;
import com.demo.lastminute.repository.BasketRepository;
import com.demo.lastminute.repository.GoodsRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
public class PrecisionServiceImplTest {

    private Goods goods;

    @TestConfiguration
    static class PrecisionServiceImplTestContextConfiguration {

        @Bean
        public PrecisionService precisionService() {

            return new PrecisionServiceImpl();
        }

        @Bean
        public GoodsService goodsService() {

            return new GoodsServiceImpl();
        }

    }

    @Autowired
    private PrecisionService precisionService;

    @Autowired
    private GoodsService goodsService;

    @MockBean
    private GoodsRepository goodsRepository;

    @MockBean
    private BasketRepository basketRepository;

    @Before
    public void setUp() {
        //(5, true, false, 'imported bottle of perfume', '47.50', '1', '2');
        Goods goodsTest = new Goods();
        goodsTest.setName("imported bottle of perfume");
        goodsTest.setDuty(true);
        goodsTest.setExempt(false);
        goodsTest.setQuantity(1);
        goodsTest.setPrice(BigDecimal.valueOf(47.50));

        this.goods = goodsTest;
    }


    @Test
    public void testRoudValue(){

        int taxes = goodsService.getTaxes(goods);
        BigDecimal tax = BigDecimal.ZERO;
        if(taxes > 0){
            Double doubleValue = (goods.getPrice().doubleValue() *taxes) / 100;
            tax = BigDecimal.valueOf(doubleValue);
            tax = precisionService.roundValue(tax);
        }

        assertThat(tax).isEqualTo(BigDecimal.valueOf(7.15));
    }



}
