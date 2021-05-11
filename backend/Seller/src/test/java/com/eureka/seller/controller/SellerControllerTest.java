package com.eureka.seller.controller;

import com.eureka.seller.entity.Seller;
import com.eureka.seller.service.SellerServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SellerController.class)
@DisplayName("셀러 테스트")
class SellerControllerTest {

    private MockMvc mockMvc;
    @Autowired
    public void setMockMvc(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Autowired
    SellerServiceImpl service;

    @DisplayName("셀러 등록")
    @Test
    void addSeller() throws Exception {
        /*
        입력으로 받을 파라미터를 정한다
         */

        Seller seller = new Seller();
        seller.setId(1);

        BDDMockito.given(service.saveSeller(seller)).willReturn(seller);

        ResultActions result = mockMvc.perform(post("/seller/add"))
                .andDo(print())
                .andExpect(status().isOk());
        /*
        실제 샘플 데이터를 입력하여 그 결과값이 일치하는지 확인한다.
        -> andExpect 활용
         */
    }

    @Test
    void addSellers() {
    }

    @Test
    void findAllSellers() {
    }

    @Test
    void findSellerById() {
    }

    @Test
    void findSellerByEmail() {
    }

    @Test
    void updateSeller() {
    }

    @Test
    void deleteSeller() {
    }
}