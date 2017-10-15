package com.demo.lastminute.repository;

import com.demo.lastminute.domain.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsRepository extends JpaRepository<Goods, Long> {

    Goods findGoodsByName(String name);

}
