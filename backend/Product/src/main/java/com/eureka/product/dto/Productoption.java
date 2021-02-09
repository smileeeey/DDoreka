package com.eureka.product.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Productoption implements Serializable {

    @Id
    @GeneratedValue
    private int optionId;
    @Column(nullable = false)
    private int discountPrice;
    @Column(nullable = false, columnDefinition = "integer default 0")
    private int price;
    @Column(nullable = false)
    private String name;
    @Column(columnDefinition = "integer default 0")
    private int stockQuantity;
    private int fileId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    @JsonBackReference
    private Product product;
}
