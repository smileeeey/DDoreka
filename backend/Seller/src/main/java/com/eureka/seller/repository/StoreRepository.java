package com.eureka.seller.repository;

import com.eureka.seller.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Integer> {
    Store findByName(String name);
    List<Store> findAllBySellerIdOrderByIdDesc(int sellerId);
}
