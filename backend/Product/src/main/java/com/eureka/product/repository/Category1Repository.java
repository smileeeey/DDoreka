package com.eureka.product.repository;

import com.eureka.product.dto.Category1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface Category1Repository extends JpaRepository<Category1,String> {

    Category1 findByName(String name);
}
