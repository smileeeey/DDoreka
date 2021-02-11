package com.eureka.product.dto;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table

@SqlResultSetMapping(
        name = "SubCategoryMapping",
        classes = @ConstructorResult(
                targetClass = SubCategory.class,
                columns = {
                        @ColumnResult(name = "D4Id", type = String.class),
                        @ColumnResult(name = "D4Name", type = String.class),
                        @ColumnResult(name = "D5Id", type = String.class),
                        @ColumnResult(name = "D5Name", type = String.class),
                }
        )
)


public class Category implements Serializable {
    @Id
    @Column(nullable = false)
    private String id;
    private String name;
    private String parentId;
    private int depth;
}


