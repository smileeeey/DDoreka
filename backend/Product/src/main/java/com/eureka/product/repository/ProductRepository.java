package com.eureka.product.repository;

import com.eureka.product.dto.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    List<Product> findByCategory3Id(String id);
    List<Product> findByCategory4Id(String id);
    List<Product> findByCategory5Id(String id);

    List<Product> findByName(String keyword);

    List<Product> findByCategory1IdAndNameLike(String categoryId, String keyword);
}
