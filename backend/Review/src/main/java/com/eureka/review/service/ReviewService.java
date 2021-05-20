package com.eureka.review.service;

import com.eureka.review.dto.ImageDTO;
import com.eureka.review.dto.ReviewAndImageDTO;
import com.eureka.review.entity.Review;
import com.eureka.review.entity.Reviewimage;
import com.eureka.review.entity.Reviewlike;
import com.eureka.review.repository.ReviewRepository;
import com.eureka.review.repository.ReviewimageRepository;
import com.eureka.review.repository.ReviewlikeRepository;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.List;

@Service
@AllArgsConstructor
public class ReviewService {

    private ReviewRepository reviewRepository;
    private ReviewimageRepository reviewimageRepository;
    private ReviewlikeRepository reviewlikeRepository;
    private RestTemplateService<?> restTemplateFile;

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

//    rest로 수정한건데 review list 따로, review image list 따로 주고있었다.
//    public Map<String,Object> getReviewsAll(int productId) throws IOException {
//
//        List<Review> reviews =  reviewRepository.findByProductId(productId);
//
//        List<Integer> fileIds = new ArrayList<>();
//
//        for (int i = 0 ; i < reviews.size() ; ++i){
//            for (int j = 0 ; j < reviews.get(i).getImages().size() ; ++j){
//                fileIds.add(reviews.get(i).getImages().get(j).getFileId());
//            }
//        }
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
//        ResponseEntity<JsonArray> responseEntityFile = restTemplateFile.get(getFilesURL, FileHttpHeaders);
//
//
//        Map<String,Object> answer = new HashMap<>();
//        answer.put("reviews",reviews);
//        answer.put("reviewImages",responseEntityFile.getBody());
//
//
//
//        return answer;
//
//
//    }


    private Map<Integer,ImageDTO> getFilesFromFileServer(List<Integer> fileIds){

        Map<Integer,ImageDTO> result = new HashMap<>();

        Gson gson = new Gson();

        //fileIds들로 file가져오기
        String fileIdJson = gson.toJson(fileIds);
        String getFilesURL = "http://k4d104.p.ssafy.io:8082/file/fileServesss";

        HttpHeaders FileHttpHeaders = new HttpHeaders();
        FileHttpHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        FileHttpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        FileHttpHeaders.set("imagesParam",fileIdJson);

        ResponseEntity<?> responseEntityFile = restTemplateFile.get(getFilesURL, FileHttpHeaders);
        //String filebyte = ((Map<String, String>)((List<ImageDTO>)responseEntityFile.getBody()).get(0)).get("imageBytes");
        List<ImageDTO> images = (List<ImageDTO>)responseEntityFile.getBody();

        System.out.println("images 받아왔어!");

        if(images == null || images.size() == 0) return null;

        System.out.println(images.size());

        ImageDTO imageDTO;
        int fileId;
        //여기 곷야대
        for (int i = 0; i < images.size(); i++) {

            Map<String,Object> imageMap = (Map<String,Object>)images.get(i);
            fileId = (Integer)imageMap.get("fileId");

            imageDTO = ImageDTO.builder()
                    .fileId(fileId)
                    .imageBytes((String)imageMap.get("imageBytes"))
                    .build();

            result.put(fileId,imageDTO);
        }

        return result;
    }


    public List<ReviewAndImageDTO> getReviewsAll(int productId) throws IOException {

        List<ReviewAndImageDTO> result = new ArrayList<>();

        List<Review> reviews =  reviewRepository.findByProductId(productId);

        List<Integer> fileIds = new ArrayList<>();

        for (int i = 0 ; i < reviews.size() ; ++i){
            for (int j = 0 ; j < reviews.get(i).getImages().size() ; ++j){
                fileIds.add(reviews.get(i).getImages().get(j).getFileId());
            }
        }

        //필요한 이미지 정보 모두 가져왔어!
        Map<Integer,ImageDTO> images = getFilesFromFileServer(fileIds);

        List<ImageDTO> curImageList;


        for (int i = 0 ; i < reviews.size() ; ++i){

            curImageList = new ArrayList<>();

            //현재 review에 해당하는 리뷰 이미지를 받아와서 넣기!
            //널체크 해줘야될꺼같음!
            for (int j = 0 ; j < reviews.get(i).getImages().size() ; ++j){
                curImageList.add(images.get(reviews.get(i).getImages().get(j).getFileId()));
            }

            ReviewAndImageDTO reviewAndImageDTO = ReviewAndImageDTO.builder()
                    .id(reviews.get(i).getId())
                    .orderId(reviews.get(i).getOrderId())
                    .optionId(reviews.get(i).getOptionId())
                    .productId(reviews.get(i).getProductId())
                    .userId(reviews.get(i).getUserId())
                    .rating(reviews.get(i).getRating())
                    .createdDate(reviews.get(i).getCreatedDate())
                    .edited(reviews.get(i).getEdited())
                    .title(reviews.get(i).getTitle())
                    .content(reviews.get(i).getContent())
                    .reviewlikeCnt(reviews.get(i).getReviewlikeCnt())
                    .liked(reviews.get(i).isLiked())
                    .reviewImages(curImageList)
                    .build();

            result.add(reviewAndImageDTO);
        }


        return result;
    }

}
