package com.eureka.user.controllers;

import com.eureka.user.services.JwtUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/cart")
public class CartController {


    private final JwtUtil jwtUtil;

    public CartController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @GetMapping(value = "/{key}")
    public String getUsers(@PathVariable(value = "key") String key) {
        System.out.println(key + "$$$$$$$$$$$$$$$$$$$$$$");

        String userId = jwtUtil.getUsername(key);
        System.out.println(userId);
        return "";
    }

}
