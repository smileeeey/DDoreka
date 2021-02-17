package com.eureka.order.service.impl;

import com.eureka.order.Entity.OrderDetailEntity;
import com.eureka.order.Entity.OrderEntity;
import com.eureka.order.Repository.OrderDetailRepositoty;
import com.eureka.order.Repository.OrderRepositoty;
import com.eureka.order.service.OrderService;
import com.eureka.order.util.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {


    private  final OrderRepositoty orderRepositoty;
    private  final OrderDetailRepositoty orderDetailRepositoty;
    public OrderServiceImpl(OrderRepositoty orderRepositoty, OrderDetailRepositoty orderDetailRepositoty) {
        this.orderRepositoty = orderRepositoty;
        this.orderDetailRepositoty = orderDetailRepositoty;
    }


    @Override
    public List<OrderEntity> getOrdersByUserId(String userId) {
        return orderRepositoty.findAllByUserId(userId);
    }

    @Override
    public void updateOrder(OrderDetailEntity orderDetailEntity) {
        orderDetailRepositoty.save(orderDetailEntity);
    }

    @Override
    public List<Integer> getOrdersByProductIdSortedByDay(String prodcutid) {
        List<Integer> ls = new ArrayList<>();
        for(int i=1;i<8;i++){
            int cnt=0;
            for(OrderEntity orderEntity : orderRepositoty.findAllByDayOfWeek(Integer.toString(i),prodcutid) ){
                cnt+=Integer.parseInt(orderEntity.getQuantity().trim());
            }
            ls.add(cnt);
        }
        return ls;
    }

    @Override
    public List<List<OrderEntity>> getOrdersBySellerIdByday(String sellerid, String month) {
        List<List<OrderEntity>> ls = new ArrayList<>();
        for(int i=1;i<31;i++){
            ls.add(orderRepositoty.findAllBySelleridofday(Integer.toString(i),sellerid,month));
        }
        return ls;
    }

    @Override
    public List<OrderEntity> getOrdersBySellerIdBystatus(String sellerid, Integer status) {
        OrderStatus orderStatus=OrderStatus.valueOf(status);
        System.out.println(orderStatus.toString());
        List<OrderDetailEntity> orderDetailEntityList=orderDetailRepositoty.findAllByOrderStatus(orderStatus);
        System.out.println(orderDetailEntityList.size());
        List<OrderEntity> orderEntityList=new ArrayList<>();
        for(OrderDetailEntity o:orderDetailEntityList){
            if(o.getOrderEntity().getSellerId().equals(sellerid)) orderEntityList.add(o.getOrderEntity());
        }
        return orderEntityList;
    }

    @Override
    public List<OrderEntity> getOrdersByUserIdBystatus(String userid, Integer status) {
        OrderStatus orderStatus=OrderStatus.valueOf(status);
        System.out.println(orderStatus.toString());
        List<OrderDetailEntity> orderDetailEntityList=orderDetailRepositoty.findAllByOrderStatus(orderStatus);
        System.out.println(orderDetailEntityList.size());
        List<OrderEntity> orderEntityList=new ArrayList<>();
        for(OrderDetailEntity o:orderDetailEntityList){

            if(o.getOrderEntity().getUserId().equals(userid)) orderEntityList.add(o.getOrderEntity());
        }
        return orderEntityList;
    }


    @Override
    public Page<OrderEntity> getOrdersByUserId(String userId, Integer page, Integer size) {
        return orderRepositoty.findAllByUserId(userId,  PageRequest.of(page,size, Sort.by("id").descending()));
    }

    @Override
    public List<OrderEntity> getOrdersBySellerId(String sellerid) {
        return orderRepositoty.findAllBySellerId(sellerid);
    }

    @Override
    public Page<OrderEntity> getOrdersBySellerId(String sellerid, Integer page, Integer size) {
        return orderRepositoty.findAllBySellerId(sellerid,PageRequest.of(page,size));
    }

    @Override
    public List<OrderEntity> getOrdersBySellerIdWithUnchecked(String sellerid) {
        List<OrderDetailEntity> orderDetailEntityList=orderDetailRepositoty.findAllByCheckDatetime(null);
        List<OrderEntity> orderEntityList=new ArrayList<>();
        for(OrderDetailEntity o:orderDetailEntityList){
            if(o.getOrderEntity().getSellerId().equals(sellerid)) orderEntityList.add(o.getOrderEntity());
        }
        return orderEntityList;
    }

    @Override
    @Transactional
    public void saveOrder(OrderEntity orderEntity) {
        try {
            OrderEntity savedOrderEntity=orderRepositoty.save(orderEntity);
            OrderDetailEntity detail= new OrderDetailEntity();
            detail.setOrderId(savedOrderEntity.getId());
            detail.setOrderStatus(OrderStatus.PAY);
            System.out.println(savedOrderEntity+":::"+detail);
            orderDetailRepositoty.save(detail );
        }catch (Exception e){
            throw  e;
        }
    }



    ////////////////////////   수민   /////////////////////////////
    @Override
    public List<Integer> getSteadySeller() {
        return orderRepositoty.findSteadySeller();
    }

    @Override
    public List<Integer> getHotProduct() {
        return orderRepositoty.findHotProduct();
    }

    @Override
    public List<Map<String,Object>> getTodayHot() {
        return orderRepositoty.findTodayHot();
    }

}
