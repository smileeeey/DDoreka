package com.eureka.product.repository;

import com.eureka.product.dto.Category2;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Category2Repository extends JpaRepository<Category2,String> {

    Category2 findByName(String name);
}
