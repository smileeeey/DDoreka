package com.eureka.seller.controller;

import com.eureka.seller.entity.Seller;
import com.eureka.seller.service.SellerService;
import org.hibernate.usertype.UserVersionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return service.saveSellers(sellers);
    }

    @GetMapping("/sellers")
    public List<Seller> findAllSellers(){
        return service.getSellers();
    }

    @GetMapping("/sellerById/{id}")
    public Seller findSellerById(@PathVariable int id){
        return service.getSellerById(id);
    }

    @GetMapping("/seller/{name}")
    public Seller findSellerByName(@PathVariable String name){
        return service.getSellerByName(name);
    }

    @PutMapping("/updateSeller")
    public Seller updateSeller(@RequestBody Seller seller){
        return service.updateSeller(seller);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteSeller(@PathVariable int id){
        return service.deleteSeller(id);
    }
}
