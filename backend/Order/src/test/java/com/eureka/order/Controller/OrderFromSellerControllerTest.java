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
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

/**
 * test for order from seller controller
 * @author tested by shimjaehyuk, leesumin
 * @since 1.0
 */
@WebMvcTest(OrderFromSellerController.class)
@DisplayName("셀러를 이용한 주문 통합 테스트")
class OrderFromSellerControllerTest {

    private MockMvc mockMvc;
    @Autowired
    public void setMockMvc(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }
    @MockBean
    OrderService orderService;

    /**
     * get orders by seller id
     * @throws Exception
     *
     * Test Content **********
     * 셀러 아이디를 입력하면 셀러가 가진 정보를 출력합니다.
     * 아이디가 jaehyuk인 판매자가 가진 OrderEntity 정보를 테스트합니다
     * 테스트할 OrderEntity의 일부 내용은 다음과 같습니다
     * @contents jaehyuk은 2개의 상품을 가지고 있다. jaehyuk을 검색했을 때 올바른 상품이 나오는지 테스트한다
     */
    @DisplayName("판매자의 id로 주문 찾기 : id : jaehyuk, page : 1, size : 1")
    @Test
    void getOrdersBySellerId() throws Exception {
        String sellerId = "jaehyuk";
        int page = 1;
        int size = 1;
        Date date = new Date();

        List<OrderEntity> list = new ArrayList<>();

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(112048);
        orderEntity.setSellerId(sellerId);
        orderEntity.setQuantity("5");
        orderEntity.setPrice("120000");
        orderEntity.setPaymentMethod("신용카드");

        OrderEntity orderEntity2 = new OrderEntity();
        orderEntity2.setId(1346241);
        orderEntity2.setSellerId(sellerId);
        orderEntity2.setQuantity("7");
        orderEntity2.setPrice("150000");
        orderEntity2.setPaymentMethod("현금");

        list.add(orderEntity);
        list.add(orderEntity2);
        Page<OrderEntity> pages = new PageImpl<>(list);

        BDDMockito.given(orderService.getOrdersBySellerId(sellerId, page, size)).willReturn(pages);

        ResultActions result = mockMvc.perform(get("/order/sellerid/jaehyuk/1/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.content").isArray())
                .andExpect(jsonPath("$.data.content[0].id", is(112048)))
                .andExpect(jsonPath("$.data.content[0].sellerId", is("jaehyuk")))
                .andExpect(jsonPath("$.data.content[0].quantity", is("5")))
                .andExpect(jsonPath("$.data.content[0].price", is("120000")))
                .andExpect(jsonPath("$.data.content[0].paymentMethod",is("신용카드")))
                .andExpect(jsonPath("$.data.content[1].id", is(1346241)))
                .andExpect(jsonPath("$.data.content[1].sellerId", is("jaehyuk")))
                .andExpect(jsonPath("$.data.content[1].quantity", is("7")))
                .andExpect(jsonPath("$.data.content[1].price", is("150000")))
                .andExpect(jsonPath("$.data.content[1].paymentMethod",is("현금")));
    }

