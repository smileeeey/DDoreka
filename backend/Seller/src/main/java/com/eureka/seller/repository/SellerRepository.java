package com.eureka.seller.repository;

import com.eureka.seller.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Integer> {
    Seller findByEmail(String email);
}
