package com.eureka.order.service;

import com.eureka.order.Entity.OrderDetailEntity;
import com.eureka.order.Entity.OrderEntity;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface OrderService {


    List<OrderEntity> getOrdersByUserId(String userId);

    Page<OrderEntity> getOrdersByUserId(String userId, Integer page, Integer size);

    List<OrderEntity> getOrdersBySellerId(String sellerid);

    Page<OrderEntity> getOrdersBySellerId(String sellerid, Integer page, Integer size);

    List<OrderEntity> getOrdersBySellerIdWithUnchecked(String sellerid);

    void saveOrder(OrderEntity orderEntity);

    void updateOrder(OrderDetailEntity orderDetailEntity);

    List<Integer> getOrdersByProductIdSortedByDay(String prodcutid);

    List<List<OrderEntity>> getOrdersBySellerIdByday(String sellerid, String month);

    List<OrderEntity> getOrdersBySellerIdBystatus(String sellerid, Integer status);

    List<OrderEntity> getOrdersByUserIdBystatus(String userid, Integer status);

    ////////////////////////   수민   /////////////////////////////

    List<Integer> getSteadySeller();

    List<Integer> getHotProduct();

    List<Map<String,Object>> getTodayHot();
}
