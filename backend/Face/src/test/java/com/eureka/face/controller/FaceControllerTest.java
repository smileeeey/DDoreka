package com.eureka.face.controller;

import com.eureka.face.entity.Face;
import com.eureka.face.service.FaceService;
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

@WebMvcTest(FaceController.class)
@DisplayName("페이스 컨트롤러 테스트")
class FaceControllerTest {

    private MockMvc mockMvc;
    @Autowired
    public void setMockMvc(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @MockBean
    private FaceService service;

    @DisplayName("감정분석 결과 저장")
    @Test
    void addFace() throws Exception {
        /*
        입력으로 받을 파라미터를 정한다
         */

        Face face = new Face();
        face.setId(1);
        Gson gson = new Gson();
        String content = gson.toJson(face);

        ResultActions result = mockMvc.perform(post("/face/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andDo(print())
                .andExpect(status().isOk());
        /*
        실제 샘플 데이터를 입력하여 그 결과값이 일치하는지 확인한다.
        -> andExpect 활용
         */
    }

    @DisplayName("감정분석 결과 조회")
    @Test
    void getFacesByUser() throws Exception {
        /*
        입력으로 받을 파라미터를 정한다
         */
        int user = 1;
        List<Face> list = new ArrayList<>();
        Face face = new Face();
        face.setId(1);

        BDDMockito.given(service.getFacesByUser(user)).willReturn(list);
        ResultActions result = mockMvc.perform(get("/face/getAllByUser/1"))
                .andDo(print())
                .andExpect(status().isOk());
        /*
        실제 샘플 데이터를 입력하여 그 결과값이 일치하는지 확인한다.
        -> andExpect 활용
         */
    }

    @DisplayName("감정분석 결과 삭제(세모)")
    @Test
    void deleteFace() throws Exception {
        /*
        입력으로 받을 파라미터를 정한다
         */

        int id = 1;
        Gson gson = new Gson();
        String content = gson.toJson(id);

        ResultActions result = mockMvc.perform(delete("/face/deleteById/1")
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