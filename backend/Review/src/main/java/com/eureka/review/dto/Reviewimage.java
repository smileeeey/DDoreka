package com.eureka.review.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Reviewimage {
    @Id
    private int fileId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="reviewId")
    @JsonBackReference
    private Review review;
}
