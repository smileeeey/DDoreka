package com.eureka.review.dto;

import com.eureka.review.entity.Review;
import com.eureka.review.entity.Reviewimage;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ReviewAndImageDTO {
    Review review;
    List<Reviewimage> images;
}
