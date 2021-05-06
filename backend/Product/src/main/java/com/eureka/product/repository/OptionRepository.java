package com.eureka.product.repository;

import com.eureka.product.entity.Productoption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Productoption, Integer> {

}
