package com.eureka.product.repository;

import com.eureka.product.entity.Productoption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


public interface OptionRepository extends JpaRepository<Productoption, Integer> {

    int findDiscountPriceByOptionId(String s);
}
