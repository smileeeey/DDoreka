package com.eureka.review.repository;

import com.eureka.review.dto.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Integer> {
    List<Review> findByProductId(int productId);

    List<Review> findAllByUserId(int userId);
}
