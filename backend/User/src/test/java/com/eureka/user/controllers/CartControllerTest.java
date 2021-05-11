package com.eureka.user.controllers;

import com.eureka.user.Entity.CartEntity;
import com.eureka.user.Entity.UseraddressEntity;
import com.eureka.user.dto.Cart;
import com.eureka.user.services.*;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CartController.class)
@DisplayName("cart controller 테스트")
class CartControllerTest {

    private MockMvc mockMvc;
    @Autowired
    public void setMockMvc(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @MockBean
    private JwtUtil jwtUtil;
    @MockBean
    private CartService cartService;
    @MockBean
    private  AuthService authService;

    @DisplayName("장바구니 조회")
    @Test
    void getcart() throws Exception {
        /*
        입력으로 받을 파라미터를 정한다
         */
        String UserEmail = "1";
        String userid = "1";

        List<CartEntity> list = new ArrayList<>();
        CartEntity cartEntity = new CartEntity();
        cartEntity.setId("1");
        list.add(cartEntity);

        BDDMockito.given(cartService.getCart(userid)).willReturn(list);
        ResultActions result = mockMvc.perform(get("/user/cart/1"))
                .andDo(print())
                .andExpect(status().isOk());
        /*
        실제 샘플 데이터를 입력하여 그 결과값이 일치하는지 확인한다.
        -> andExpect 활용
         */
    }

    @DisplayName("장바구니 저장")
    @Test
    void saveCart() throws Exception {
        /*
        입력으로 받을 파라미터를 정한다
         */

        Cart cart = new Cart();
        cart.setOptionId("1");

        Gson gson = new Gson();
        String content = gson.toJson(cart);

        ResultActions result = mockMvc.perform(post("/user/cart")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andDo(print())
                .andExpect(status().isOk());
        /*
        실제 샘플 데이터를 입력하여 그 결과값이 일치하는지 확인한다.
        -> andExpect 활용
         */
    }

    @DisplayName("장바구니 수정(세모)")
    @Test
    void updateCart() throws Exception {
        /*
        입력으로 받을 파라미터를 정한다
         */

        CartEntity cartEntity = new CartEntity();
        cartEntity.setId("1");

        Gson gson = new Gson();
        String content = gson.toJson(cartEntity);

        ResultActions result = mockMvc.perform(put("/user/cart")
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
    void deleteAllCart() {
    }

    @DisplayName("장바구니 개별 항목 삭제")
    @Test
    void deleteCart() throws Exception {
        /*
        입력으로 받을 파라미터를 정한다
         */

        String cartId = "1";

        Gson gson = new Gson();
        String content = gson.toJson(cartId);

        ResultActions result = mockMvc.perform(delete("/user/cart/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andDo(print())
                .andExpect(status().isOk());
        /*
        실제 샘플 데이터를 입력하여 그 결과값이 일치하는지 확인한다.
        -> andExpect 활용
         */
    }
}