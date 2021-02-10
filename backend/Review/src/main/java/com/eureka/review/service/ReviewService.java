package com.eureka.review.service;

import com.eureka.review.dto.Review;
import com.eureka.review.dto.Reviewimage;
import com.eureka.review.repository.ReviewRepository;
import com.eureka.review.repository.ReviewimageRepository;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewimageRepository reviewimageRepository;

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
}
