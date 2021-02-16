package com.eureka.product.repository;

import com.eureka.product.dto.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, String> {

    Category findByName(String name);

    List<Category> findByDepthBetweenOrderByIdAsc(int i, int i1);

    List<Category> findByDepth(int i);
}
