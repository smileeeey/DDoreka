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

    public Seller saveSeller(Seller seller) {
        return repository.save(seller);
    }

    public List<Seller> saveSellers(List<Seller> sellers) {
        return repository.saveAll(sellers);
    }

    public List<Seller> getSellers() {
        return repository.findAll();
    }

    public Seller getSellerById(int id) {
        return repository.findById(id).orElse(null);
    }

    public Seller getSellerByEmail(String email) {
        return repository.findByEmail(email);
    }

    public String deleteSeller(int id) {
        repository.deleteById(id);
        return "Seller removed !!" + id;
    }

    public Seller updateSeller(Seller seller) {
        Seller existingSeller = repository.findById(seller.getId()).orElse(null);
        existingSeller.setName(seller.getName());
        existingSeller.setEmail(seller.getEmail());
        existingSeller.setPhone(seller.getPhone());
        existingSeller.setBank_company(seller.getBank_company());
        existingSeller.setBank_account(seller.getBank_account());
        return repository.save(existingSeller);
    }
}
