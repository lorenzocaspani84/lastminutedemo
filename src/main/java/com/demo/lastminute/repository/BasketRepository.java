package com.demo.lastminute.repository;

import com.demo.lastminute.domain.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {

    List<Basket> findAllByOrderByIdAsc();
}
