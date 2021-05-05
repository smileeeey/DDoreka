package com.eureka.user.controllers;


import com.eureka.user.Entity.UseraddressEntity;
import com.eureka.user.dto.Response;
import com.eureka.user.services.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"2. Address"})
@RestController
@RequestMapping("/user/address")
@CrossOrigin(origins = "*")
public class AddressController {

    private final AuthService authService;

    private final AddressService addressService;

    private final JwtUtil jwtUtil;

    private final CookieUtil cookieUtil;



    public AddressController(AuthService authService, AddressService addressService, JwtUtil jwtUtil, CookieUtil cookieUtil) {
        this.authService = authService;
        this.addressService = addressService;
        this.jwtUtil = jwtUtil;
        this.cookieUtil = cookieUtil;

    }

    @ApiOperation(value="구매자별 주소 조회", notes = "구매자가 등록한 주소 목록 조회", httpMethod = "GET")
    @GetMapping(value = "/{id}")
    public Response getAddress(@ApiParam(value="구매자 고유값") @PathVariable("id") String loginUser){
        System.out.println("get address "+ loginUser);
        Response response;
        try {
            response= new Response("success", "조회성공.", addressService.getaddress(loginUser)) ;
        } catch (Exception e) {
            response= new Response("error", "조회실패", null) ;
        }
        return response;
    }

    @ApiOperation(value="주소 등록", notes = "구매자의 배송지 주소 등록", httpMethod = "POST")
    @PostMapping(value = "/{id}")
    public Response saveAddress(@ApiParam(value="구매자 고유값") @PathVariable("id") String email, @ApiParam(value="주소 데이터")  @RequestBody UseraddressEntity useraddressEntity ){
        System.out.println(" post address "+email+" "+useraddressEntity);
        Response response;
        try {
            response= new Response("success", "등록성공", addressService.   saveaddress(useraddressEntity,email)) ;
        } catch (Exception e) {
            response= new Response("error", e.getMessage(), null) ;
        }
        return response;
    }

    @ApiOperation(value="주소 수정", notes = "구매자의 배송지 주소 수정", httpMethod = "PUT")
    @PutMapping(value = "/{id}")
    public Response updateAddress(@ApiParam(value="구매자 고유값") @PathVariable("id") String email,@ApiParam(value="주소 데이터")  @RequestBody UseraddressEntity useraddressEntity ){
        System.out.println(" updateAddress "+email+" "+useraddressEntity);
        Response response;
        try {
            response= new Response("success", "등록성공", addressService.updateAddress(useraddressEntity,email)) ;
        } catch (Exception e) {
            response= new Response("error", e.getMessage(), null) ;
        }
        return response;
    }

    @ApiOperation(value="주소 삭제", notes = "구매자의 배송지 주소 삭제", httpMethod = "DELETE")
    @DeleteMapping(value = "/{id}/{addressId}")
    public Response deleteAddress(@ApiParam(value="구매자 고유값") @PathVariable("id") String email,@ApiParam(value="주소 고유값")  @PathVariable("addressId") String addressId){
        System.out.println(" post address "+email+" "+addressId);
        Response response;
        try {
            response= new Response("success", "삭제 성공", addressService.delete(addressId,email)) ;
        } catch (Exception e) {
            response= new Response("error", "삭제 실패", e.getMessage()) ;
        }
        return response;
    }

}
