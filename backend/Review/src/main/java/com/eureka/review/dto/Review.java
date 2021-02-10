package com.eureka.review.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false)
    private int orderId;
    @Column(nullable = false)
    private int optionId;
    @Column(nullable = false)
    private int productId;
    @Column(nullable = false)
    private int userId;
    @Column(nullable = false)
    private float rating;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(nullable = false,columnDefinition = "INT default 0")
    private int edited;
    @Column(nullable = false)
    private String title;
    @Column(columnDefinition = "varchar(3000)")
    private String content;

    @Transient
    private int reviewlikeCnt;
    @Transient
    private boolean liked;

    @OneToMany(mappedBy = "review",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Reviewimage> images = new ArrayList<>();

}
