package com.eureka.review.service;

import com.eureka.review.dto.ImageDTO;
import com.eureka.review.dto.RestResponse;
import com.eureka.review.dto.ReviewDTO;
import com.eureka.review.entity.Review;
import com.eureka.review.entity.Reviewimage;
import com.eureka.review.entity.Reviewlike;
import com.eureka.review.repository.ReviewRepository;
import com.eureka.review.repository.ReviewimageRepository;
import com.eureka.review.repository.ReviewlikeRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import springfox.documentation.spring.web.json.Json;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;
import java.util.List;

@Service
public class ReviewService {

    private ReviewRepository reviewRepository;
    private ReviewimageRepository reviewimageRepository;
    private ReviewlikeRepository reviewlikeRepository;
    private RestTemplateService<JsonArray> restTemplateFile;


    @Autowired
    public ReviewService(ReviewRepository reviewRepository, ReviewimageRepository reviewimageRepository, ReviewlikeRepository reviewlikeRepository, RestTemplateService<JsonArray> restTemplateFile){
        this.reviewRepository = reviewRepository;
        this.reviewimageRepository = reviewimageRepository;
        this.reviewlikeRepository = reviewlikeRepository;
        this.restTemplateFile = restTemplateFile;
    }

    public Review saveReview(Map<String,Object> param){

        Gson gson = new Gson();

        JsonParser jsonParser = new JsonParser();

        Review review = gson.fromJson(param.get("review").toString(),Review.class);

        Date cur = new Date();
        review.setCreatedDate(cur);
        Review reviewResult = reviewRepository.save(review);
        System.out.println("상품 저장 완료. 아이디: "+reviewResult.getId());

        Reviewimage[] imageArr = gson.fromJson(param.get("image").toString(),Reviewimage[].class);
        List<Reviewimage> images = Arrays.asList(imageArr);

        for (int i = 0 ; i < images.size() ; ++i){
            images.get(i).setReview(reviewResult);
        }

        reviewimageRepository.saveAll(images);

        return reviewResult;
    }

    public Reviewlike saveLike(Reviewlike reviewlike) {
        return reviewlikeRepository.save(reviewlike);
    }


    public void deleteLike(int reviewId, int userId) {
        reviewlikeRepository.deleteByReviewIdAndUserId(reviewId,userId);
    }


    public void deleteReview(int reviewId) {
        reviewlikeRepository.deleteByReviewId(reviewId);
        reviewRepository.deleteById(reviewId);
    }

    public Review updateReview(Map<String,Object> param) throws Exception {

        Review review = (Review)param.get("review");
        List<Integer> deletedFileId = (List)param.get("deleteFileId");
        List<Integer> addedFileId = (List)param.get("addedFileId");

        if(reviewRepository.save(review) == null)   throw new Exception("리뷰 업데이트 실패");

        //file 서버에서도 삭제 해야댐! 프론트에서 요청 보내기!!
        for (Integer fileId : deletedFileId) {
            reviewimageRepository.deleteById(fileId);
        }

        for (Integer fileId : addedFileId) {
            reviewimageRepository.save(new Reviewimage(fileId,review));
        }

        return review;
    }

    public List<Review> getReviews(int productId, String userId) {

        List<Review> reviews =  reviewRepository.findByProductId(productId);

        for (Review review : reviews) {
            int cnt = reviewlikeRepository.findCountByReviewId(review.getId());
            review.setReviewlikeCnt(cnt);

            if(!userId.equals("notLogin")){
                int userIdInt = Integer.parseInt(userId);
                int size = reviewlikeRepository.findByUserIdAndReviewId(review.getId(),userIdInt).size();
                System.out.println(review.getId()+" "+userIdInt+" 의 로우개수:"+size);
                if(size>0)   review.setLiked(true);
            }

        }

        return reviews;
    }

    public List<Review> getReviewsByUser(int userId) {
        List<Review> reviews =  reviewRepository.findAllByUserId(userId);

        for (Review review : reviews) {
            int cnt = reviewlikeRepository.findCountByReviewId(review.getId());
            review.setReviewlikeCnt(cnt);
        }

        return reviews;
    }

//    public String getReviewsAll(int productId) throws IOException {
//
//        List<Review> reviews =  reviewRepository.findByProductId(productId);
//        List<Integer> fileIds = new ArrayList<>();
//        Review review;
//        for (int j=0; j<reviews.size() ; ++j) {
//            review = reviews.get(j);
//            int cnt = reviewlikeRepository.findCountByReviewId(review.getId());
//            review.setReviewlikeCnt(cnt);
//
//            if(review.getImages().size()>=0){
//                for (int i = 0 ; i < review.getImages().size() ; ++i){
//                    fileIds.add(review.getImages().get(i).getFileId());
//                }
//            }
//
////            if(!userId.equals("notLogin")){
////                int userIdInt = Integer.parseInt(userId);
////                int size = reviewlikeRepository.findByUserIdAndReviewId(review.getId(),userIdInt).size();
////                System.out.println(review.getId()+" "+userIdInt+" 의 로우개수:"+size);
////                if(size>0)   review.setLiked(true);
////            }
//
//        }
//        System.out.println("hihi "+reviews.size());
//
//        String getFilesURL = "http://localhost:8082/file/fileServesss";
//
//        Gson gson = new Gson();
//
//        String imageJson = gson.toJson(fileIds);
//
//        HttpHeaders FileHttpHeaders = new HttpHeaders();
//        FileHttpHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
//        FileHttpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
//        FileHttpHeaders.set("imagesParam",imageJson);
//
//        System.out.println("image list to json:");
//        System.out.println(imageJson);
//
//        ResponseEntity<JsonArray> responseEntityFile = restTemplateFile.get(getFilesURL, FileHttpHeaders);
//
//        RestResponse restResponse = new RestResponse(reviews,responseEntityFile.getBody().toString());
//
//        String result = gson.toJson(restResponse);
//
//        return result;
//
//
//    }

    public Map<String,Object> getReviewsAll(int productId) throws IOException {
        System.out.println(productId);



        List<Review> reviews =  reviewRepository.findByProductId(productId);


        List<Integer> fileIds = new ArrayList<>();

        for (int i = 0 ; i < reviews.size() ; ++i){
            for (int j = 0 ; j < reviews.get(i).getImages().size() ; ++j){
                fileIds.add(reviews.get(i).getImages().get(j).getFileId());
            }
        }

        String getFilesURL = "http://localhost:8082/file/fileServesss";

        Gson gson = new Gson();

        String imageJson = gson.toJson(fileIds);

        HttpHeaders FileHttpHeaders = new HttpHeaders();
        FileHttpHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        FileHttpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        FileHttpHeaders.set("imagesParam",imageJson);

        ResponseEntity<JsonArray> responseEntityFile = restTemplateFile.get(getFilesURL, FileHttpHeaders);


        Map<String,Object> answer = new HashMap<>();
        answer.put("reviews",reviews);
        answer.put("reviewImages",responseEntityFile.getBody());



        return answer;


    }
}
