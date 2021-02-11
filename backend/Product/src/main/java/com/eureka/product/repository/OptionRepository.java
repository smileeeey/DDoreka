package com.eureka.product.repository;

import com.eureka.product.dto.Productoption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionRepository extends JpaRepository<Productoption, Integer> {

}
