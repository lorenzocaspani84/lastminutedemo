package com.demo.lastminute.repository;

import com.demo.lastminute.domain.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "goodsRepository")
public interface GoodsRepository extends JpaRepository<Goods, Long> {

}
