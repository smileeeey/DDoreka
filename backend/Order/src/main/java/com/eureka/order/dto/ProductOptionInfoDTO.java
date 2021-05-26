package com.eureka.order.dto;

import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductOptionInfoDTO {
    private int productId;
    private String productName;
    private int optionId;
    private String optionName;
    private int price;
    private String thumbnail;
}