package com.eureka.user.controllers;

import com.eureka.user.Entity.CartEntity;
import com.eureka.user.Entity.UserEntity;
import com.eureka.user.dto.Cart;
import com.eureka.user.dto.UserInfo;
import com.eureka.user.services.AuthService;
import com.eureka.user.services.CookieUtil;
import com.eureka.user.services.JwtUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
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

@WebMvcTest(UserController.class)
@DisplayName("user controller 테스트")
class UserControllerTest {

    private MockMvc mockMvc;
    @Autowired
    public void setMockMvc(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @MockBean
    private AuthService authService;
    @MockBean
    private JwtUtil jwtUtil;
    @MockBean
    private CookieUtil cookieUtil;

    @DisplayName("로그인")
    @Test
    void login() throws Exception {
        /*
        입력으로 받을 파라미터를 정한다
         */

        String userEmail = "1";
        String pw = "1";
        Gson gson = new Gson();

        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("userEmail",userEmail);
        jsonObject.addProperty("pw",pw);
        String content = gson.toJson(jsonObject);

        ResultActions result = mockMvc.perform(post("/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andDo(print())
                .andExpect(status().isOk());
        /*
        실제 샘플 데이터를 입력하여 그 결과값이 일치하는지 확인한다.
        -> andExpect 활용
         */
    }

    @DisplayName("회원가입")
    @Test
    void saveUser() throws Exception {
        /*
        입력으로 받을 파라미터를 정한다
         */

        UserEntity user = new UserEntity();
        user.setId(1);
        Gson gson = new Gson();
        String content = gson.toJson(user);

        ResultActions result = mockMvc.perform(post("/user/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andDo(print())
                .andExpect(status().isOk());
        /*
        실제 샘플 데이터를 입력하여 그 결과값이 일치하는지 확인한다.
        -> andExpect 활용
         */
    }

    @DisplayName("구매자 정보 수정")
    @Test
    void updateUser() throws Exception {
        /*
        입력으로 받을 파라미터를 정한다
         */

        UserEntity user = new UserEntity();
        user.setId(1);
        Gson gson = new Gson();
        String content = gson.toJson(user);

        ResultActions result = mockMvc.perform(put("/user/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andDo(print())
                .andExpect(status().isOk());
        /*
        실제 샘플 데이터를 입력하여 그 결과값이 일치하는지 확인한다.
        -> andExpect 활용
         */
    }

    @DisplayName("구매자 핸드폰번호 수정")
    @Test
    void updateUserPhone() throws Exception {
        /*
        입력으로 받을 파라미터를 정한다
         */

        UserInfo user = new UserInfo();
        user.setId("1");
        Gson gson = new Gson();
        String content = gson.toJson(user);

        ResultActions result = mockMvc.perform(put("/user/update/phone")
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
    void deleteUser() {
    }
}