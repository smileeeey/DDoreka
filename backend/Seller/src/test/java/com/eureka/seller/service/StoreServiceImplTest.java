package com.eureka.seller.service;

import com.eureka.seller.entity.Store;
import com.eureka.seller.repository.StoreRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StoreServiceImplTest {

    @Autowired
    StoreRepository storeRepository;

    @Test
    void getStoreBySellerId() {
        int sellerId = 1;

        Store store = storeRepository.findAllBySellerIdOrderByIdDesc(sellerId).get(0);

        Assertions.assertThat(store).isEqualTo(null);

    }
}