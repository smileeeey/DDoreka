package com.eureka.review.controller;

import com.eureka.review.dto.Response;
import com.eureka.review.dto.Review;
import com.eureka.review.service.ReviewService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService service;

    public ReviewController(ReviewService service){
        this.service = service;
    }

    // 상품id주면 리뷰 가져오기

    //review 등록
    @PostMapping(value = "/write", produces = "application/json;charset=utf8")
    public Response addReview(@RequestBody Map<String,Object> param){
        Response response;
        try{
            response = new Response("success", "리뷰 등록 성공", service.saveReview(param));
        }catch(Exception e){
            response = new Response("error", "리뷰 등록 실패", e.getMessage());
        }


        return response;
    }
    //review 좋아요 누르기

    //review 삭제

    //review 수정
}
