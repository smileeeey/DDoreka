package com.eureka.product.repository;

import com.eureka.product.entity.Productimage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

public interface ImageRepository extends JpaRepository<Productimage, Integer> {

    List<Productimage> findByProductId(int id);
}