package com.eureka.order.service;

import com.eureka.order.Entity.OrderDetailEntity;
import com.eureka.order.Entity.OrderEntity;
import com.eureka.order.dto.Order;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * order service
 * @author refactored by shimjaehyuk, leesumin
 * @since 1.0
 */
public interface OrderService {

    /**
     * get orders by user id
     * @param userId
     * @return list of order entity
     */
    List<OrderEntity> getOrdersByUserId(String userId);

    /**
     * update order
     * @param orderDetailEntity
     */
    void updateOrder(OrderDetailEntity orderDetailEntity);

    /**
     * get list of order count by product id sorted by day
     * @param productId
     * @return list of count
     */
    List<Integer> getOrdersByProductIdSortedByDay(String productId);

    /**
     * get orders by user id
     * @param userId
     * @param page
     * @param size
     * @return page of order
     */
    Page<OrderEntity> getOrdersByUserId(String userId, Integer page, Integer size);

    /**
     * get orders by seller id
     * @deprecated NOT USED
     * @param sellerId
     * @return list of order
     */
    List<OrderEntity> getOrdersBySellerId(String sellerId);


    /**
     * get orders by seller id
     * @param sellerId
     * @param page
     * @param size
     * @return page of order
     */
    Page<OrderEntity> getOrdersBySellerId(String sellerId, Integer page, Integer size);

    /**
     * get orders by seller id with unchecked
     * @param sellerId
     * @return List<OrderEntity>
     */
    List<OrderEntity> getOrdersBySellerIdWithUnchecked(String sellerId);

    /**
     * save order
     * @param orderEntity
     */
    void saveOrder(OrderEntity orderEntity);

    /**
     * get orders by seller id in last month
     * @param sellerId
     * @param month
     * @return list of list of order
     */
    List<List<OrderEntity>> getOrdersBySellerIdByDay(String sellerId, String month);

    /**
     * get list of order by seller id, status
     * @param sellerId
     * @param status
     * @return list of order
     */
    List<OrderEntity> getOrdersBySellerIdByStatus(String sellerId, Integer status);

    /**
     * get orders by user id, status
     * @param userId
     * @param status
     * @return list of order entity
     */
    List<OrderEntity> getOrdersByUserIdByStatus(String userId, Integer status);

    /**
     * get steady seller
     * @return List<Integer>
     */
    List<Integer> getSteadySeller();

    /**
     * get hot product
     * @return List<Integer>
     */
    List<Integer> getHotProduct();

    /**
     * get today hoy
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> getTodayHot();

    /**
     * get today hot products
     * @return List<String>
     */
    List<String> getTodayHotProducts();

    /**
     * get today hot products
     * @return List<String>
     */
    List<String> getWeekHotProducts();

    /**
     * get today hot products
     * @return List<String>
     */
    List<String> getMonthHotProducts();
}
