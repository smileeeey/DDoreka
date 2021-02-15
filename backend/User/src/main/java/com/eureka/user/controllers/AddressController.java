package com.eureka.user.controllers;


import com.eureka.user.Entity.UseraddressEntity;
import com.eureka.user.dto.Response;
import com.eureka.user.services.*;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/{id}")
<<<<<<< HEAD
    public Response getaddress(@PathVariable("id") String loginUser) {
        System.out.println("get address");
=======
    public Response getAddress(@PathVariable("id") String loginUser){
        System.out.println("get address "+ loginUser);
>>>>>>> a5d6b9746a8060369aa07a14ed58bd04ffdf5525
        Response response;
        try {
            response = new Response("success", "조회성공.", addressService.getaddress(loginUser));
        } catch (Exception e) {
            response = new Response("error", "조회실패", null);
        }
        return response;
    }

    @PostMapping(value = "/{id}")
<<<<<<< HEAD
    public Response saveaddress(@PathVariable("id") String email, @RequestBody UseraddressEntity useraddressEntity) {
        System.out.println(" post address " + email + " " + useraddressEntity);
        Response response;
        try {
            response = new Response("success", "등록성공", addressService.putaddress(useraddressEntity, email));
=======
    public Response saveAddress(@PathVariable("id") String email, @RequestBody UseraddressEntity useraddressEntity ){
        System.out.println(" post address "+email+" "+useraddressEntity);
        Response response;
        try {
            response= new Response("success", "등록성공", addressService.   saveaddress(useraddressEntity,email)) ;
        } catch (Exception e) {
            response= new Response("error", e.getMessage(), null) ;
        }
        return response;
    }

    @PutMapping(value = "/{id}")
    public Response updateAddress(@PathVariable("id") String email, @RequestBody UseraddressEntity useraddressEntity ){
        System.out.println(" updateAddress "+email+" "+useraddressEntity);
        Response response;
        try {
            response= new Response("success", "등록성공", addressService.updateAddress(useraddressEntity,email)) ;
>>>>>>> a5d6b9746a8060369aa07a14ed58bd04ffdf5525
        } catch (Exception e) {
            response = new Response("error", e.getMessage(), null);
        }
        return response;
    }

    @DeleteMapping(value = "/{id}/{addressId}")
<<<<<<< HEAD
    public Response deleteaddress(@PathVariable("id") String email, @PathVariable("addressId") String addressId) {
        System.out.println(" post address " + email + " " + addressId);
=======
    public Response deleteAddress(@PathVariable("id") String email, @PathVariable("addressId") String addressId){
        System.out.println(" post address "+email+" "+addressId);
>>>>>>> a5d6b9746a8060369aa07a14ed58bd04ffdf5525
        Response response;
        try {
            response = new Response("success", "삭제 성공", addressService.delete(addressId, email));
        } catch (Exception e) {
            response = new Response("error", "삭제 실패", e.getMessage());
        }
        return response;
    }

}
