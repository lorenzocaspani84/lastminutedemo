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
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class GoodsServiceImplTest {

    @TestConfiguration
    static class GoodsServiceImplTestContextConfiguration {

        @Bean
        public GoodsService goodsService() {

            return new GoodsServiceImpl();
        }

        @Bean
        public PrecisionService precisionService() {

            return new PrecisionServiceImpl();
        }

    }

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private PrecisionService precisionService;

    @MockBean
    private GoodsRepository goodsRepository;

    @MockBean
    private BasketRepository basketRepository;

    @Before
    public void setUp() {
        //(5, true, false, 'imported bottle of perfume', '47.50', '1', '2');
        Goods goods = new Goods();
        goods.setName("imported bottle of perfume");
        goods.setDuty(true);
        goods.setExempt(false);
        goods.setQuantity(1);
        goods.setPrice(BigDecimal.valueOf(47.50));

        Mockito.when(goodsRepository.findGoodsByName(goods.getName()))
                .thenReturn(goods);
    }

    @Test
    public void whenValidName_thenGoodsBeFound() {
        String name = "imported bottle of perfume";
        Goods found = goodsService.findGoodsByName(name);

        assertThat(found.getName())
                .isEqualTo(name);
    }


    @Test
    public void whenSingleGoods_thenCorrectTax() {
        String name = "imported bottle of perfume";
        Goods goods = goodsService.findGoodsByName(name);

        int taxes = goodsService.getTaxes(goods);

        assertThat(taxes)
                .isEqualTo(15);
    }

    @Test
    public void whenSingleGoods_thenCorrectTotalTax() {
        String name = "imported bottle of perfume";
        Goods goods = goodsService.findGoodsByName(name);

        BigDecimal singleGoodsTotalTaxes = goodsService.totalTaxOnSingleGood(goods.getPrice(), 15);

        assertThat(singleGoodsTotalTaxes)
                .isEqualTo(BigDecimal.valueOf(7.15));
    }

}