package com.eureka.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Searchlog implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String category1Id;

    @Column(nullable = false)
    private String searchWord;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date searchDate;
}
