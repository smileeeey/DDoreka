package com.eureka.review.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ReviewAndImage {
    Review review;
    List<Reviewimage> images;
}
