package com.eureka.product.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductOptionInfoDTO {
    private int productId;
    private String productName;
    private int optionId;
    private String optionName;
    private int price;
}
