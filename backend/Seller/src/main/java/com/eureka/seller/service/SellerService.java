package com.eureka.seller.service;

import com.eureka.seller.entity.Seller;
import com.eureka.seller.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerService {
    @Autowired
    private SellerRepository repository;

    public Seller savaSeller(Seller seller){
        return repository.save(seller);
    }

    public List<Seller> savaSellers(List<Seller> sellers){
        return repository.saveAll(sellers);
    }
}
