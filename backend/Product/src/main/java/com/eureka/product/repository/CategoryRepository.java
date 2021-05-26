package com.eureka.product.repository;

import com.eureka.product.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

    Category findByName(String name);

    List<Category> findByDepthBetweenOrderByIdAsc(int i, int i1);

    List<Category> findByDepth(int i);
}
