package com.eureka.seller.service;

import com.eureka.seller.entity.Seller;

import java.util.List;

public interface SellerService {
    Seller saveSeller(Seller seller);
    List<Seller> saveSellers(List<Seller> sellers);
    List<Seller> getSellers();
    Seller getSellerById(int id);
    Seller getSellerByEmail(String email);
    String deleteSeller(int id);
    Seller updateSeller(Seller seller);
}
