package com.eureka.seller.controller;

import com.eureka.seller.entity.Seller;
import com.eureka.seller.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SellerController {

    @Autowired
    private SellerService service;

    @PostMapping("/addSeller")
    public Seller addSeller(@RequestBody Seller seller){
        return service.saveSeller(seller);
    }

    @PostMapping("/addSellers")
    public List<Seller> addSellers(@RequestBody List<Seller> sellers){

    }
}
