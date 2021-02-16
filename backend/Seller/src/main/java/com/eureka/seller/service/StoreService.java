package com.eureka.seller.service;

import com.eureka.seller.entity.Store;
import com.eureka.seller.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {
    @Autowired
    private StoreRepository repository;

    public Store saveStore(Store store) {
        return repository.save(store);
    }

    public List<Store> saveStores(List<Store> stores) {
        return repository.saveAll(stores);
    }

    public List<Store> getStores() {
        return repository.findAll();
    }

    public Store getStoreById(int id) {
        return repository.findById(id).orElse(null);
    }
    public Store getStoreBySellerId(int sellerId) {
        return repository.findAllBySellerIdOrderByIdDesc(sellerId).get(0);
    }

    public Store getStoreByName(String name) {
        return repository.findByName(name);
    }

    public String deleteStore(int id) {
        repository.deleteById(id);
        return "store removed !!" + id;
    }

    //need to add function to change seller id
    public Store updateStore(Store store) {
        Store existingStore = repository.findById(store.getId()).orElse(null);
        existingStore.setName(store.getName());
        existingStore.setPhone(store.getPhone());
        existingStore.setMain_address(store.getMain_address());
        existingStore.setSub_address(store.getSub_address());
        existingStore.setZipcode(store.getZipcode());
        return repository.save(existingStore);
    }
}
