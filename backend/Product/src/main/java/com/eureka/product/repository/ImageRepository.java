package com.eureka.product.repository;

import com.eureka.product.dto.Productimage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Productimage, Integer> {

    List<Productimage> findByProductId(int id);
}