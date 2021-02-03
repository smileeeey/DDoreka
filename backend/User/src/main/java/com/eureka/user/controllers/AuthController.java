//package com.eureka.user.controllers;
//
//
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.provider.OAuth2Authentication;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//@EnableAuthorizationServer
//public class AuthController {
//
//    @RequestMapping(value = { "/user" }, produces = "application/json")
//    public Map<String, Object> user(OAuth2Authentication user) {
//        System.out.println("AuthController");
//        Map<String, Object> userInfo = new HashMap<>();
//        userInfo.put("user", user.getUserAuthentication().getPrincipal());
//        userInfo.put("authorities", AuthorityUtils.authorityListToSet(user.getUserAuthentication().getAuthorities()));
//        return userInfo;
//    }
//
//    @GetMapping(value = "/login")
//    public String loginPage() {
//        return "login";
//    }
//}
