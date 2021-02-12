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
public class Reviewlike {
    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false)
    private int reviewId;
    @Column(nullable = false)
    private int userId;
    @Column(columnDefinition = "INT default 0")
    private int likeox;
}
