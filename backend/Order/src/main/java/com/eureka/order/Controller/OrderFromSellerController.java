package com.eureka.order.Controller;


import com.eureka.order.dto.Response;
import com.eureka.order.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Class to control order information by seller
 * @author refactored by shimjaehyuk, leesumin
 * @since 1.0
 */
@Api(tags = "1. Order from seller")
@RestController
@RequestMapping("/order")
@CrossOrigin
@AllArgsConstructor
public class OrderFromSellerController {

    /**
     * OrderService Bean created by Spring Boot constructor injection
     */
    @Autowired
    private OrderService orderService;
//    @Autowired
//    public OrderFromSellerController(OrderService orderService) {
//        this.orderService = orderService;
//    }

    /**
     * get order information by seller id
     * @param sellerid
     * @param page
     * @param size
     * @return response
     */
    @ApiOperation(value="판매자의 id로 주문 찾기(세모)", notes = "판매자 id로 주문 찾기", httpMethod = "GET")
    @GetMapping(value ="sellerid/{sellerid}/{page}/{size}")
    public Response getOrdersBySellerId(@ApiParam(value="판매자 id") @PathVariable("sellerid") String sellerid, @ApiParam(value="몇 번째 page인지") @PathVariable Integer page, @ApiParam(value="한 페이지에 포함될 데이터 개수") @PathVariable Integer size){

        Response response;
        try {
            response= new Response("success", "조회성공", orderService.getOrdersBySellerId(sellerid,page,size)) ;
        } catch (Exception e) {
            response= new Response("error", e.getMessage(), null) ;
        }
        return response;
    }

    /**
     * get order information that not yet checked by seller
     * @param sellerid
     * @return response
     */
    @ApiOperation(value="해당 판매자가 아직 주문확인 하지 않은 주문 찾기", notes = "판매자 id로 주문확인 필요한 주문들", httpMethod = "GET")
    @GetMapping(value ="sellerid/{sellerid}/unchecked")
    public Response getOrdersBySellerIdWithUnchecked(@ApiParam(value="판매자 id") @PathVariable("sellerid") String sellerid){

        Response response;
        try {
            response= new Response("success", "조회성공", orderService.getOrdersBySellerIdWithUnchecked(sellerid)) ;
        } catch (Exception e) {
            e.printStackTrace();
            response= new Response("error", e.getMessage(), null) ;
        }
        return response;

    }

    /**
     * get Number of products selled by day of the week
     * @param productid
     * @return response
     */
    @ApiOperation(value="상품의 주간 요일별 판매량(일요일 :1 ~)", notes = "상품의 주간 판매량", httpMethod = "GET")
    @GetMapping(value ="product/{productid}/dayofweek")
    public Response getOrdersByProductIdSortedByDayofWeek(@ApiParam(value="상품 id") @PathVariable("productid") String productid){

        Response response;
        try {
            response= new Response("success", "조회성공", orderService.getOrdersByProductIdSortedByDay(productid)) ;
        } catch (Exception e) {
            response= new Response("error", e.getMessage(), null) ;
        }
        return response;
    }

    /**
     * get sales information of products by day of the month
     * @param sellerid
     * @param month
     * @return response
     */
    @ApiOperation(value="상품의 월간 일별 판매정보", notes = "해당 월의 1~30일까지의 해당 판매자에게 들어온 주문 데이터를 list로 반환", httpMethod = "GET")
    @GetMapping(value ="sellerid/{sellerid}/{month}")
    public Response getOrdersBySellerIdByday(@ApiParam(value="판매자 id") @PathVariable("sellerid") String sellerid,@ApiParam(value="조회할 월") @PathVariable("month") String month){

        Response response;
        try {
            response= new Response("success", "조회성공", orderService.getOrdersBySellerIdByDay(sellerid, month)) ;
        } catch (Exception e) {
            response= new Response("error", e.getMessage(), null) ;
        }
        return response;
    }

    /**
     * get order state
     * @param sellerid
     * @param status
     * @return response
     */
    @ApiOperation(value="주문 상태별 조회", notes = "판매자에게 들어온 주문 데이터 중 해당 상태인 주문 데이터 list로 반환(0:결제완료,1:배송중,2:배송완료,3:환불신청)", httpMethod = "GET")
    @GetMapping(value ="sellerid/{sellerid}/status/{statusnum}")
    public Response getOrdersBySellerIdBystatus(@ApiParam(value="판매자 id") @PathVariable("sellerid") String sellerid,@ApiParam(value="주문 상태") @PathVariable("statusnum") Integer status){

        Response response;
        try {
            response= new Response("success", "조회성공", orderService.getOrdersBySellerIdByStatus(sellerid, status)) ;
        } catch (Exception e) {
            e.printStackTrace();
            response= new Response("error", e.getMessage(), null) ;
        }
        return response;
    }

}
