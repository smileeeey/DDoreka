package com.eureka.product.dto;

import lombok.*;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.SqlResultSetMapping;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubCategory {
    private String D4Id;
    private String D4Name;
    private String D5Id;
    private String D5Name;
}
