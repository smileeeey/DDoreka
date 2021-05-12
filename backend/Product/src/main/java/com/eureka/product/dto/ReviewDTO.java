package com.eureka.product.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
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
