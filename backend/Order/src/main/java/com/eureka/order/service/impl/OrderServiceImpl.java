package com.eureka.order.service.impl;

import com.eureka.order.Entity.OrderDetailEntity;
import com.eureka.order.Entity.OrderEntity;
import com.eureka.order.Repository.OrderDetailRepositoty;
import com.eureka.order.Repository.OrderRepositoty;
import com.eureka.order.dto.OptionPriceDTO;
import com.eureka.order.dto.Order;
import com.eureka.order.dto.OrderDTO;
import com.eureka.order.dto.ShoppingDTO;
import com.eureka.order.service.OrderService;
import com.eureka.order.service.RestTemplateService;
import com.eureka.order.util.OrderStatus;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * order serviceimpl
 * @author refactored by shimjaehyuk, leesumin
 * @since 1.0
 */
@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    /**
     * OrderRepository Bean created by Spring Boot constructor injection
     * OrderDetailRepository Bean created by Spring Boot constructor injection
     */
    private OrderRepositoty orderRepositoty;
    private OrderDetailRepositoty orderDetailRepositoty;
    private RestTemplateService<?> restTemplateProduct;

    /**
     * get orders by user id
     *
     * @param userId
     * @return list of order entity
     */
    @Override
    public List<OrderEntity> getOrdersByUserId(String userId) {
        return orderRepositoty.findAllByUserId(userId);
    }

    /**
     * update order
     *
     * @param orderDetailEntity
     */
    @Override
    public void updateOrder(OrderDetailEntity orderDetailEntity) {
        orderDetailRepositoty.save(orderDetailEntity);
    }

    /**
     * get list of order count by product id sorted by day
     *
     * @param productId
     * @return list of count
     */
    @Override
    public List<Integer> getOrdersByProductIdSortedByDay(String productId) {
        List<Integer> ls = new ArrayList<>();
        for (int i = 1; i < 8; i++) {
            int cnt = 0;
            for (OrderEntity orderEntity : orderRepositoty.findAllByDayOfWeek(Integer.toString(i), productId)) {
                cnt += Integer.parseInt(orderEntity.getQuantity().trim());
            }
            ls.add(cnt);
        }
        return ls;
    }

    /**
     * get orders by seller id in last month
     *
     * @param sellerId
     * @param month
     * @return list of list of order
     */
    @Override
    public List<List<OrderEntity>> getOrdersBySellerIdByDay(String sellerId, String month) {
        List<List<OrderEntity>> ls = new ArrayList<>();
        for (int i = 1; i < 31; i++) {
            ls.add(orderRepositoty.findAllBySelleridofday(Integer.toString(i), sellerId, month));
        }
        return ls;
    }

    /**
     * get list of order by seller id, status
     *
     * @param sellerId
     * @param status
     * @return list of order
     */
    @Override
    public List<OrderEntity> getOrdersBySellerIdByStatus(String sellerId, Integer status) {
        OrderStatus orderStatus = OrderStatus.valueOf(status);
        List<OrderDetailEntity> orderDetailEntityList = orderDetailRepositoty.findAllByOrderStatus(orderStatus);
        List<OrderEntity> orderEntityList = new ArrayList<>();
        for (OrderDetailEntity o : orderDetailEntityList) {
            if (o.getOrderEntity().getSellerId().equals(sellerId)) orderEntityList.add(o.getOrderEntity());
        }
        return orderEntityList;
    }

    /**
     * get orders by user id, status
     *
     * @param userId
     * @param status
     * @return list of order entity
     */
    @Override
    public List<OrderEntity> getOrdersByUserIdByStatus(String userId, Integer status) {
        OrderStatus orderStatus = OrderStatus.valueOf(status);
        List<OrderDetailEntity> orderDetailEntityList = orderDetailRepositoty.findAllByOrderStatus(orderStatus);
        List<OrderEntity> orderEntityList = new ArrayList<>();
        for (OrderDetailEntity o : orderDetailEntityList) {

            if (o.getOrderEntity().getUserId().equals(userId)) orderEntityList.add(o.getOrderEntity());
        }
        return orderEntityList;
    }

    /**
     * get orders by user id
     *
     * @param userId
     * @param page
     * @param size
     * @return page of order
     */
    @Override
    public Page<OrderEntity> getOrdersByUserId(String userId, Integer page, Integer size) {
        return orderRepositoty.findAllByUserId(userId, PageRequest.of(page, size, Sort.by("id").descending()));
    }

    /**
     * @param sellerId
     * @return list of order
     * @deprecated NOT USED
     * get orders by seller id
     */
    @Override
    public List<OrderEntity> getOrdersBySellerId(String sellerId) {
        return orderRepositoty.findAllBySellerId(sellerId);
    }

    /**
     * get orders by seller id
     *
     * @param sellerId
     * @param page
     * @param size
     * @return page of order
     */
    @Override
    public Page<OrderEntity> getOrdersBySellerId(String sellerId, Integer page, Integer size) {
        return orderRepositoty.findAllBySellerId(sellerId, PageRequest.of(page, size));
    }

    /**
     * get orders by seller id with unchecked
     *
     * @param sellerId
     * @return List<OrderEntity>
     */
    @Override
    public List<OrderEntity> getOrdersBySellerIdWithUnchecked(String sellerId) {
        List<OrderDetailEntity> orderDetailEntityList = orderDetailRepositoty.findAllByCheckDatetime(null);
        List<OrderEntity> orderEntityList = new ArrayList<>();
        for (OrderDetailEntity o : orderDetailEntityList) {
            if (o.getOrderEntity().getSellerId().equals(sellerId)) orderEntityList.add(o.getOrderEntity());
        }
        return orderEntityList;
    }

    /**
     * save order
     *
     * @param orderEntity
     */
    @Override
    @Transactional
    public void saveOrder(OrderEntity orderEntity) {
        try {
            OrderEntity savedOrderEntity = orderRepositoty.save(orderEntity);
//            OrderDetailEntity detail= new OrderDetailEntity();
//            detail.setOrderId(savedOrderEntity.getId());
//            detail.setOrderStatus(OrderStatus.PAY);
            orderDetailRepositoty.save(
                    OrderDetailEntity.builder()
                            .orderId(savedOrderEntity.getId())
                            .orderStatus(OrderStatus.PAY)
                            .build()
            );
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * get steady seller
     *
     * @return List<Integer>
     */
    @Override
    public List<Integer> getSteadySeller() {
        return orderRepositoty.findSteadySeller();
    }

    /**
     * get hot product
     *
     * @return List<Integer>
     */
    @Override
    public List<Integer> getHotProduct() {
        return orderRepositoty.findHotProduct();
    }

    /**
     * get today hot
     *
     * @return List<Map < String, Object>>
     */
    @Override
    public List<Map<String, Object>> getTodayHot() {
        return orderRepositoty.findTodayHot();
    }

    /**
     * get today hot products
     *
     * @return List<String>
     */
    @Override
    public List<String> getTodayHotProducts() {
        return orderRepositoty.todayHotProducts();
    }

    /**
     * get weekHotProducts
     *
     * @return List<String>
     */
    @Override
    public List<String> getWeekHotProducts() {
        return orderRepositoty.weekHotProducts();
    }

    /**
     * get monthHotProducts
     *
     * @return List<String>
     */
    @Override
    public List<String> getMonthHotProducts() {
        return orderRepositoty.monthHotProducts();
    }


    private List<OptionPriceDTO> getOptionPrice(String reqProduct){
        String getProductURL = "http://localhost:8081/product/prices";

        HttpHeaders productHttpHeaders = new HttpHeaders();
        productHttpHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        productHttpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        productHttpHeaders.set("option-ids", reqProduct);
        System.out.println(reqProduct);


        ResponseEntity<?> responseEntityFile = restTemplateProduct.get(getProductURL, productHttpHeaders);

        System.out.println("rest 갔다옴!");
        return (List<OptionPriceDTO>)responseEntityFile.getBody();
    }

    private void saveOrderAndDetail(OrderDTO orders, List<ShoppingDTO> shoppings){

    }

    @Override
    public void saveOrderAll(OrderDTO orders, List<ShoppingDTO> shoppings) {

        String reqProduct = getOptionIds(shoppings);

        if(reqProduct == null){
            return;
        }

        //Product 서버에서 option에 해당하는 가격 정보 받아오기
        List<OptionPriceDTO> optionPriceDTOS = getOptionPrice(reqProduct);

        if(optionPriceDTOS.isEmpty() || optionPriceDTOS == null){
            System.out.println("shopping에 해당하는 option 정보가 productOption 테이블에 없다!");
            return;
        }

        //넘겨받은 option - price 매칭해서 order 생성
        List<OrderEntity> orderEntities = new ArrayList<>();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = new Date();

        String curTime = format.format(time);
        System.out.println("현재시간 " + curTime);
        for (int i = 0; i < shoppings.size(); ++i) {

            String optionId = shoppings.get(i).getOptionId();
            int price = 0;

            for (int j = 0; j < optionPriceDTOS.size(); j++) {
                Map<String,Object> optionPriceMap = (Map<String, Object>)optionPriceDTOS.get(j);

                if(optionPriceMap.get("optionId").equals(optionId)){
                    price = Integer.parseInt(shoppings.get(i).getQuantity()) * (Integer)optionPriceMap.get("price");
                    break;
                }
            }


            OrderEntity orderEntity = OrderEntity.builder()
                    .userId(orders.getUserId())
                    .productId(shoppings.get(i).getProductId())
                    .optionId(shoppings.get(i).getOptionId())
                    .sellerId(shoppings.get(i).getSellerId())
                    .addressMain(orders.getAddressMain())
                    .addressSub(orders.getAddressSub())
                    .recipientName(orders.getRecipientName())
                    .zipcode(orders.getZipcode())
                    .deliveryMsg(orders.getDeliveryMsg())
                    .recipientPhone(orders.getRecipientPhone())
                    .quantity(shoppings.get(i).getQuantity())
                    .price(Integer.toString(price))
//                    .datetime(curTime)
                    .paymentMethod(orders.getPaymentMethod())
                    .build();

            System.out.println("엔티티 잘만듬!!");
            orderEntities.add(
                    orderEntity
            );
        }
        System.out.println("orderEntities 잘만들어짐 사이즈는 " + orderEntities.size());

        List<OrderEntity> saveOrderEntities = orderRepositoty.saveAll(orderEntities);

        System.out.println("저장하고난 사이즈 " + saveOrderEntities.size());

        List<OrderDetailEntity> orderDetailEntities = new ArrayList<>();

        for (int i = 0; i < saveOrderEntities.size(); ++i) {
            System.out.println(i + "번쨰");
            OrderDetailEntity orderDetailEntity = OrderDetailEntity.builder()
                    .orderId(saveOrderEntities.get(i).getId())

                    .orderStatus(OrderStatus.PAY)
                    .build();

            orderDetailEntities.add(
                    orderDetailEntity
            );
        }

        System.out.println("오더디테일 저장할꺼 만듬 " + orderDetailEntities.size());

        List<OrderDetailEntity> orderDetailEntities1 = orderDetailRepositoty.saveAll(orderDetailEntities);

        System.out.println("오더디테일 저장완료!");
        //장바구니 삭제
        List<Integer> cartIds = new ArrayList<>();

        for (int i = 0; i < shoppings.size(); ++i) {
            cartIds.add(shoppings.get(i).getCartId());
        }

        Gson gson = new Gson();

        String cartIdStr = gson.toJson(cartIds);

        String deleteCartURL = "http://localhost:8085/user/cart/deletes";

        HttpHeaders cartHttpHeaders = new HttpHeaders();
        cartHttpHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        cartHttpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        cartHttpHeaders.set("cartIdsParam", cartIdStr);


        restTemplateProduct.delete(deleteCartURL, cartHttpHeaders);

        System.out.println("삭제 성공!");


    }

    private String getOptionIds(List<ShoppingDTO> shoppings){

        if (shoppings == null || shoppings.isEmpty()) {
            System.out.println("shoppings 데이터 없음!!");
            return null;
        }

        List<Integer> optionIds = new ArrayList<>();
        for (int i = 0; i < shoppings.size(); ++i) {
            optionIds.add(Integer.parseInt(shoppings.get(i).getOptionId()));
        }

        Gson gson = new Gson();

        return gson.toJson(optionIds);
    }

    /*
    @Override
    public void saveOrderAll( List<ShoppingDTO> shoppings) {

        //List<String>를  으로 넘기고 map<"option-id",List<option_id,price = OptionPriceDTO> :

        if(shoppings.isEmpty() || shoppings == null){
            System.out.println("shoppings 데이터 없음!!");
            return;
        }

//        List<String> optionIds = new ArrayList<>();
//        for (int i = 0 ; i < shoppings.size() ; ++i){
//            optionIds.add(shoppings.get(i).getOptionId());
//        }

        List<Integer> optionIds = new ArrayList<>();
        for (int i = 0 ; i < shoppings.size() ; ++i){
            optionIds.add(Integer.parseInt(shoppings.get(i).getOptionId()));
        }

        Gson gson = new Gson();
        String reqProduct = gson.toJson(optionIds);

        //set을 list로 변환하는 과정!!!
//        Set<String> optionIdSet = new HashSet<>(); // 무야호
//        shoppings.forEach((shopping)-> optionIdSet.add(shopping.getOptionId()));
//        List<String> optionIds = new ArrayList<>(optionIdSet);
//        정준영이 틀렸을 경우
//        Iterator<String> iter = optionIdSet.iterator(); // set을 Iterator 안에 담기
//        while(iter.hasNext()) { // iterator에 다음 값이 있다면
//            optionIds.add(iter.next());
//        }

        String getProductURL = "http://k4d104.p.ssafy.io:8081/product/prices";

        HttpHeaders productHttpHeaders = new HttpHeaders();
        productHttpHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        productHttpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        reqProduct = reqProduct.toString().substring(1, reqProduct.toString().length()-1);
        System.out.println(reqProduct);

        productHttpHeaders.set("option-ids",reqProduct);

        ResponseEntity<?> responseEntityFile = restTemplateProduct.get(getProductURL, productHttpHeaders);

        System.out.println("rest 갔다옴!");
        List<OptionPriceDTO> optionPriceDTOS = (List<OptionPriceDTO>)responseEntityFile.getBody();

        if(optionPriceDTOS.size() == 0 || optionPriceDTOS == null){
            System.out.println("shopping에 해당하는 option 정보가 productOption 테이블에 없다!");
            return;
        }

        System.out.println("옵션에 맞는 가격값 가져왔는데 그 크기는!"+optionPriceDTOS.size());



    }
     */
}