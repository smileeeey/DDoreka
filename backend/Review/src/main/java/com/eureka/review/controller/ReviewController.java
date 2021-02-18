package com.eureka.review.controller;

import com.eureka.review.dto.Response;
import com.eureka.review.dto.Reviewlike;
import com.eureka.review.service.ReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/review")
@CrossOrigin(origins = "*")
public class ReviewController {

    private final ReviewService service;

    public ReviewController(ReviewService service){
        this.service = service;
    }

    // 상품id주면 리뷰 가져오기
    @GetMapping(value="/get/{productId}/{userId}")
    public Response getReviewByProductId(@PathVariable int productId, @PathVariable String userId){
        Response response;
        try{
            response = new Response("success", "리뷰 조회 성공", service.getReviews(productId,userId));
        }catch(Exception e){
            response = new Response("error", "리뷰 조회 실패", e.getMessage());
        }
        return response;
    }

    // 유저 id만으로 리뷰가져오기
    @GetMapping(value="/get/{userId}")
    public Response getReviewByUserId(@PathVariable int userId){
        Response response;
        try{

            response = new Response("success", "리뷰 조회 성공", service.getReviewsByUser(userId));
        }catch(Exception e){
            response = new Response("error", "리뷰 조회 실패", e.getMessage());
        }
        return response;
    }


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
    @PostMapping(value="/like", produces = "application/json;charset=utf8")
    public Response addLike(@RequestBody Reviewlike reviewlike){
        Response response;
        try{
            response = new Response("success", "리뷰 좋아요 성공", service.saveLike(reviewlike));
        }catch(Exception e){
            response = new Response("error", "리뷰 좋아요 실패", e.getMessage());
        }
        return response;
    }

    // review 좋아요 취소
    @DeleteMapping(value="/dislike/{reviewId}/{userId}")
    public Response deleteLike(@PathVariable int reviewId, @PathVariable int userId){
        Response response;
        try{
            service.deleteLike(reviewId,userId);
            response = new Response("success", "리뷰 좋아요 삭제 성공", null);
        }catch(Exception e){
            response = new Response("error", "리뷰 좋아요 삭제 실패", e.getMessage());
        }
        return response;
    }

    //review 삭제
    @DeleteMapping(value = "/delete/{reviewId}")
    public Response addReview(@PathVariable int reviewId){
        Response response;
        try{
            service.deleteReview(reviewId);
            response = new Response("success", "리뷰 삭제 성공", null);
        }catch(Exception e){
            response = new Response("error", "리뷰 삭제 실패", e.getMessage());
        }


        return response;
    }

    //review 수정
    @PutMapping(value="/update")
    public Response updaetReview(@RequestBody Map<String,Object> param){
        Response response;
        try{
            response = new Response("success", "리뷰 업데이트 성공", service.updateReview(param));
        }catch(Exception e){
            response = new Response("error", "리뷰 업데이트 실패", e.getMessage());
        }


        return response;
    }


    //관리자 id로 review 가져오기 최근 top5
    @GetMapping(value="/getbyproductids")
    public Response getReviewByProductIds(@RequestParam(value="productIds",required = true) List<Integer> productIds){
        Response response;
        try{
            response = new Response("success", "상품아이디들중 최신 5개의 리뷰 조회 성공", service.getReviewsByProductIds(productIds));
        }catch(Exception e){
            response = new Response("error", "상품아이디들중 최신 5개의 리뷰 조회 실패", e.getMessage());
        }
        return response;
    }


}
