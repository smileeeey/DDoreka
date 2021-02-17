package com.eureka.review.repository;

import com.eureka.review.dto.Reviewlike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

public interface ReviewlikeRepository extends JpaRepository<Reviewlike,Integer> {

    @Transactional
    @Modifying
    @Query(value="delete from reviewlike where review_id = :reviewId and user_id = :userId",nativeQuery = true)
    void deleteByReviewIdAndUserId(@Param("reviewId") int reviewId,@Param("userId") int userId);

    @Transactional
    @Modifying
    @Query(value="delete from reviewlike where review_id = :reviewId",nativeQuery = true)
    void deleteByReviewId(@Param("reviewId") int reviewId);

    @Query(value = "select count(id) from reviewlike where review_id = :reviewId",nativeQuery = true)
    int findCountByReviewId(@Param("reviewId") int reviewId);

    @Query(value="select * from reviewlike where review_id=:reviewId and user_id=:userId",nativeQuery = true)
    List<Reviewlike> findByUserIdAndReviewId(@Param("reviewId") int userId, @Param("userId") int reviewId);
}
