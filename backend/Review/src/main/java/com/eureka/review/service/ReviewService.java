package com.eureka.review.service;

import com.eureka.review.dto.Review;
import com.eureka.review.dto.Reviewimage;
import com.eureka.review.dto.Reviewlike;
import com.eureka.review.repository.ReviewRepository;
import com.eureka.review.repository.ReviewimageRepository;
import com.eureka.review.repository.ReviewlikeRepository;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewimageRepository reviewimageRepository;

    @Autowired
    private ReviewlikeRepository reviewlikeRepository;

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


    public void deleteLike(int reviewlikeId) {
        reviewlikeRepository.deleteById(reviewlikeId);
    }

    public void deleteReview(int reviewId) {
        reviewlikeRepository.deleteByReviewId(reviewId);
        reviewRepository.deleteById(reviewId);
    }

    public Review updateReview(Review review) throws Exception {
        if(reviewRepository.save(review) == null)   throw new Exception("해당 리뷰 업데이트 실패");
        return review;
    }

    public List<Review> getReviews(int productId, int userId) {

        List<Review> reviews =  reviewRepository.findAllByProductId(productId);

        for (Review review : reviews) {
            int cnt = reviewlikeRepository.findCountByReviewId(review.getId());
            review.setReviewlikeCnt(cnt);
            int size = reviewlikeRepository.findByUserIdAndReviewId(review.getId(),userId).size();
            System.out.println(review.getId()+" "+userId+" 의 로우개수:"+size);
            if(size>0)   review.setLiked(true);
        }

        return reviews;
    }
}
