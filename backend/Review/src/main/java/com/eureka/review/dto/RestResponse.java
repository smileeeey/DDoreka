package com.eureka.review.dto;

import com.eureka.review.entity.Review;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RestResponse {
    List<Review> reviews;
    String images;
}
