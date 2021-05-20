package com.eureka.review.controller;

import com.eureka.review.dto.Response;
import com.eureka.review.dto.ReviewAndImageDTO;
import com.eureka.review.entity.Reviewlike;
import com.eureka.review.service.ReviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(tags = {"1. Review"})
@RestController
@RequestMapping("/review")
@CrossOrigin(origins = "*")
public class ReviewController {

    private final ReviewService service;

    @Autowired
    public ReviewController(ReviewService service){
        this.service = service;
    }

    // 상품id주면 리뷰 가져오기 RestTemplate
    @ApiOperation(value="리뷰 전부 가져오기 RestTemplate x", notes = "상품id에 해당하는 리뷰, 리뷰 좋아요, 리뷰 사진까지 한번에 똭!", httpMethod = "GET")
    @GetMapping(value="/getReviewsAll/{productId}")
    public ResponseEntity<?> getReviewsAll(@ApiParam(value="상품 고유값") @PathVariable int productId){
        List<ReviewAndImageDTO> result = null;
        try{
            result = service.getReviewsAll(productId);
            return new ResponseEntity(result,null, HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(result,null,HttpStatus.BAD_REQUEST); //이거맞나?
        }
    }

    // 상품id주면 리뷰 가져오기
    @ApiOperation(value="리뷰 정보 조회", notes = "상품id에 해당하는 리뷰 데이터 반환, 로그인 되어있다면 사용자가 리뷰들에 좋아요 누른 여부 true로 함께 반환", httpMethod = "GET")
    @GetMapping(value="/get/{productId}/{userId}")
    public Response getReviewByProductId(@ApiParam(value="상품 고유값") @PathVariable int productId, @ApiParam(value="사용자 고유값") @PathVariable String userId){
        Response response;
        try{
            response = new Response("success", "리뷰 조회 성공", service.getReviews(productId,userId));
        }catch(Exception e){
            response = new Response("error", "리뷰 조회 실패", e.getMessage());
        }
        return response;
    }

    // 유저 id만으로 리뷰가져오기
    @ApiOperation(value="사용자별 리뷰 정보 조회", notes = "사용자가 작성한 리뷰 데이터 반환", httpMethod = "GET")
    @GetMapping(value="/get/{userId}")
    public Response getReviewByUserId(@ApiParam(value="사용자 고유값") @PathVariable int userId){
        Response response;
        try{

            response = new Response("success", "리뷰 조회 성공", service.getReviewsByUser(userId));
        }catch(Exception e){
            response = new Response("error", "리뷰 조회 실패", e.getMessage());
        }
        return response;
    }


    //review 등록
    @ApiOperation(value="리뷰 등록", notes = "리뷰 데이터 등록", httpMethod = "POST")
    @PostMapping(value = "/write", produces = "application/json;charset=utf8")
    public Response addReview(@ApiParam(value="리뷰 데이터") @RequestBody Map<String,Object> param){
        Response response;
        try{
            response = new Response("success", "리뷰 등록 성공", service.saveReview(param));
        }catch(Exception e){
            response = new Response("error", "리뷰 등록 실패", e.getMessage());
        }
        return response;
    }

    //review 좋아요 누르기
    @ApiOperation(value="리뷰 좋아요 등록", notes = "리뷰 좋아요 데이터 등록", httpMethod = "POST")
    @PostMapping(value="/like", produces = "application/json;charset=utf8")
    public Response addLike(@ApiParam(value="리뷰 좋아요 데이터") @RequestBody Reviewlike reviewlike){
        Response response;
        try{
            response = new Response("success", "리뷰 좋아요 성공", service.saveLike(reviewlike));
        }catch(Exception e){
            response = new Response("error", "리뷰 좋아요 실패", e.getMessage());
        }
        return response;
    }

    // review 좋아요 취소
    @ApiOperation(value="리뷰 좋아요 취소", notes = "해당 reviewId, userId에 해당하는 리뷰 좋아요 삭제(추후 수정 필요)", httpMethod = "DELETE")
    @DeleteMapping(value="/dislike/{reviewId}/{userId}")  //여기 사실 reviewLikeId만 있으면 userId 필요 없음! 프론트에서 이미 걸러졌을 로직
    public Response deleteLike(@ApiParam(value="리뷰 고유값") @PathVariable int reviewId,@ApiParam(value="사용자 고유값")  @PathVariable int userId){
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
    @ApiOperation(value="리뷰 삭제(세모)", notes = "해당 reviewId에 해당하는 리뷰 삭제(cascade로 삭제되는지 확인 필요)", httpMethod = "DELETE")
    @DeleteMapping(value = "/delete/{reviewId}")
    public Response addReview(@ApiParam(value="리뷰 고유값") @PathVariable int reviewId){
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
    @ApiOperation(value="리뷰 수정(세모)", notes = "해당 reviewId에 해당하는 리뷰 데이터 수정", httpMethod = "PUT")
    @PutMapping(value="/update")
    public Response updaetReview(@ApiParam(value="리뷰 데이터") @RequestBody Map<String,Object> param){
        Response response;
        try{
            response = new Response("success", "리뷰 업데이트 성공", service.updateReview(param));
        }catch(Exception e){
            response = new Response("error", "리뷰 업데이트 실패", e.getMessage());
        }


        return response;
    }


}
