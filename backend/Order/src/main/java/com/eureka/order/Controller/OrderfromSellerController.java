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

}
