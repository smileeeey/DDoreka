package com.eureka.seller.controller;

import com.eureka.seller.entity.Seller;
import com.eureka.seller.entity.Store;
import com.eureka.seller.service.SellerService;
import com.eureka.seller.service.StoreService;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StoreController.class)
@DisplayName("스토어 테스트")
class StoreControllerTest {


    private MockMvc mockMvc;
    @Autowired
    public void setMockMvc(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @MockBean
    StoreService service;

    @DisplayName("스토어 추가")
    @Test
    void addStore() throws Exception {
        /*
        입력으로 받을 파라미터를 정한다
         */

        Store store = new Store();
        store.setId(1);

        Gson gson = new Gson();
        String content = gson.toJson(store);

        ResultActions result = mockMvc.perform(post("/store/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andDo(print())
                .andExpect(status().isOk());
        /*
        실제 샘플 데이터를 입력하여 그 결과값이 일치하는지 확인한다.
        -> andExpect 활용
         */
    }

    @Test
    void addStores() {
    }

    @Test
    void findAllStores() {
    }

    @Test
    void findStoreById() {
    }

    @Test
    void findStoreBySellerId() throws Exception {
        /*
        입력으로 받을 파라미터를 정한다
         */
        int sellerId = 1;

        Store store = new Store();
        store.setId(1);

        BDDMockito.given(service.getStoreBySellerId(sellerId)).willReturn(store);
        ResultActions result = mockMvc.perform(get("/store//getBySellerId/1"))
                .andDo(print())
                .andExpect(status().isOk());
        /*
        실제 샘플 데이터를 입력하여 그 결과값이 일치하는지 확인한다.
        -> andExpect 활용
         */
    }

    @Test
    void findStoreByName() {
    }

    @Test
    void updateStore() {
    }

    @Test
    void deleteStore() {
    }
}