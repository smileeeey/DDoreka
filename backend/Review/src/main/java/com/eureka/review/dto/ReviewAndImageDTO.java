package com.eureka.review.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ReviewAndImageDTO {
    private int id;
    private int orderId;
    private int optionId;
    private int productId;
    private int userId;
    private float rating;
    private Date createdDate;
    private int edited;
    private String title;
    private String content;
    private int reviewlikeCnt;
    private boolean liked;
    private List<ImageDTO> reviewImages;
}
