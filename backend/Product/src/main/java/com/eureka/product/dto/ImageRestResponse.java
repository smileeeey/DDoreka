package com.eureka.product.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ImageRestResponse {
    List<ImageDTO> images;
}
