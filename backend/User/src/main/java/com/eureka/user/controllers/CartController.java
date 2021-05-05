package com.eureka.user.controllers;

import com.eureka.user.Entity.CartEntity;
import com.eureka.user.dto.Cart;
import com.eureka.user.dto.Response;
import com.eureka.user.services.AuthService;
import com.eureka.user.services.CartService;
import com.eureka.user.services.JwtUtil;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"3. Cart"})
@RestController
@RequestMapping(value = "user/cart")
public class CartController {


    private final JwtUtil jwtUtil;

    private final CartService cartService;

    private  final AuthService authService;

    public CartController(JwtUtil jwtUtil, CartService cartService, AuthService authService) {
        this.jwtUtil = jwtUtil;
        this.cartService = cartService;
        this.authService = authService;
    }

    @ApiOperation(value="장바구니 조회", notes = "구매자의 장바구니에 담긴 데이터 반환", httpMethod = "GET")
    @GetMapping(value="/{userid}")
    public Response getcart(@ApiParam(value="구매자 고유값") @PathVariable("userid") String UserEmail ){
        System.out.println("getcart : "+UserEmail);
        try{
            String userid=authService.getUserId(UserEmail);
            return new Response("success","제품 장바구니 조회 성공",cartService.getCart(userid));
        }
        catch (Exception e){
            return new Response("error","제품 장바구니 조회 실패",e.getMessage());
        }
    }

    @ApiOperation(value="장바구니 저장", notes = "구매자의 장바구니에 데이터 저장", httpMethod = "POST")
    @PostMapping()
    public Response saveCart(@ApiParam(value="장바구니 데이터") @RequestBody Cart cart){
        System.out.println(cart);
        try{
            return new Response("success","제품 장바구니 담기 성공",cartService.saveCart(cart));
        }
        catch (Exception e){
            return new Response("error","제품 장바구니 담기 실패",e.getMessage());
        }
    }

    @ApiOperation(value="장바구니 수정", notes = "구매자의 장바구니에 데이터 수정", httpMethod = "PUT")
    @PutMapping()
    public Response updateCart(@ApiParam(value="장바구니 데이터") @RequestBody CartEntity cart){
        System.out.println("updateCart " +cart);
        try{
            return new Response("success","제품 장바구니 변경 성공",cartService.updateCart(cart));
        }
        catch (Exception e){
            return new Response("error","제품 장바구니 변경 실패",e.getMessage());
        }
    }

    @ApiOperation(value="장바구니 비우기", notes = "구매자의 모든 장바구니 데이터 삭제", httpMethod = "DELETE")
    @DeleteMapping(value="/all/{UserEmail}")
    public Response deleteAllCart(@ApiParam(value="구매자 고유값") @PathVariable("UserEmail") String UserEmail){
        System.out.println("deleteAllCart " +UserEmail);
        try{
            cartService.deleteAll(UserEmail);
            return new Response("success","제품 장바구니 비우기 성공",null);
        }
        catch (Exception e){
            return new Response("error","제품 장바구니 비우기 실패",e.getMessage());
        }
    }

    @ApiOperation(value="장바구니 개별 항목 삭제", notes = "장바구니 고유값에 해당하는 장바구니 데이터 삭제", httpMethod = "DELETE")
    @DeleteMapping(value="/{cartid}")
    public Response deleteCart(@ApiParam(value="장바구니 고유값") @PathVariable("cartid") String cartId){
        System.out.println("deleteCart " +cartId);
        try{
            cartService.deleteCart(cartId);
            return new Response("success","제품 장바구니 삭제 성공",null);
        }
        catch (Exception e){
            return new Response("error","제품 장바구니 삭제 실패",e.getMessage());
        }
    }



}
