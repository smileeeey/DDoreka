package com.eureka.product.dto;

import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OptionDTO {
    private int optionId;
    private int discountPrice;
    private int price;
    private String name;
    private int stockQuantity;
    private int fileId;
    private int productId;
}
