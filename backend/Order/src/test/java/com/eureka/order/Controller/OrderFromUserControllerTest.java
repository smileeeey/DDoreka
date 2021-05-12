package com.eureka.order.Controller;

import com.eureka.order.Entity.OrderDetailEntity;
import com.eureka.order.Entity.OrderEntity;
import com.eureka.order.service.OrderService;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderFromUserController.class)
@DisplayName("유저를 이용한 주문 통합 테스트")
class OrderFromUserControllerTest {

    private MockMvc mockMvc;
    @Autowired
    public void setMockMvc(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }
    @MockBean
    OrderService orderService;

    @DisplayName("판매자의 id로 주문 찾기 with paging x : id : 1, page : 1, size : 1")
    @Test
    void getOrdersByUserId() throws Exception {
        /*
        입력으로 받을 파라미터를 정한다
         */
        String userId = "1";
        int page = 1;
        int size = 1;

        List<OrderEntity> list = new ArrayList<>();
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(1);
        list.add(orderEntity);
        Page<OrderEntity> pages = new PageImpl<>(list);

        BDDMockito.given(orderService.getOrdersByUserId(userId,page,size)).willReturn(pages);

        ResultActions result = mockMvc.perform(get("/order/userid/1/1/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.content").isArray())
                .andExpect(jsonPath("$.data.content[0].id", is("1")));
        /*
        실제 샘플 데이터를 입력하여 그 결과값이 일치하는지 확인한다.
        -> andExpect 활용
         */
    }

    @DisplayName("해당 구매자의 모든 주문 정보 : userId : 1")
    @Test
    void getOrders() throws Exception {
        /*
        입력으로 받을 파라미터를 정한다
         */
        String userId = "1";

        List<OrderEntity> list = new ArrayList<>();
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(1);
        list.add(orderEntity);

        BDDMockito.given(orderService.getOrdersByUserId(userId)).willReturn(list);

        ResultActions result = mockMvc.perform(get("/order/userid/1/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data.[0].id", is("1")));
        /*
        실제 샘플 데이터를 입력하여 그 결과값이 일치하는지 확인한다.
        -> andExpect 활용
         */
    }

    @DisplayName("구매자의 주문 상태별 조회 : userid : 1, status : 1")
    @Test
    void getOrdersByUserIdBystatus() throws Exception {
        /*
        입력으로 받을 파라미터를 정한다
         */
        String userid = "1";
        int status = 1;

        List<OrderEntity> list = new ArrayList<>();
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(1);
        list.add(orderEntity);

        BDDMockito.given(orderService.getOrdersByUserIdBystatus(userid, status)).willReturn(list);

        ResultActions result = mockMvc.perform(get("/order/userid/1/status/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data.[0].id", is("1")));
        /*
        실제 샘플 데이터를 입력하여 그 결과값이 일치하는지 확인한다.
        -> andExpect 활용
         */
    }

    @DisplayName("구매자의 주문 데이터 저장 : OrderEntity : orderEntity")
    @Test
    void saveOrders() throws Exception {
        /*
        입력으로 받을 파라미터를 정한다
         */
        OrderEntity inputedOrderEntity = new OrderEntity();
        inputedOrderEntity.setUserId("1");

        Gson gson = new Gson();
        String content = gson.toJson(inputedOrderEntity);

        ResultActions result = mockMvc.perform(post("/order")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray());

        /*
        실제 샘플 데이터를 입력하여 그 결과값이 일치하는지 확인한다.
        -> andExpect 활용
         */
    }

    @DisplayName("구매자의 주문 데이터 수정")
    @Test
    void updateOrders() throws Exception {
        /*
        입력으로 받을 파라미터를 정한다
         */
        OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
        orderDetailEntity.setCancelMsg("1");

        Gson gson = new Gson();
        String content = gson.toJson(orderDetailEntity);

        ResultActions result = mockMvc.perform(put("/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andDo(print())
                .andExpect(status().isOk());

        /*
        실제 샘플 데이터를 입력하여 그 결과값이 일치하는지 확인한다.
        -> andExpect 활용
         */
    }


    @DisplayName("최근 한달간 주문이 가장 많았던 상품 top10")
    @Test
    void steadySeller() throws Exception {
        /*
        입력으로 받을 파라미터를 정한다
         */
        String userid = "1";
        int status = 1;

        List<Integer> list = new ArrayList<>();
        list.add(1);

        BDDMockito.given(orderService.getSteadySeller()).willReturn(list);

        ResultActions result = mockMvc.perform(get("/order/recommend/steadyseller"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data.[0]", is(1)));
        /*
        실제 샘플 데이터를 입력하여 그 결과값이 일치하는지 확인한다.
        -> andExpect 활용
         */
    }

    @DisplayName("최근 일주일간 주문이 가장 많았던 상품 top10")
    @Test
    void hotProduct() throws Exception {
        /*
        입력으로 받을 파라미터를 정한다
         */
        String userid = "1";
        int status = 1;

        List<Integer> list = new ArrayList<>();
        list.add(1);

        BDDMockito.given(orderService.getHotProduct()).willReturn(list);

        ResultActions result = mockMvc.perform(get("/order/recommend/hotproduct"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data.[0]", is(1)));
        /*
        실제 샘플 데이터를 입력하여 그 결과값이 일치하는지 확인한다.
        -> andExpect 활용
         */
    }

    @DisplayName("최근 24시간 동안 주문이 가장 많았던 상품 top6")
    @Test
    void todayHot() throws Exception {
        /*
        입력으로 받을 파라미터를 정한다
         */
        String userid = "1";
        int status = 1;

        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("1",1);
        list.add(map);

        BDDMockito.given(orderService.getTodayHot()).willReturn(list);

        ResultActions result = mockMvc.perform(get("/order/recommend/todayhot"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data.[0].1", is(1)));
        /*
        실제 샘플 데이터를 입력하여 그 결과값이 일치하는지 확인한다.
        -> andExpect 활용
         */
    }
}