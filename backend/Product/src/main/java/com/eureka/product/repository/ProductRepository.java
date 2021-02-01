package com.eureka.product.repository;

import com.eureka.product.dto.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    List<Product> findByCategory3(String id);
    List<Product> findByCategory4(String id);
    List<Product> findByCategory5(String id);

    List<Product> findByName(String keyword);

    List<Product> findByCategory1AndNameLike(String categoryId, String keyword);
}
