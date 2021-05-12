package com.eureka.order.Controller;

import com.eureka.order.Entity.OrderDetailEntity;
import com.eureka.order.Entity.OrderEntity;
import com.eureka.order.dto.Response;
import com.eureka.order.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Class to control order information by user
 * @author refactored by shimjaehyuk, leesumin
 * @since 1.0
 */
@Api(tags = "order from user")
@RestController
@RequestMapping(value = "/order")
@CrossOrigin
public class OrderFromUserController {

    /**
     * OrderService Bean created by Spring Boot constructor injection
     */
    private OrderService orderService;
    @Autowired
    public OrderFromUserController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * @deprecated NOT USED
     * @param userId
     * @param page
     * @param size
     * @return response
     */
    @ApiOperation(value="구매자의 id로 주문 찾기 with paging x", notes = "구매자 id로 주문 찾기", httpMethod = "GET")
    @GetMapping(value ="userid/{userId}/{page}/{size}")
    public Response getOrdersByUserId(@ApiParam(value="구매자 id") @PathVariable("userId") String userId,  @ApiParam(value="몇 번째 page인지") @PathVariable Integer page, @ApiParam(value="한 페이지에 포함될 데이터 개수") @PathVariable Integer size){

        System.out.println("getorders : "+userId);
        Response response;
        try {
            response= new Response("success", "조회성공", orderService.getOrdersByUserId(userId,page,size)) ;
        } catch (Exception e) {
            response= new Response("error", e.getMessage(), null) ;
        }
        return response;

    }

    /**
     * get all of user's order information
     * @param userId
     * @return response
     */
    @ApiOperation(value="해당 구매자의 모든 주문 정보", notes = "구매자 id의 모든 주문 찾기", httpMethod = "GET")
    @GetMapping(value ="userid/{userId}/all")
    public Response getOrders(@ApiParam(value="구매자 id") @PathVariable("userId") String userId){

        System.out.println("getorders : "+userId);
        Response response;
        try {
            response= new Response("success", "조회성공", orderService.getOrdersByUserId(userId)) ;
        } catch (Exception e) {
            response= new Response("error", e.getMessage(), null) ;
        }
        return response;

    }

    /**
     * get user's order by status
     * @param userid
     * @param status
     * @return response
     */
    @ApiOperation(value="구매자의 주문 상태별 조회", notes = "구매자의 주문 데이터 중 해당 상태인 주문 데이터 list로 반환(0:결제완료,1:배송중,2:배송완료,3:환불신청)", httpMethod = "GET")
    @GetMapping(value ="userid/{userid}/status/{statusnum}")
    public Response getOrdersByUserIdBystatus(@ApiParam(value="구매자 id") @PathVariable("userid") String userid,@ApiParam(value="주문 상태") @PathVariable("statusnum") Integer status){

        Response response;
        try {
            response= new Response("success", "조회성공", orderService.getOrdersByUserIdByStatus(userid, status)) ;
        } catch (Exception e) {
            response= new Response("error", e.getMessage(), null) ;
        }
        return response;
    }

    /**
     * post user's order information
     * @param orderEntity
     * @return response
     */
    @ApiOperation(value="구매자의 주문 데이터 저장", notes = "구매자의 주문 데이터 저장", httpMethod = "POST")
    @PostMapping()
    public Response saveOrders(@ApiParam(value="입력받은 주문 저장 데이터") @RequestBody OrderEntity orderEntity) {

        Response response;
        try {
            orderService.saveOrder(orderEntity);
            response= new Response("success", "등록성공", orderService.getOrdersByUserId(orderEntity.getUserId())) ;
        } catch (Exception e) {
            response= new Response("error", e.getMessage(), null) ;
        }
        return response;
    }

    /**
     * put user's order information
     * @param userId
     * @param orderDetailEntity
     * @return response
     */
    @ApiOperation(value="구매자의 주문 데이터 수정", notes = "구매자의 주문 데이터 수정", httpMethod = "PUT")
    @PutMapping ("/update/user/{userId}")
    public Response updateOrders(@ApiParam(value="구매자 id") @PathVariable("userId") String userId,@ApiParam(value="입력받은 주문 수정 데이터") @RequestBody OrderDetailEntity orderDetailEntity) {

        Response response;
        try {
            orderService.updateOrder(orderDetailEntity);
            response= new Response("success", "변경성공", orderService.getOrdersByUserId(userId)) ;
        } catch (Exception e) {
            response= new Response("error", e.getMessage(), null) ;
        }
        return response;
    }

    /**
     * get Top 10 of the products that have been ordered the most in the last month
     * @return response
     */
    @ApiOperation(value="최근 한달간 주문이 가장 많았던 상품 top10", notes = "상품 id list로 반환", httpMethod = "GET")
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

    /**
     *  get Top 10 of the products that have been ordered the most in the last week
     * @return response
     */
    @ApiOperation(value="최근 일주일간 주문이 가장 많았던 상품 top10", notes = "상품 id list로 반환", httpMethod = "GET")
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

    /**
     * get Top 6 of the products that have been ordered the most int the last 24 hours
     * @return response
     */
    @ApiOperation(value="최근 24시간 동안 주문이 가장 많았던 상품 top6", notes = "상품 id list로 반환", httpMethod = "GET")
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
