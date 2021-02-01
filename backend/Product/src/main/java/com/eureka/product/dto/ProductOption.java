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
@NoArgsConstructor
@AllArgsConstructor
public class ProductOption implements Serializable {
    @Id
    @GeneratedValue
    private int optionId;
    private int productId;
    private int discountPrice;
    private int price;
    private String name;
    private int stockQuantity;
}
