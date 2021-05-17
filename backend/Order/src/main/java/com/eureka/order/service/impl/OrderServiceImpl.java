package com.eureka.order.service.impl;

import com.eureka.order.Entity.OrderDetailEntity;
import com.eureka.order.Entity.OrderEntity;
import com.eureka.order.Repository.OrderDetailRepositoty;
import com.eureka.order.Repository.OrderRepositoty;
import com.eureka.order.dto.Order;
import com.eureka.order.service.OrderService;
import com.eureka.order.util.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * order serviceimpl
 * @author refactored by shimjaehyuk, leesumin
 * @since 1.0
 */
@Service
public class OrderServiceImpl implements OrderService {

    /**
     * OrderRepository Bean created by Spring Boot constructor injection
     * OrderDetailRepository Bean created by Spring Boot constructor injection
     */
    private  OrderRepositoty orderRepositoty;
    private  OrderDetailRepositoty orderDetailRepositoty;
    @Autowired
    public OrderServiceImpl(OrderRepositoty orderRepositoty, OrderDetailRepositoty orderDetailRepositoty) {
        this.orderRepositoty = orderRepositoty;
        this.orderDetailRepositoty = orderDetailRepositoty;
    }

    /**
     * get orders by user id
     * @param userId
     * @return list of order entity
     */
    @Override
    public List<OrderEntity> getOrdersByUserId(String userId) {
        return orderRepositoty.findAllByUserId(userId);
    }

    /**
     * update order
     * @param orderDetailEntity
     */
    @Override
    public void updateOrder(OrderDetailEntity orderDetailEntity) {
        orderDetailRepositoty.save(orderDetailEntity);
    }

    /**
     * get list of order count by product id sorted by day
     * @param productId
     * @return list of count
     */
    @Override
    public List<Integer> getOrdersByProductIdSortedByDay(String productId) {
        List<Integer> ls = new ArrayList<>();
        for(int i=1;i<8;i++){
            int cnt=0;
            for(OrderEntity orderEntity : orderRepositoty.findAllByDayOfWeek(Integer.toString(i),productId) ){
                cnt+=Integer.parseInt(orderEntity.getQuantity().trim());
            }
            ls.add(cnt);
        }
        return ls;
    }

    /**
     * get orders by seller id in last month
     * @param sellerId
     * @param month
     * @return list of list of order
     */
    @Override
    public List<List<OrderEntity>> getOrdersBySellerIdByDay(String sellerId, String month) {
        List<List<OrderEntity>> ls = new ArrayList<>();
        for(int i=1;i<31;i++){
            ls.add(orderRepositoty.findAllBySelleridofday(Integer.toString(i),sellerId,month));
        }
        return ls;
    }

    /**
     * get list of order by seller id, status
     * @param sellerId
     * @param status
     * @return list of order
     */
    @Override
    public List<OrderEntity> getOrdersBySellerIdByStatus(String sellerId, Integer status) {
        OrderStatus orderStatus=OrderStatus.valueOf(status);
        List<OrderDetailEntity> orderDetailEntityList=orderDetailRepositoty.findAllByOrderStatus(orderStatus);
        List<OrderEntity> orderEntityList=new ArrayList<>();
        for(OrderDetailEntity o:orderDetailEntityList){
            if(o.getOrderEntity().getSellerId().equals(sellerId)) orderEntityList.add(o.getOrderEntity());
        }
        return orderEntityList;
    }

    /**
     * get orders by user id, status
     * @param userId
     * @param status
     * @return list of order entity
     */
    @Override
    public List<OrderEntity> getOrdersByUserIdByStatus(String userId, Integer status) {
        OrderStatus orderStatus=OrderStatus.valueOf(status);
        List<OrderDetailEntity> orderDetailEntityList=orderDetailRepositoty.findAllByOrderStatus(orderStatus);
        List<OrderEntity> orderEntityList=new ArrayList<>();
        for(OrderDetailEntity o:orderDetailEntityList){

            if(o.getOrderEntity().getUserId().equals(userId)) orderEntityList.add(o.getOrderEntity());
        }
        return orderEntityList;
    }

    /**
     * get orders by user id
     * @param userId
     * @param page
     * @param size
     * @return page of order
     */
    @Override
    public Page<OrderEntity> getOrdersByUserId(String userId, Integer page, Integer size) {
        return orderRepositoty.findAllByUserId(userId,  PageRequest.of(page,size, Sort.by("id").descending()));
    }

    /**
     * @deprecated NOT USED
     * get orders by seller id
     * @param sellerId
     * @return list of order
     */
    @Override
    public List<OrderEntity> getOrdersBySellerId(String sellerId) {
        return orderRepositoty.findAllBySellerId(sellerId);
    }

    /**
     * get orders by seller id
     * @param sellerId
     * @param page
     * @param size
     * @return page of order
     */
    @Override
    public Page<OrderEntity> getOrdersBySellerId(String sellerId, Integer page, Integer size) {
        return orderRepositoty.findAllBySellerId(sellerId,PageRequest.of(page,size));
    }

    /**
     * get orders by seller id with unchecked
     * @param sellerId
     * @return List<OrderEntity>
     */
    @Override
    public List<OrderEntity> getOrdersBySellerIdWithUnchecked(String sellerId) {
        List<OrderDetailEntity> orderDetailEntityList=orderDetailRepositoty.findAllByCheckDatetime(null);
        List<OrderEntity> orderEntityList=new ArrayList<>();
        for(OrderDetailEntity o:orderDetailEntityList){
            if(o.getOrderEntity().getSellerId().equals(sellerId)) orderEntityList.add(o.getOrderEntity());
        }
        return orderEntityList;
    }

    /**
     * save order
     * @param orderEntity
     */
    @Override
    @Transactional
    public void saveOrder(OrderEntity orderEntity) {
        try {
            OrderEntity savedOrderEntity=orderRepositoty.save(orderEntity);
            OrderDetailEntity detail= new OrderDetailEntity();
            detail.setOrderId(savedOrderEntity.getId());
            detail.setOrderStatus(OrderStatus.PAY);
            orderDetailRepositoty.save(detail );
        }catch (Exception e){
            throw  e;
        }
    }

    /**
     * get steady seller
     * @return List<Integer>
     */
    @Override
    public List<Integer> getSteadySeller() {
        return orderRepositoty.findSteadySeller();
    }

    /**
     * get hot product
     * @return List<Integer>
     */
    @Override
    public List<Integer> getHotProduct() {
        return orderRepositoty.findHotProduct();
    }

    /**
     * get today hot
     * @return List<Map<String,Object>>
     */
    @Override
    public List<Map<String,Object>> getTodayHot() {
        return orderRepositoty.findTodayHot();
    }

    /**
     * get today hot products
     * @return List<String>
     */
    @Override
    public List<String> getTodayHotProducts() {
        return orderRepositoty.todayHotProducts();
    }

    /**
     * get weekHotProducts
     * @return List<String>
     */
    @Override
    public List<String> getWeekHotProducts() {
        return orderRepositoty.weekHotProducts();
    }

    /**
     * get monthHotProducts
     * @return List<String>
     */
    @Override
    public List<String> getMonthHotProducts() {
        return orderRepositoty.monthHotProducts();
    }
}
