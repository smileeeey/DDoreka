package com.eureka.seller.controller;

import com.eureka.seller.entity.Store;
import com.eureka.seller.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/store")
public class StoreController {

    @Autowired
    private StoreService service;

    @PostMapping("/add")
    public Store addStore(@RequestBody Store store) {
        return service.saveStore(store);
    }

    @PostMapping("/addAll")
    public List<Store> addStores(@RequestBody List<Store> stores) {
        return service.saveStores(stores);
    }

    @GetMapping("/getAll")
    public List<Store> findAllStores() {
        return service.getStores();
    }

    @GetMapping("/getById/{id}")
    public Store findStoreById(@PathVariable int id) {
        return service.getStoreById(id);
    }

    @GetMapping("/getBySellerId/{sellerId}")
    public Store findStoreBySellerId(@PathVariable int sellerId) {
        return service.getStoreBySellerId(sellerId);
    }

    @GetMapping("/getByName/{name}")
    public Store findStoreByName(@PathVariable String name) {
        return service.getStoreByName(name);
    }

    @PutMapping("/update")
    public Store updateStore(@RequestBody Store store) {
        return service.updateStore(store);
    }

    @DeleteMapping("/deleteById/{id}")
    public String deleteStore(@PathVariable int id) {
        return service.deleteStore(id);
    }
}
