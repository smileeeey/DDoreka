package com.eureka.product.dto;

import com.eureka.product.entity.Productimage;
import com.eureka.product.entity.Productoption;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private int id;
    private int storeId;
    private String name;
    private String category1Id;
    private String category2Id;
    private String category3Id;
    private String category4Id;
    private String category5Id;
    private Date registerDate;
    private Date updateDate;
    private String detailInfo;
    private double rating;
    private int reviewCnt;
}
