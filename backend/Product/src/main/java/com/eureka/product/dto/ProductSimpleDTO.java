package com.eureka.product.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductSimpleDTO {
    private int productId;
    private String name;
    private int price;
    private int review_cnt;
    private double rating;
    private int fileId;
    private String thumbnail;
}
