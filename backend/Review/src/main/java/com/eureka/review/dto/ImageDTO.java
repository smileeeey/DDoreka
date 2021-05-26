package com.eureka.review.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageDTO {
    private int fileId;
    private char imageType;
    private String imageBytes;
}
