package com.eureka.order.Controller;

import com.eureka.order.Entity.OrderDetailEntity;
import com.eureka.order.Entity.OrderEntity;
import com.eureka.order.dto.Response;
import com.eureka.order.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/order")
@CrossOrigin
public class OrderFromUserController {

    private final OrderService orderService;

    public OrderFromUserController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value ="userid/{userId}")
    public Response getOrdersByUserId(@PathVariable("userId") String userId, @RequestParam Integer page,Integer size){

        System.out.println("getorders : "+userId);
        Response response;
        try {
            response= new Response("success", "조회성공", orderService.getOrdersByUserId(userId,page,size)) ;
        } catch (Exception e) {
            response= new Response("error", e.getMessage(), null) ;
        }
        return response;

    }

    @GetMapping(value ="userid/{userId}/all")
    public Response getOrders(@PathVariable("userId") String userId){

        System.out.println("getorders : "+userId);
        Response response;
        try {
            response= new Response("success", "조회성공", orderService.getOrdersByUserId(userId)) ;
        } catch (Exception e) {
            response= new Response("error", e.getMessage(), null) ;
        }
        return response;

    }

    @GetMapping(value ="userid/{userid}/status/{statusnum}")
    public Response getOrdersByUserIdBystatus(@PathVariable("userid") String userid,@PathVariable("statusnum") Integer status){

        System.out.println("getOrdersByUserIdBystatus : "+userid+" "+status);
        Response response;
        try {
            response= new Response("success", "조회성공", orderService.getOrdersByUserIdBystatus(userid, status)) ;
        } catch (Exception e) {
            response= new Response("error", e.getMessage(), null) ;
        }
        return response;
    }

    @PostMapping()
    public Response saveOrders( @RequestBody OrderEntity orderEntity) {

        System.out.println("saveorders : "  +orderEntity);
        Response response;
        try {
            orderService.saveOrder(orderEntity);
            response= new Response("success", "등록성공", orderService.getOrdersByUserId(orderEntity.getUserId())) ;
        } catch (Exception e) {
            response= new Response("error", e.getMessage(), null) ;
        }
        return response;
    }

    @PutMapping ()
    public Response updateOrders(@RequestBody OrderDetailEntity orderDetailEntity) {

        System.out.println("updateOrders : "  +orderDetailEntity);
        Response response;
        try {
            orderService.updateOrder(orderDetailEntity);
            response= new Response("success", "변경성공", orderService.getOrdersByUserId(orderDetailEntity.getOrderId())) ;
        } catch (Exception e) {
            response= new Response("error", e.getMessage(), null) ;
        }
        return response;
    }

////////////////////////   수민   /////////////////////////////

    //스테디 셀러
    @GetMapping(value ="/recommend/steadyseller")
    public Response steadySeller(){

        Response response;
        try {
            response= new Response("success", "스테디셀러 조회 성공", orderService.getSteadySeller()) ;
        } catch (Exception e) {
            response= new Response("error", "스테디셀러 조회 실패", e.getMessage()) ;
        }
        return response;
    }

    //요즘 뜨는 상품
    @GetMapping(value ="/recommend/hotproduct")
    public Response hotProduct(){
        Response response;
        try {
            response= new Response("success", "요즘 뜨는 상품 조회 성공", orderService.getHotProduct()) ;
        } catch (Exception e) {
            response= new Response("error", "요즘 뜨는 상품 조회 실패", e.getMessage()) ;
        }
        return response;
    }

    // 오늘의 상품
    @GetMapping(value ="/recommend/todayhot")
    public Response todayHot(){
        Response response;
        try {
            response= new Response("success", "오늘 뜨는 상품 조회 성공", orderService.getTodayHot()) ;
        } catch (Exception e) {
            response= new Response("error", "오늘 뜨는 상품 조회 실패", e.getMessage()) ;
        }
        return response;
    }
}
