package com.eureka.product.dto;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Category1 implements Serializable {
    @Id
    @GeneratedValue
    private String id;
    private String name;
}
