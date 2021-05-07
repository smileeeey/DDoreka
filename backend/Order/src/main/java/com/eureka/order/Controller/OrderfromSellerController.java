package com.eureka.order.Controller;


import com.eureka.order.dto.Response;
import com.eureka.order.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

@Api(tags = "1. 판매자의 주문관리")
@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderfromSellerController {
    private final OrderService orderService;

    public OrderfromSellerController(OrderService orderService) {
        this.orderService = orderService;
    }


    @ApiOperation(value="판매자의 id로 주문 찾기(세모)", notes = "판매자 id로 주문 찾기", httpMethod = "GET")
    @GetMapping(value ="sellerid/{sellerid}/{page}/{size}")
    public Response getOrdersBySellerId(@PathVariable("sellerid") String sellerid, @ApiParam(value="몇 번째 page인지") @PathVariable Integer page, @ApiParam(value="한 페이지에 포함될 데이터 개수") @PathVariable Integer size){

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

    @GetMapping(value ="product/{productid}/dayofweek")
    public Response getOrdersByProductIdSortedByDayofWeek(@PathVariable("productid") String productid){

        System.out.println("getOrdersByProductIdSortedByDay : "+productid);
        Response response;
        try {
            response= new Response("success", "조회성공", orderService.getOrdersByProductIdSortedByDay(productid)) ;
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
