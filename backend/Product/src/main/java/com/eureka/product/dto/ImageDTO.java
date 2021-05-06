package com.eureka.product.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ImageDTO {
    private int fileId;
    private char imageType;
    private int productId;
}
