package com.eureka.file.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReviewImageDTO {
    private int reviewId;
    private int fileId;
    private byte[] imageBytes;
}
