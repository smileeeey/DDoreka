package com.eureka.seller.service;

import com.eureka.seller.entity.Store;

import java.util.List;

public interface StoreService {
    Store saveStore(Store store);
    List<Store> saveStores(List<Store> stores);
    List<Store> getStores();
    Store getStoreById(int id);
    Store getStoreBySellerId(int sellerId);
    Store getStoreByName(String name);
    String deleteStore(int id);
    //need to add function to change seller id
    Store updateStore(Store store);
}
