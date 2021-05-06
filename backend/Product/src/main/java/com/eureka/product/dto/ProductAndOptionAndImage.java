package com.eureka.product.dto;

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
