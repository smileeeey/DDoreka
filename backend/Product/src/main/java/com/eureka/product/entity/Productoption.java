package com.eureka.product.entity;

import com.eureka.product.entity.Product;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@ToString(exclude = "product")
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false, exclude = {"product"})
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
