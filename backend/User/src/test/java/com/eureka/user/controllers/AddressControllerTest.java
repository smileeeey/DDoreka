package com.eureka.user.controllers;

import com.eureka.user.Entity.UseraddressEntity;
import com.eureka.user.services.AddressService;
import com.eureka.user.services.AuthService;
import com.eureka.user.services.CookieUtil;
import com.eureka.user.services.JwtUtil;
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

@WebMvcTest(AddressController.class)
@DisplayName("address controller 테스트")
class AddressControllerTest {

    private MockMvc mockMvc;
    @Autowired
    public void setMockMvc(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @MockBean
    private AuthService authService;
    @MockBean
    private AddressService addressService;
    @MockBean
    private JwtUtil jwtUtil;
    @MockBean
    private CookieUtil cookieUtil;

    @DisplayName("구매자별 주소 조회")
    @Test
    void getAddress() throws Exception {
        /*
        입력으로 받을 파라미터를 정한다
         */
        String loginUser = "1";

        List<UseraddressEntity> list = new ArrayList<>();

        BDDMockito.given(addressService.getaddress(loginUser)).willReturn(list);
        ResultActions result = mockMvc.perform(get("/user/address/1"))
                .andDo(print())
                .andExpect(status().isOk());
        /*
        실제 샘플 데이터를 입력하여 그 결과값이 일치하는지 확인한다.
        -> andExpect 활용
         */
    }

    @DisplayName("주소 등록")
    @Test
    void saveAddress() throws Exception {
        /*
        입력으로 받을 파라미터를 정한다
         */

        Gson gson = new Gson();
        UseraddressEntity useraddressEntity = new UseraddressEntity();
        useraddressEntity.setAddressId("1");
        String email = "1";

        String content = gson.toJson(useraddressEntity);

        ResultActions result = mockMvc.perform(post("/user/address/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andDo(print())
                .andExpect(status().isOk());
        /*
        실제 샘플 데이터를 입력하여 그 결과값이 일치하는지 확인한다.
        -> andExpect 활용
         */
    }

    @DisplayName("주소 수정(세모)")
    @Test
    void updateAddress() throws Exception {
         /*
        입력으로 받을 파라미터를 정한다
         */

        Gson gson = new Gson();
        UseraddressEntity useraddressEntity = new UseraddressEntity();
        useraddressEntity.setAddressId("1");
        String email = "1";

        String content = gson.toJson(useraddressEntity);

        ResultActions result = mockMvc.perform(put("/user/address/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andDo(print())
                .andExpect(status().isOk());
        /*
        실제 샘플 데이터를 입력하여 그 결과값이 일치하는지 확인한다.
        -> andExpect 활용
         */
    }

    @DisplayName("주소 삭제")
    @Test
    void deleteAddress() throws Exception {
                 /*
        입력으로 받을 파라미터를 정한다
         */

        Gson gson = new Gson();
        UseraddressEntity useraddressEntity = new UseraddressEntity();
        useraddressEntity.setAddressId("1");
        String email = "1";

        String content = gson.toJson(useraddressEntity);

        ResultActions result = mockMvc.perform(delete("/user/address/1/1")
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