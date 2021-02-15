package com.eureka.order.Controller;


import com.eureka.order.dto.Response;
import com.eureka.order.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderfromSellerController {
    private final OrderService orderService;

    public OrderfromSellerController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value ="sellerid/{sellerid}")
    public Response getOrdersBySellerId(@PathVariable("sellerid") String sellerid, @RequestParam Integer page, Integer size){

        System.out.println("getOrdersBySellerId : "+sellerid);
        Response response;
        try {
            response= new Response("success", "조회성공", orderService.getOrdersBySellerId(sellerid,page,size)) ;
        } catch (Exception e) {
            response= new Response("error", e.getMessage(), null) ;
        }
        return response;

    }

    @GetMapping(value ="sellerid/{sellerid}/unchecked")
    public Response getOrdersBySellerIdWithUnchecked(@PathVariable("sellerid") String sellerid){

        System.out.println("getOrdersBySellerIdWithUnchecked : "+sellerid);
        Response response;
        try {
            response= new Response("success", "조회성공", orderService.getOrdersBySellerIdWithUnchecked(sellerid)) ;
        } catch (Exception e) {
            response= new Response("error", e.getMessage(), null) ;
        }
        return response;

    }

    @GetMapping(value ="prodcut/{prodcutid}/dayofweek")
    public Response getOrdersByProductIdSortedByDayofWeek(@PathVariable("prodcutid") String prodcutid){

        System.out.println("getOrdersByProductIdSortedByDay : "+prodcutid);
        Response response;
        try {
            response= new Response("success", "조회성공", orderService.getOrdersByProductIdSortedByDay(prodcutid)) ;
        } catch (Exception e) {
            response= new Response("error", e.getMessage(), null) ;
        }
        return response;
    }

    @GetMapping(value ="sellerid/{sellerid}/{month}")
    public Response getOrdersBySellerIdByday(@PathVariable("sellerid") String sellerid,@PathVariable("month") String month){

        System.out.println("getOrdersBySellerIdByday : "+sellerid+" "+month);
        Response response;
        try {
            response= new Response("success", "조회성공", orderService.getOrdersBySellerIdByday(sellerid, month)) ;
        } catch (Exception e) {
            response= new Response("error", e.getMessage(), null) ;
        }
        return response;
    }

    @GetMapping(value ="sellerid/{sellerid}/status/{statusnum}")
    public Response getOrdersBySellerIdBystatus(@PathVariable("sellerid") String sellerid,@PathVariable("statusnum") Integer status){

        System.out.println("getOrdersBySellerIdBystatus : "+sellerid+" "+status);
        Response response;
        try {
            response= new Response("success", "조회성공", orderService.getOrdersBySellerIdBystatus(sellerid, status)) ;
        } catch (Exception e) {
            response= new Response("error", e.getMessage(), null) ;
        }
        return response;
    }

}
