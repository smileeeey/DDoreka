package com.eureka.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table
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
    private int discountPrice;
    private String detailInfo;
    private double rating;
}