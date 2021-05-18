package com.eureka.product.repository;

import com.eureka.product.dto.OptionPriceDTO;
import com.eureka.product.entity.Productoption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;


public interface OptionRepository extends JpaRepository<Productoption, Integer> {

    Integer findDiscountPriceByOptionId(int parseInt);

    Productoption findAllByOptionId(int optionId);

    List<Productoption> findAllByOptionIdIn(List<Integer> optionIdsInt);
}
