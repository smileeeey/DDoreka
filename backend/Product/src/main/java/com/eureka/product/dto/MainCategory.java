package com.eureka.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MainCategory {
    private String id;
    private String name;
    private int depth;
    private List<MainCategory> subCategory;
}
