package com.eureka.seller.controller;

import com.eureka.seller.entity.Store;
import com.eureka.seller.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StoreController {

    @Autowired
    private StoreService service;

    @PostMapping("/addStore")
    public Store addStore(@RequestBody Store store) {
        return service.saveStore(store);
    }

    @PostMapping("/addStores")
    public List<Store> addStores(@RequestBody List<Store> stores) {
        return service.saveStores(stores);
    }

    @GetMapping("/stores")
    public List<Store> findAllStores() {
        return service.getStores();
    }

    @GetMapping("/storesById/{id}")
    public Store findStoreById(@PathVariable int id) {
        return service.getStoreById(id);
    }

    @GetMapping("/store/{name}")
    public Store findStoreByName(@PathVariable String name) {
        return service.getStoreByName(name);
    }

    @PutMapping("/updateStore")
    public Store updateStore(@RequestBody Store store) {
        return service.updateStore(store);
    }

    @DeleteMapping("/deleteStore/{id}")
    public String deleteStore(@PathVariable int id) {
        return service.deleteStore(id);
    }
}
