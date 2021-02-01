package com.eureka.user.controllers;

import com.eureka.user.dto.Request.RequestLoginUser;
import com.eureka.user.dto.Response;
import com.eureka.user.dto.User;
import com.eureka.user.services.AuthService;
import com.eureka.user.services.CookieUtil;
import com.eureka.user.services.JwtUtil;
import com.eureka.user.services.RedisUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final AuthService authService;

    private final JwtUtil jwtUtil;

    private final CookieUtil cookieUtil;

    private final RedisUtil redisUtil;

    public UserController(AuthService authService, JwtUtil jwtUtil, CookieUtil cookieUtil, RedisUtil redisUtil) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
        this.cookieUtil = cookieUtil;

        this.redisUtil = redisUtil;
    }

    @PostMapping(value = "/signup")
    public Response saveUser(@RequestBody User user){
        try{
            authService.saveUser(user);
            return new Response("success","회원가입을 성공적으로 완료했습니다.",null);
        }
        catch (Exception e){
            return new Response("error","회원가입중 오류",e.getMessage());
        }
    }

    @PostMapping(value = "/login")
    public Response login(@RequestBody RequestLoginUser loginUser, HttpServletRequest req, HttpServletResponse res){
        try{
            final User user =authService.getUser(loginUser.getEmail(),loginUser.getPw());
            final  String token=jwtUtil.generateToken(user);
            final String refreshJwt = jwtUtil.generateRefreshToken(user);

            Cookie accessToken = cookieUtil.createCookie(JwtUtil.ACCESS_TOKEN_NAME, token);
            Cookie refreshToken = cookieUtil.createCookie(JwtUtil.REFRESH_TOKEN_NAME, refreshJwt);

            redisUtil.setDataExpire(refreshJwt, user.getEmail(), JwtUtil.REFRESH_TOKEN_VALIDATION_SECOND);

            res.addCookie(accessToken);
            res.addCookie(refreshToken);

            System.out.println("token : "+ token);
            System.out.println("refrshtoken : "+ refreshJwt);

            return new Response("success","로그인 성공",token);
        }
        catch (Exception e){
            return new Response("error","로그인 실패",e.getMessage());
        }
    }

    @GetMapping("/password/{key}")
    public Response isPasswordUUIdValidate(@PathVariable String key) {
        Response response;
        try {
            if (authService.isPasswordUuidValidate(key))
                response = new Response("success", "정상적인 접근입니다.", null);
            else
                response = new Response("error", "유효하지 않은 Key값입니다.", null);
        } catch (Exception e) {
            response = new Response("error", "유효하지 않은 key값입니다.", null);
        }
        return response;
    }


    @GetMapping(value = "/")
    public List<User> getUsers(){
        System.out.println("getUsers");
        return authService.getUsers();
    }

    @PutMapping(value = "/")
    public void updateUser(@RequestBody User user){
        authService.updateUser(user);
    }

    @DeleteMapping(value = "/")
    public void deleteUser( String id){

    }
}
