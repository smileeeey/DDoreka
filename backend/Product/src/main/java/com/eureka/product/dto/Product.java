package com.eureka.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    private int storeId;
    private String name;
    private String category1Id;
    private String category2Id;
    private String category3Id;
    private String category4Id;
    private String category5Id;
    private String registerDateTime;
    private String updateDateTime;
    private String detailInfo;
    private double rating;
    private int reviewCnt;

    @OneToMany(mappedBy = "product")
    private List<ProductOption> options = new ArrayList<>();

}