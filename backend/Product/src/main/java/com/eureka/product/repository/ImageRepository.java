package com.eureka.product.repository;

import com.eureka.product.dto.Productimage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Productimage,Integer> {

}