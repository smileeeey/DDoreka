package com.eureka.seller.controller;

import com.eureka.seller.entity.Seller;
import com.eureka.seller.service.SellerService;
import org.hibernate.usertype.UserVersionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private SellerService service;

    @PostMapping("/add")
    public Seller addSeller(@RequestBody Seller seller) {
        return service.saveSeller(seller);
    }

    @PostMapping("/addAll")
    public List<Seller> addSellers(@RequestBody List<Seller> sellers) {
        return service.saveSellers(sellers);
    }

    @GetMapping("/getAll")
    public List<Seller> findAllSellers() {
        return service.getSellers();
    }

    @GetMapping("/getById/{id}")
    public Seller findSellerById(@PathVariable int id) {
        return service.getSellerById(id);
    }

    @GetMapping("/getByName/{name}")
    public Seller findSellerByName(@PathVariable String name) {
        return service.getSellerByName(name);
    }

    @PutMapping("/update")
    public Seller updateSeller(@RequestBody Seller seller) {
        return service.updateSeller(seller);
    }

    @DeleteMapping("/deleteById/{id}")
    public String deleteSeller(@PathVariable int id) {
        return service.deleteSeller(id);
    }
}