    /**
     * get orders by seller id with unchecked
     * @throws Exception
     *
     * Test Content **********
     * 셀러 아이디를 입력하면 셀러가 가진 정보를 출력합니다. 단, 아직 확정되지 않은 데이터만 제공합니다
     * 아이디가 jaehyuk인 판매자가 가진 OrderEntity 정보를 테스트합니다
     * 테스트할 OrderEntity의 일부 내용은 다음과 같습니다
     * @contents jaehyuk은 확인 안된 1개의 상품을 가지고 있다. jaehyuk을 검색했을 때 올바른 상품이 나오는지 테스트한다
     */
    @DisplayName("해당 판매자가 아직 주문확인 하지 않은 주문 찾기 : sellerId : jaehyuk")
    @Test
    void getOrdersBySellerIdWithUnchecked() throws Exception {
        String sellerId = "jaehyuk";

        List<OrderEntity> list = new ArrayList<>();

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(112048);
        orderEntity.setSellerId(sellerId);
        orderEntity.setQuantity("5");
        orderEntity.setPrice("120000");
        orderEntity.setPaymentMethod("신용카드");

        list.add(orderEntity);

        BDDMockito.given(orderService.getOrdersBySellerIdWithUnchecked(sellerId)).willReturn(list);

        ResultActions result = mockMvc.perform(get("/order/sellerid/jaehyuk/unchecked"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data[0].id", is(112048)))
                .andExpect(jsonPath("$.data[0].sellerId", is("jaehyuk")))
                .andExpect(jsonPath("$.data[0].quantity", is("5")))
                .andExpect(jsonPath("$.data[0].price", is("120000")))
                .andExpect(jsonPath("$.data[0].paymentMethod",is("신용카드")));
    }

    /**
     * get orders by product id sorted by day of week
     * @throws Exception
     *
     * Test Content **********
     * 프로덕트 아이디를 입력하면 상품의 주간 판매량(수량)이 나옵니다.
     * 프로덕트 아이디가 112048일 때 주간 판매량이 나옵니다.
     * @contents 112048 상품은 다음과 같이 판매되었다 [1,5,0,2,54,1249,7]
     */
    @DisplayName("상품의 주간 요일별 판매량(일요일 :1 ~)")
    @Test
    void getOrdersByProductIdSortedByDayofWeek() throws Exception {
        String productId = "112048";
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(5);
        list.add(0);
        list.add(2);
        list.add(54);
        list.add(1249);
        list.add(7);

        BDDMockito.given(orderService.getOrdersByProductIdSortedByDay(productId)).willReturn(list);

        ResultActions result = mockMvc.perform(get("/order/product/112048/dayofweek"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data.[0]", is(1)))
                .andExpect(jsonPath("$.data.[1]", is(5)))
                .andExpect(jsonPath("$.data.[2]", is(0)))
                .andExpect(jsonPath("$.data.[3]", is(2)))
                .andExpect(jsonPath("$.data.[4]", is(54)))
                .andExpect(jsonPath("$.data.[5]", is(1249)))
                .andExpect(jsonPath("$.data.[6]", is(7)));
    }

    /**
     * get orders by seller id sorted by day
     * @throws Exception
     *
     * Test Content **********
     * 판매자 아이디와 달을 입력하면 주문 정보가 나옵니다.
     * 판매자 아이디가 jaehyuk, 해당 달이 8월일 때 해당 주문 정보들이 나옵니다.
     * @contents jaehyuk, 8은 List of OrderEntity가 나옵니다
     */
    @DisplayName("상품의 월간 일별 판매정보 : sellerid : jaehyuk, month : 1")
    @Test
    void getOrdersBySellerIdByday() throws Exception {
        String sellerId = "jaehyuk";
        String month = "8";

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(112048);
        orderEntity.setSellerId(sellerId);
        orderEntity.setQuantity("5");
        orderEntity.setPrice("120000");
        orderEntity.setPaymentMethod("신용카드");

        OrderEntity orderEntity2 = new OrderEntity();
        orderEntity2.setId(1346241);
        orderEntity2.setSellerId(sellerId);
        orderEntity2.setQuantity("7");
        orderEntity2.setPrice("150000");
        orderEntity2.setPaymentMethod("현금");

        List<OrderEntity> list = new ArrayList<>();
        list.add(orderEntity);
        list.add(orderEntity2);

        List<List<OrderEntity>> motherList = new ArrayList<>();
        motherList.add(list);

        BDDMockito.given(orderService.getOrdersBySellerIdByDay(sellerId, month)).willReturn(motherList);

        ResultActions result = mockMvc.perform(get("/order/sellerid/jaehyuk/8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data.[0].[0].id", is(112048)))
                .andExpect(jsonPath("$.data.[0].[0].sellerId", is("jaehyuk")))
                .andExpect(jsonPath("$.data.[0].[0].quantity", is("5")))
                .andExpect(jsonPath("$.data.[0].[0].price", is("120000")))
                .andExpect(jsonPath("$.data.[0].[0].paymentMethod", is("신용카드")))
                .andExpect(jsonPath("$.data.[0].[1].id", is(1346241)))
                .andExpect(jsonPath("$.data.[0].[1].sellerId", is("jaehyuk")))
                .andExpect(jsonPath("$.data.[0].[1].quantity", is("7")))
                .andExpect(jsonPath("$.data.[0].[1].price", is("150000")))
                .andExpect(jsonPath("$.data.[0].[1].paymentMethod", is("현금")));
    }

    /**
     * get orders by seller id by status
     * @throws Exception
     *
     * Test Content **********
     * 판매자 아이디와 상태(SHIPPING)를 입력하면 주문 정보가 나옵니다.
     * 판매자 아이디가 jaehyuk, 상태가 2일 때 해당 주문 정보들이 나옵니다.
     * @contents jaehyuk, 2은 List of OrderEntity가 나옵니다
     */
    @DisplayName("주문 상태별 조회 : sellerid : jaehyuk, status : 1")
    @Test
    void getOrdersBySellerIdBystatus() throws Exception {
        String sellerId = "jaehyuk";
        int status = 2;

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(112048);
        orderEntity.setSellerId(sellerId);
        orderEntity.setQuantity("5");
        orderEntity.setPrice("120000");
        orderEntity.setPaymentMethod("신용카드");

        List<OrderEntity> list = new ArrayList<>();
        list.add(orderEntity);

        BDDMockito.given(orderService.getOrdersBySellerIdByStatus(sellerId, status)).willReturn(list);

        ResultActions result = mockMvc.perform(get("/order/sellerid/jaehyuk/status/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data.[0].id", is(112048)))
                .andExpect(jsonPath("$.data.[0].sellerId", is("jaehyuk")))
                .andExpect(jsonPath("$.data.[0].quantity", is("5")))
                .andExpect(jsonPath("$.data.[0].price", is("120000")))
                .andExpect(jsonPath("$.data.[0].paymentMethod", is("신용카드")));
    }

}