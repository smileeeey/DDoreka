package com.eureka.order.service;

import com.eureka.order.Entity.OrderDetailEntity;
import com.eureka.order.Entity.OrderEntity;
import org.springframework.data.domain.Page;

import java.util.List;

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

    List<Integer> getOrdersBySellerIdBystatus(String sellerid, Integer status);

}
