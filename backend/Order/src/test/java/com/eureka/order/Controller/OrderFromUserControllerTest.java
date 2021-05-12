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

/**
 * test for order from user controller
 * @author tested by shimjaehyuk, leesumin
 * @since 1.0
 */
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

    /**
     * get orders by user id
     * @throws Exception
     *
     * Test Content **********
     * 유저 아이디를 입력하면 셀러가 가진 정보를 출력합니다.
     * 아이디가 jaehyuk인 사용자가 가진 OrderEntity 정보를 테스트합니다
     * 테스트할 OrderEntity의 일부 내용은 다음과 같습니다
     * @contents jaehyuk은 2개의 상품을 가지고 있다. jaehyuk을 검색했을 때 올바른 주문정보가 나오는지 테스트한다
     */
    @DisplayName("유저의 id로 주문 찾기 with paging x : id : 1, page : 1, size : 1")
    @Test
    void getOrdersByUserId() throws Exception {
        String userId = "jaehyuk";
        int page = 1;
        int size = 1;

        List<OrderEntity> list = new ArrayList<>();

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(112048);
        orderEntity.setUserId(userId);
        orderEntity.setQuantity("5");
        orderEntity.setPrice("120000");
        orderEntity.setPaymentMethod("신용카드");

        OrderEntity orderEntity2 = new OrderEntity();
        orderEntity2.setId(1346241);
        orderEntity2.setUserId(userId);
        orderEntity2.setQuantity("7");
        orderEntity2.setPrice("150000");
        orderEntity2.setPaymentMethod("현금");

        list.add(orderEntity);
        list.add(orderEntity2);
        Page<OrderEntity> pages = new PageImpl<>(list);

        BDDMockito.given(orderService.getOrdersByUserId(userId,page,size)).willReturn(pages);

        ResultActions result = mockMvc.perform(get("/order/userid/jaehyuk/1/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.content").isArray())
                .andExpect(jsonPath("$.data.content[0].id", is(112048)))
                .andExpect(jsonPath("$.data.content[0].userId", is("jaehyuk")))
                .andExpect(jsonPath("$.data.content[0].quantity", is("5")))
                .andExpect(jsonPath("$.data.content[0].price", is("120000")))
                .andExpect(jsonPath("$.data.content[0].paymentMethod", is("신용카드")))
                .andExpect(jsonPath("$.data.content[1].id", is(1346241)))
                .andExpect(jsonPath("$.data.content[1].userId", is("jaehyuk")))
                .andExpect(jsonPath("$.data.content[1].quantity", is("7")))
                .andExpect(jsonPath("$.data.content[1].price", is("150000")))
                .andExpect(jsonPath("$.data.content[1].paymentMethod", is("현금")));
    }

    /**
     * get orders all
     * @throws Exception
     *
     * Test Content **********
     * 유저 아이디를 입력하면 해당 구매자의 모든 주문 정보를 출력합니다
     * 아이디가 jaehyuk인 사용자가 가진 OrderEntity 정보를 테스트합니다
     * 테스트할 OrderEntity의 일부 내용은 다음과 같습니다
     * @contents jaehyuk을 검색했을 때 올바른 리스트가 나오는지 테스트한다
     */
    @DisplayName("해당 구매자의 모든 주문 정보 : userId : jaehyuk")
    @Test
    void getOrders() throws Exception {
        String userId = "jaehyuk";

        List<OrderEntity> list = new ArrayList<>();

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(112048);
        orderEntity.setUserId(userId);
        orderEntity.setQuantity("5");
        orderEntity.setPrice("120000");
        orderEntity.setPaymentMethod("신용카드");

        OrderEntity orderEntity2 = new OrderEntity();
        orderEntity2.setId(1346241);
        orderEntity2.setUserId(userId);
        orderEntity2.setQuantity("7");
        orderEntity2.setPrice("150000");
        orderEntity2.setPaymentMethod("현금");

        list.add(orderEntity);
        list.add(orderEntity2);

        BDDMockito.given(orderService.getOrdersByUserId(userId)).willReturn(list);

        ResultActions result = mockMvc.perform(get("/order/userid/jaehyuk/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data.[0].id", is(112048)))
                .andExpect(jsonPath("$.data.[0].userId", is("jaehyuk")))
                .andExpect(jsonPath("$.data.[0].quantity", is("5")))
                .andExpect(jsonPath("$.data.[0].price", is("120000")))
                .andExpect(jsonPath("$.data.[0].paymentMethod", is("신용카드")))
                .andExpect(jsonPath("$.data.[1].id", is(1346241)))
                .andExpect(jsonPath("$.data.[1].userId", is("jaehyuk")))
                .andExpect(jsonPath("$.data.[1].quantity", is("7")))
                .andExpect(jsonPath("$.data.[1].price", is("150000")))
                .andExpect(jsonPath("$.data.[1].paymentMethod", is("현금")));
    }

    /**
     * get orders by user id by status
     * @throws Exception
     *
     * Test Content **********
     * 유저 아이디와 상태(SHIPPING)를 입력하면 해당 구매자의 모든 주문 정보를 출력합니다
     * 아이디가 jaehyuk인 사용자가 가진 OrderEntity 정보를 테스트합니다
     * 테스트할 OrderEntity의 일부 내용은 다음과 같습니다
     * @contents jaehyuk과 status를 검색했을 때 올바른 리스트가 나오는지 테스트한다
     */
    @DisplayName("구매자의 주문 상태별 조회 : userid : jaehyuk, status : 2")
    @Test
    void getOrdersByUserIdByStatus() throws Exception {

        String userId = "jaehyuk";
        int status = 2;

        List<OrderEntity> list = new ArrayList<>();

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(112048);
        orderEntity.setUserId(userId);
        orderEntity.setQuantity("5");
        orderEntity.setPrice("120000");
        orderEntity.setPaymentMethod("신용카드");

        list.add(orderEntity);

        BDDMockito.given(orderService.getOrdersByUserIdByStatus(userId, status)).willReturn(list);

        ResultActions result = mockMvc.perform(get("/order/userid/jaehyuk/status/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data.[0].id", is(112048)))
                .andExpect(jsonPath("$.data.[0].userId", is("jaehyuk")))
                .andExpect(jsonPath("$.data.[0].quantity", is("5")))
                .andExpect(jsonPath("$.data.[0].price", is("120000")))
                .andExpect(jsonPath("$.data.[0].paymentMethod", is("신용카드")));
    }

    /**
     * save orders
     * @throws Exception
     *
     * Test Content **********
     * 유저 정보를 body에 담아 해당 구매자의  주문 정보를 저장합니다
     * @contents POST
     */
    @DisplayName("구매자의 주문 데이터 저장 : OrderEntity : orderEntity")
    @Test
    void saveOrders() throws Exception {

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(112048);
        orderEntity.setUserId("jaehyuk");
        orderEntity.setQuantity("5");
        orderEntity.setPrice("120000");
        orderEntity.setPaymentMethod("신용카드");

        Gson gson = new Gson();
        String content = gson.toJson(orderEntity);

        ResultActions result = mockMvc.perform(post("/order")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray());
    }

    /**
     * update orders
     * @throws Exception
     *
     * Test Content **********
     * 유저 정보를 body에 담아 해당 구매자의  주문 상세 정보를 수정합니다
     * @contents POST
     */
    @DisplayName("구매자의 주문 데이터 수정")
    @Test
    void updateOrders() throws Exception {

        String userId = "jaehyuk";

        OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
        orderDetailEntity.setCancelMsg("취소했습니다");
        orderDetailEntity.setOrderEntity(new OrderEntity());

        Gson gson = new Gson();
        String content = gson.toJson(orderDetailEntity);

        ResultActions result = mockMvc.perform(put("/order/update/user/jaehyuk")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andDo(print())
                .andExpect(status().isOk());
    }

    /**
     * get steady seller
     * @throws Exception
     *
     * Test Content **********
     * 스테디 셀러 목록의 상품 id list를 얻어옵니다
     * @contents 스테디 셀러 상품 id list 출력 [112048, 132352, 625123, 12557, 7455, 4357, 5677, 1111, 3344, 1010]
     */
    @DisplayName("최근 한달간 주문이 가장 많았던 상품 top10")
    @Test
    void steadySeller() throws Exception {

        List<Integer> list = new ArrayList<>();
        list.add(112048);
        list.add(132352);
        list.add(625123);
        list.add(12557);
        list.add(7455);
        list.add(4357);
        list.add(5677);
        list.add(1111);
        list.add(3344);
        list.add(1010);

        BDDMockito.given(orderService.getSteadySeller()).willReturn(list);

        ResultActions result = mockMvc.perform(get("/order/recommend/steadyseller"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data.[0]", is(112048)))
                .andExpect(jsonPath("$.data.[1]", is(132352)))
                .andExpect(jsonPath("$.data.[2]", is(625123)))
                .andExpect(jsonPath("$.data.[3]", is(12557)))
                .andExpect(jsonPath("$.data.[4]", is(7455)))
                .andExpect(jsonPath("$.data.[5]", is(4357)))
                .andExpect(jsonPath("$.data.[6]", is(5677)))
                .andExpect(jsonPath("$.data.[7]", is(1111)))
                .andExpect(jsonPath("$.data.[8]", is(3344)))
                .andExpect(jsonPath("$.data.[9]", is(1010)));
    }

    /**
     * hot product
     * @throws Exception
     *
     * Test Content **********
     * hot products 10개를 반환합니다
     * @contents 1주일간 주문이 가장 많았던 상품 10개의 id list 출력 [112048, 132352, 625123, 12557, 7455, 4357, 5677, 1111, 3344, 1010]
     */
    @DisplayName("최근 일주일간 주문이 가장 많았던 상품 top10")
    @Test
    void hotProduct() throws Exception {

        List<Integer> list = new ArrayList<>();
        list.add(112048);
        list.add(132352);
        list.add(625123);
        list.add(12557);
        list.add(7455);
        list.add(4357);
        list.add(5677);
        list.add(1111);
        list.add(3344);
        list.add(1010);

        BDDMockito.given(orderService.getHotProduct()).willReturn(list);

        ResultActions result = mockMvc.perform(get("/order/recommend/hotproduct"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data.[0]", is(112048)))
                .andExpect(jsonPath("$.data.[1]", is(132352)))
                .andExpect(jsonPath("$.data.[2]", is(625123)))
                .andExpect(jsonPath("$.data.[3]", is(12557)))
                .andExpect(jsonPath("$.data.[4]", is(7455)))
                .andExpect(jsonPath("$.data.[5]", is(4357)))
                .andExpect(jsonPath("$.data.[6]", is(5677)))
                .andExpect(jsonPath("$.data.[7]", is(1111)))
                .andExpect(jsonPath("$.data.[8]", is(3344)))
                .andExpect(jsonPath("$.data.[9]", is(1010)));
    }

    /**
     * today hot
     * @throws Exception
     *
     * Test Content **********
     * today hot 6개를 반환합니다
     * @contents 24시간동안 주문이 가장 많았던 상품 6개의 id list 출력 [112048, 132352, 625123, 12557, 7455, 4357]
     */
    @DisplayName("최근 24시간 동안 주문이 가장 많았던 상품 top6")
    @Test
    void todayHot() throws Exception {

        List<Map<String,Object>> list = new ArrayList<>();

        Map<String,Object> map = new HashMap<>();
        map.put("112048",11);
        map.put("132352",10);
        map.put("625123",9);
        map.put("12557",8);
        map.put("7455",7);
        map.put("4357",6);

        list.add(map);

        BDDMockito.given(orderService.getTodayHot()).willReturn(list);

        ResultActions result = mockMvc.perform(get("/order/recommend/todayhot"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data.[0].112048", is(11)))
                .andExpect(jsonPath("$.data.[0].132352", is(10)))
                .andExpect(jsonPath("$.data.[0].625123", is(9)))
                .andExpect(jsonPath("$.data.[0].12557", is(8)))
                .andExpect(jsonPath("$.data.[0].7455", is(7)))
                .andExpect(jsonPath("$.data.[0].4357", is(6)));
    }
}