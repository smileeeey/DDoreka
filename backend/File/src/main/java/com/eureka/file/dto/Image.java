package com.eureka.file.dto;


import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Image {

    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false)
    private String path;
    @Column(nullable = false)
    private String originName;
    @Column(nullable = false)
    private String systemName;
    @Column(columnDefinition = "float default 0.0")
    private int size;
    @Column(nullable = false)
    private String type;

    @Transient
    private byte[] imageBytes;

}
