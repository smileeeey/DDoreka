package com.eureka.product.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductNameAndThumbnailDTO {
    private int productId;
    private String name;
    private String thumbnail;
}
