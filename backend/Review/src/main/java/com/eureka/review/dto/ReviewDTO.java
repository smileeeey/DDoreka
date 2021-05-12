package com.eureka.review.dto;

import com.eureka.review.entity.Reviewimage;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ReviewDTO {
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
    private List<ReviewimageDTO> images = new ArrayList<>();
}
