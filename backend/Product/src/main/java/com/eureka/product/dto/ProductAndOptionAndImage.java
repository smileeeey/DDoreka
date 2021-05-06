package com.eureka.product.dto;

import com.eureka.product.entity.Product;
import com.eureka.product.entity.Productimage;
import com.eureka.product.entity.Productoption;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductAndOptionAndImage {
    Product product;
    List<Productoption> option;
    List<Productimage> image;
}
