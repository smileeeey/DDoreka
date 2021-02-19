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
    public List<Integer> getHotProduct(int userId) {

        if(userId==0)
            return orderRepositoty.findHotProduct();
        else{

            //map 생성
            List<Integer> userIds = orderRepositoty.findDistinctUserId();
            List<Integer> productIds = orderRepositoty.findDistinctProductId();

            int totalUser = userIds.size();
            int totalProduct = productIds.size();
            int[][] map = new int[totalUser][totalProduct];
            int userIdx = 0;
            for (Integer id : userIds) {

                List<Integer> products = orderRepositoty.findByUserId(id);

                int productlistIdx = 0;
                for (Integer product : products) {
                    while(productlistIdx<totalProduct){

                        if(productIds.get(productlistIdx) == product) {
                            map[userIdx][productlistIdx] = 1;
                            ++productlistIdx;
                            break;
                        }
                        ++productlistIdx;
                    }
                }
                //이러면 터터지나/??

                ++userIdx;
            }

            int targetUserIdx = 0;
            for (int i = 0 ; i < totalUser ; ++i) {
                if (userIds.get(i) == userId) {
                    targetUserIdx = i;
                    break;
                }
            }

                int max=0;
            int maxIdx = 0;

            for (int i = 0 ; i < totalUser ; ++i){
                if(i == targetUserIdx)    continue;

                int cnt = 0;
                for (int j = 0 ; j < totalProduct ; ++j){
                    if(map[targetUserIdx][j]+map[i][j] == 2)    ++cnt;
                }

                if(cnt>max){
                    max = cnt;
                    maxIdx = i;
                }
            }

            return orderRepositoty.findByUserId(userIds.get(maxIdx));
        }
    }



    @Override
    public List<Map<String,Object>> getTodayHot() {
        return orderRepositoty.findTodayHot();
    }

}
