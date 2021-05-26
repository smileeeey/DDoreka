package com.eureka.order.service;

import com.eureka.order.Entity.OrderDetailEntity;
import com.eureka.order.Entity.OrderEntity;
import com.eureka.order.Repository.OrderDetailRepositoty;
import com.eureka.order.Repository.OrderRepositoty;
import com.eureka.order.service.impl.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * test for order from service
 * @author tested by shimjaehyuk, leesumin
 * @since 1.0
 */
@DisplayName("오더 서비스 테스트")
class OrderServiceTest {

    OrderService orderService;
    @Mock
    private OrderRepositoty orderRepositoty;
    @Mock
    private OrderDetailRepositoty orderDetailRepositoty;
    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        //orderService = new OrderServiceImpl(orderRepositoty, orderDetailRepositoty);
    }

    /**
     * get orders by user id
     * @throws Exception
     *
     * Test Content **********
     * @contents user id를 service에 입력하면 List Order Entity가 출력된다
     */
    @Test
    @DisplayName("주문 조회 by user id")
    void getOrdersByUserId() {
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

        BDDMockito.given(orderRepositoty.findAllByUserId(userId)).willReturn(list);

        List<OrderEntity> responseOrderEntity = orderService.getOrdersByUserId(userId);
        Assertions.assertThat(responseOrderEntity.get(0).getId()).isEqualTo(orderEntity.getId());
        Assertions.assertThat(responseOrderEntity.get(0).getUserId()).isEqualTo(orderEntity.getUserId());
        Assertions.assertThat(responseOrderEntity.get(0).getQuantity()).isEqualTo(orderEntity.getQuantity());
        Assertions.assertThat(responseOrderEntity.get(0).getPrice()).isEqualTo(orderEntity.getPrice());
        Assertions.assertThat(responseOrderEntity.get(0).getPaymentMethod()).isEqualTo(orderEntity.getPaymentMethod());

        Assertions.assertThat(responseOrderEntity.get(1).getId()).isEqualTo(orderEntity2.getId());
        Assertions.assertThat(responseOrderEntity.get(1).getUserId()).isEqualTo(orderEntity2.getUserId());
        Assertions.assertThat(responseOrderEntity.get(1).getQuantity()).isEqualTo(orderEntity2.getQuantity());
        Assertions.assertThat(responseOrderEntity.get(1).getPrice()).isEqualTo(orderEntity2.getPrice());
        Assertions.assertThat(responseOrderEntity.get(1).getPaymentMethod()).isEqualTo(orderEntity2.getPaymentMethod());
    }

    /**
     * get orders by user id
     * @throws Exception
     *
     * Test Content **********
     * @contents orderEntity를 service에 입력하면 삽입되는 동작을 확인하는 테스트
     */
    @Test
    @DisplayName("주문 수정")
    void updateOrder() {
        OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
        orderDetailEntity.setOrderId(112048);
        orderDetailEntity.setCourier("courier");
        orderDetailEntity.setCancelMsg("canceled...");

        orderService.updateOrder(orderDetailEntity);
        Mockito.verify(orderDetailRepositoty).save(orderDetailEntity);
    }

    /**
     * get orders by product id sorted by day
     * @throws Exception
     *
     * Test Content **********
     * @contents product id로 1주일간 판매 수량을 테스트한다 [5,7,2,3,1,7,0]
     */
    @Test
    @DisplayName("주문 상품들의 총 수량 테스트")
    void getOrdersByProductIdSortedByDay() {


        OrderEntity orderEntity10 = new OrderEntity();
        orderEntity10.setQuantity("5");
        OrderEntity orderEntity20 = new OrderEntity();
        orderEntity20.setQuantity("7");
        OrderEntity orderEntity30 = new OrderEntity();
        orderEntity30.setQuantity("2");
        OrderEntity orderEntity40 = new OrderEntity();
        orderEntity40.setQuantity("3");
        OrderEntity orderEntity50 = new OrderEntity();
        orderEntity50.setQuantity("1");
        OrderEntity orderEntity60 = new OrderEntity();
        orderEntity60.setQuantity("7");
        OrderEntity orderEntity70 = new OrderEntity();
        orderEntity70.setQuantity("0");

        OrderEntity orderEntity11 = new OrderEntity();
        orderEntity11.setQuantity("5");
        OrderEntity orderEntity21 = new OrderEntity();
        orderEntity21.setQuantity("7");
        OrderEntity orderEntity31 = new OrderEntity();
        orderEntity31.setQuantity("2");
        OrderEntity orderEntity41 = new OrderEntity();
        orderEntity41.setQuantity("3");
        OrderEntity orderEntity51 = new OrderEntity();
        orderEntity51.setQuantity("1");
        OrderEntity orderEntity61 = new OrderEntity();
        orderEntity61.setQuantity("7");
        OrderEntity orderEntity71 = new OrderEntity();
        orderEntity71.setQuantity("0");

        OrderEntity orderEntity13 = new OrderEntity();
        orderEntity13.setQuantity("5");
        OrderEntity orderEntity23 = new OrderEntity();
        orderEntity23.setQuantity("7");
        OrderEntity orderEntity33 = new OrderEntity();
        orderEntity33.setQuantity("2");
        OrderEntity orderEntity43 = new OrderEntity();
        orderEntity43.setQuantity("3");
        OrderEntity orderEntity53 = new OrderEntity();
        orderEntity53.setQuantity("1");
        OrderEntity orderEntity63 = new OrderEntity();
        orderEntity63.setQuantity("7");
        OrderEntity orderEntity73 = new OrderEntity();
        orderEntity73.setQuantity("0");

        List<OrderEntity> list1 = new ArrayList<>();
        list1.add(orderEntity10);
        list1.add(orderEntity11);
        list1.add(orderEntity13);

        List<OrderEntity> list2 = new ArrayList<>();
        list1.add(orderEntity20);
        list1.add(orderEntity21);
        list1.add(orderEntity23);

        List<OrderEntity> list3 = new ArrayList<>();
        list1.add(orderEntity30);
        list1.add(orderEntity31);
        list1.add(orderEntity33);

        BDDMockito.given(orderRepositoty.findAllByDayOfWeek(Integer.toString(1),"1122")).willReturn(list1);
        BDDMockito.given(orderRepositoty.findAllByDayOfWeek(Integer.toString(2),"2233")).willReturn(list2);
        BDDMockito.given(orderRepositoty.findAllByDayOfWeek(Integer.toString(3),"3344")).willReturn(list3);

        List<Integer> answers = orderService.getOrdersByProductIdSortedByDay("1122");
        List<Integer> answers2 = orderService.getOrdersByProductIdSortedByDay("2233");
        List<Integer> answers3 = orderService.getOrdersByProductIdSortedByDay("3344");

        Assertions.assertThat(answers.get(0)).isEqualTo(42);
        Assertions.assertThat(answers2.get(1)).isEqualTo(0);
        Assertions.assertThat(answers3.get(2)).isEqualTo(0);
    }

    /**
     * get steady seller
     * @throws Exception
     *
     * Test Content **********
     * @contents 스테디 셀러 상품 id list 출력 [112048, 132352, 625123, 12557, 7455, 4357, 5677, 1111, 3344, 1010]
     */
    @Test
    @DisplayName("스테디 셀러 테스트")
    void getSteadySeller() {

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

        BDDMockito.given(orderRepositoty.findSteadySeller()).willReturn(list);
        List<Integer> answers = orderService.getSteadySeller();
        Assertions.assertThat(answers.get(0)).isEqualTo(list.get(0));
        Assertions.assertThat(answers.get(1)).isEqualTo(list.get(1));
        Assertions.assertThat(answers.get(2)).isEqualTo(list.get(2));
        Assertions.assertThat(answers.get(3)).isEqualTo(list.get(3));
        Assertions.assertThat(answers.get(4)).isEqualTo(list.get(4));
        Assertions.assertThat(answers.get(5)).isEqualTo(list.get(5));
        Assertions.assertThat(answers.get(6)).isEqualTo(list.get(6));
        Assertions.assertThat(answers.get(7)).isEqualTo(list.get(7));
        Assertions.assertThat(answers.get(8)).isEqualTo(list.get(8));
        Assertions.assertThat(answers.get(9)).isEqualTo(list.get(9));
    }

    /**
     * get hot product
     * @throws Exception
     *
     * Test Content **********
     * @contents 1주일간 주문이 가장 많았던 상품 10개의 id list 출력 [112048, 132352, 625123, 12557, 7455, 4357, 5677, 1111, 3344, 1010]
     */
    @Test
    void getHotProduct() {
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

        BDDMockito.given(orderRepositoty.findHotProduct()).willReturn(list);
        List<Integer> answers = orderService.getHotProduct();
        Assertions.assertThat(answers.get(0)).isEqualTo(list.get(0));
        Assertions.assertThat(answers.get(1)).isEqualTo(list.get(1));
        Assertions.assertThat(answers.get(2)).isEqualTo(list.get(2));
        Assertions.assertThat(answers.get(3)).isEqualTo(list.get(3));
        Assertions.assertThat(answers.get(4)).isEqualTo(list.get(4));
        Assertions.assertThat(answers.get(5)).isEqualTo(list.get(5));
        Assertions.assertThat(answers.get(6)).isEqualTo(list.get(6));
        Assertions.assertThat(answers.get(7)).isEqualTo(list.get(7));
        Assertions.assertThat(answers.get(8)).isEqualTo(list.get(8));
        Assertions.assertThat(answers.get(9)).isEqualTo(list.get(9));
    }

    /**
     * get today hoy
     * @throws Exception
     *
     * Test Content **********
     * @contents 24시간동안 주문이 가장 많았던 상품 6개의 id list 출력 [112048, 132352, 625123, 12557, 7455, 4357]
     */
    @Test
    void getTodayHot() {

        List<Map<String,Object>> list = new ArrayList<>();

        Map<String,Object> map = new HashMap<>();
        map.put("112048",11);
        map.put("132352",10);
        map.put("625123",9);
        map.put("12557",8);
        map.put("7455",7);
        map.put("4357",6);
        list.add(map);

        BDDMockito.given(orderRepositoty.findTodayHot()).willReturn(list);
        List<Map<String,Object>> answers = orderService.getTodayHot();
        Assertions.assertThat(answers.get(0).get("112048")).isEqualTo(list.get(0).get("112048"));
        Assertions.assertThat(answers.get(0).get("132352")).isEqualTo(list.get(0).get("132352"));
        Assertions.assertThat(answers.get(0).get("625123")).isEqualTo(list.get(0).get("625123"));
        Assertions.assertThat(answers.get(0).get("12557")).isEqualTo(list.get(0).get("12557"));
        Assertions.assertThat(answers.get(0).get("7455")).isEqualTo(list.get(0).get("7455"));
        Assertions.assertThat(answers.get(0).get("4357")).isEqualTo(list.get(0).get("4357"));
    }
}