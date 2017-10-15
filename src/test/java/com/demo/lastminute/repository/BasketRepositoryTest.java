package com.demo.lastminute.repository;

import com.demo.lastminute.LastminuteApplication;
import com.demo.lastminute.LastminuteApplicationTest;
import com.demo.lastminute.domain.Basket;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@Profile("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = LastminuteApplicationTest.class)
public class BasketRepositoryTest {

    @Autowired
    BasketRepository basketRepository;

    @Test
    public void test() throws Exception {
        List<Basket> baskets = basketRepository.findAllByOrderByIdAsc();

        assertThat(baskets).isNotEmpty();
    }
}