package com.eureka.product.repository;

import com.eureka.product.dto.ProductSimpleDTO;
import com.eureka.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@EnableJpaRepositories
public interface ProductRepository extends JpaRepository<Product, Integer> {


    Page<Product> findByCategory3Id(String id, Pageable pageable);

    Page<Product> findByCategory4Id(String id, Pageable pageable);

    Page<Product> findByCategory5Id(String id, Pageable pageable);

    List<Product> findByName(String keyword);

    Page<Product> findByNameContains(String keyword, Pageable pageable);

    Page<Product> findByCategory1IdAndNameContains(String category1Id, String keyword, Pageable pageable);

    List<Product> findByStoreId(int storeId);

    List<Product> findTop23ByCategory1IdOrderByRegisterDateDesc(String id);

    List<Product> findByIdIn(List<Integer> productIds);

    List<Product> findIdTop10ByStoreIdOrderByRegisterDateDesc(int storeId);

    String findNameById(int productId);
}
