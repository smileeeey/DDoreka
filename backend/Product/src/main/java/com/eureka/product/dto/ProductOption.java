package com.eureka.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ProductOption implements Serializable {
    @Id
    @GeneratedValue
    private int optionId;
    @ManyToOne
    @JoinColumn(name="productId")
    private Product product;
    private int discountPrice;
    private int price;
    private String name;
    private int stockQuantity;
    private int fileId;


}
