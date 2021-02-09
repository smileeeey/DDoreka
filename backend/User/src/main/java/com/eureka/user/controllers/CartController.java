package com.eureka.user.controllers;

import com.eureka.user.Entity.CartEntity;
import com.eureka.user.dto.Cart;
import com.eureka.user.dto.Response;
import com.eureka.user.services.AuthService;
import com.eureka.user.services.CartService;
import com.eureka.user.services.JwtUtil;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping(value="/{userid}")
    public Response getcart(@PathVariable("userid") String UserEmail ){
        System.out.println("getcart : "+UserEmail);
        try{
            String userid=authService.getUserId(UserEmail);
            return new Response("success","제품 장바구니 조회 성공",cartService.getCart(userid));
        }
        catch (Exception e){
            return new Response("error","제품 장바구니 조회 실패",e.getMessage());
        }
    }

    @PostMapping()
    public Response saveCart(@RequestBody Cart cart){
        System.out.println(cart);
        try{
            return new Response("success","제품 장바구니 담기 성공",cartService.saveCart(cart));
        }
        catch (Exception e){
            return new Response("error","제품 장바구니 담기 실패",e.getMessage());
        }
    }

    @PutMapping()
    public Response updateCart(@RequestBody CartEntity cart){
        System.out.println("updateCart " +cart);
        try{
            return new Response("success","제품 장바구니 변경 성공",cartService.updateCart(cart));
        }
        catch (Exception e){
            return new Response("error","제품 장바구니 변경 실패",e.getMessage());
        }
    }

<<<<<<< HEAD
    @GetMapping(value = "/{key}")
    public String getUsers(@PathVariable(value = "key") String key) {
        System.out.println(key + "$$$$$$$$$$$$$$$$$$$$$$");

        String userId = jwtUtil.getUsername(key);
        System.out.println(userId);
        return "";
=======
    @DeleteMapping(value="/all/{UserEmail}")
    public Response deleteAllCart(@PathVariable("UserEmail") String UserEmail){
        System.out.println("deleteAllCart " +UserEmail);
        try{
            cartService.deleteAll(UserEmail);
            return new Response("success","제품 장바구니 비우기 성공",null);
        }
        catch (Exception e){
            return new Response("error","제품 장바구니 비우기 실패",e.getMessage());
        }
    }

    @DeleteMapping(value="/{cartid}")
    public Response deleteCart(@PathVariable("cartid") String cartId){
        System.out.println("deleteCart " +cartId);
        try{
            cartService.deleteCart(cartId);
            return new Response("success","제품 장바구니 삭제 성공",null);
        }
        catch (Exception e){
            return new Response("error","제품 장바구니 삭제 실패",e.getMessage());
        }
>>>>>>> a5d6b9746a8060369aa07a14ed58bd04ffdf5525
    }



}
