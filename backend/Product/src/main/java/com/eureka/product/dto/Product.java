package com.eureka.product.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Product implements Serializable {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private int storeId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String category1Id;

    @Column(nullable = false)
    private String category2Id;

    @Column(nullable = false)
    private String category3Id;

    @Column(nullable = false)
    private String category4Id;

    @Column(nullable = false)
    private String category5Id;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date registerDate;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    @Lob
    private String detailInfo;

    @Column(columnDefinition = "float default 0.0")
    private double rating;

    @Column(columnDefinition = "integer default 0")
    private int reviewCnt;

    @OneToMany(mappedBy = "product")
    @JsonManagedReference
    private List<Productoption> options = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    @JsonManagedReference
    private List<Productimage> images = new ArrayList<>();
}
