package com.eureka.review.repository;

import com.eureka.review.dto.Reviewimage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface ReviewimageRepository extends JpaRepository<Reviewimage,Integer> {

    @Transactional
    @Modifying
    @Query(value = "delete from reviewimage where review_id = :reviewId",nativeQuery = true)
    void deleteByReviewId(@Param("reviewId") int reviewId);
}
