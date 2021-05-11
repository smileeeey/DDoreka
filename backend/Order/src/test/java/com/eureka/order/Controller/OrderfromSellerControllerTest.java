package com.eureka.order.Controller;

import com.eureka.order.Entity.OrderEntity;
import com.eureka.order.service.OrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;


@WebMvcTest(OrderfromSellerController.class)
@DisplayName("셀러를 이용한 주문 통합 테스트")
class OrderfromSellerControllerTest {

    private MockMvc mockMvc;
    @Autowired
    public void setMockMvc(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }
    @MockBean
    OrderService orderService;


    @DisplayName("판매자의 id로 주문 찾기 : id : 1, page : 1, size : 1")
    @Test
    void getOrdersBySellerId() throws Exception {
        /*
        입력으로 받을 파라미터를 정한다
         */
        String sellerid = "1";
        int page = 1;
        int size = 1;

        List<OrderEntity> list = new ArrayList<>();
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(1);
        list.add(orderEntity);
        Page<OrderEntity> pages = new PageImpl<>(list);

        BDDMockito.given(orderService.getOrdersBySellerId(sellerid, page, size)).willReturn(pages);

        ResultActions result = mockMvc.perform(get("/order/sellerid/1/1/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.content").isArray())
                .andExpect(jsonPath("$.data.content[0].id", is("1")));
        /*
        실제 샘플 데이터를 입력하여 그 결과값이 일치하는지 확인한다.
        -> andExpect 활용
         */
    }

    @DisplayName("해당 판매자가 아직 주문확인 하지 않은 주문 찾기 : sellerid : 1")
    @Test
    void getOrdersBySellerIdWithUnchecked() throws Exception {
        /*
        입력으로 받을 파라미터를 정한다
         */
        String sellerid = "1";

        List<OrderEntity> list = new ArrayList<>();
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(1);
        list.add(orderEntity);
        BDDMockito.given(orderService.getOrdersBySellerIdWithUnchecked(sellerid)).willReturn(list);

        ResultActions result = mockMvc.perform(get("/order/sellerid/1/unchecked"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data.[0].id", is("1")));
        /*
        실제 샘플 데이터를 입력하여 그 결과값이 일치하는지 확인한다.
        -> andExpect 활용
         */
    }

    @DisplayName("상품의 주간 요일별 판매량(일요일 :1 ~)")
    @Test
    void getOrdersByProductIdSortedByDayofWeek() throws Exception {
        /*
        입력으로 받을 파라미터를 정한다
         */
        String productid = "1";
        List<Integer> list = new ArrayList<>();
        list.add(1);

        BDDMockito.given(orderService.getOrdersByProductIdSortedByDay(productid)).willReturn(list);

        ResultActions result = mockMvc.perform(get("/order/product/1/dayofweek"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data.[0]", is(1)));
        /*
        실제 샘플 데이터를 입력하여 그 결과값이 일치하는지 확인한다.
        -> andExpect 활용
         */
    }

    @DisplayName("상품의 월간 일별 판매정보 : sellerid : 1, month : 1")
    @Test
    void getOrdersBySellerIdByday() throws Exception {
        /*
        입력으로 받을 파라미터를 정한다
         */
        String sellerid = "1";
        String month = "1";

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(1);
        List<OrderEntity> list = new ArrayList<>();
        list.add(orderEntity);
        List<List<OrderEntity>> motherList = new ArrayList<>();
        motherList.add(list);

        BDDMockito.given(orderService.getOrdersBySellerIdByday(sellerid, month)).willReturn(motherList);

        ResultActions result = mockMvc.perform(get("/order/sellerid/1/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data.[0].[0].id", is("1")));
        /*
        실제 샘플 데이터를 입력하여 그 결과값이 일치하는지 확인한다.
        -> andExpect 활용
         */
    }


    @DisplayName("주문 상태별 조회 : sellerid : 1, status : 1")
    @Test
    void getOrdersBySellerIdBystatus() throws Exception {
        /*
        입력으로 받을 파라미터를 정한다
         */
        String sellerid = "1";
        int status = 1;

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(1);
        List<OrderEntity> list = new ArrayList<>();
        list.add(orderEntity);

        BDDMockito.given(orderService.getOrdersBySellerIdBystatus(sellerid, status)).willReturn(list);

        ResultActions result = mockMvc.perform(get("/order/sellerid/1/status/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data.[0].id", is("1")));
        /*
        실제 샘플 데이터를 입력하여 그 결과값이 일치하는지 확인한다.
        -> andExpect 활용
         */
    }

}