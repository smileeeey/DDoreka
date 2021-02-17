package com.eureka.product.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
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
    @CreationTimestamp
    private Date searchDate;

}
