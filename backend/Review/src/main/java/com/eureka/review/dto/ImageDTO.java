package com.eureka.review.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ImageDTO {
    private int fileId;
    private char imageType;
    private byte[] imageBytes;
}
