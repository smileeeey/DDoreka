package com.eureka.product.repository;

import com.eureka.product.dto.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {


    Page<Product> findByCategory3Id(String id, PageRequest pageable);

    Page<Product> findByCategory4Id(String id, PageRequest pageable);

    Page<Product> findByCategory5Id(String id, PageRequest pageable);

    List<Product> findByName(String keyword);

    Page<Product> findByNameContains(String keyword, PageRequest pageable);

    Page<Product> findByCategory1IdAndNameContains(String category1Id, String keyword, PageRequest pageable);

    List<Product> findByStoreId(int storeId);
}
