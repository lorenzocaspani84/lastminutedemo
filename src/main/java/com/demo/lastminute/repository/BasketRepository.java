package com.demo.lastminute.repository;

import com.demo.lastminute.domain.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BasketRepository extends JpaRepository<Basket, Long> {

    List<Basket> findAllByOrderByIdAsc();
}
