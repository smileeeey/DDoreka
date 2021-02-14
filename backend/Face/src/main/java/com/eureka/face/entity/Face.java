package com.eureka.face.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

@Table
public class Face {

    @Id
    @GeneratedValue
    private int id;
    private int product;
    private String username;
    private int happy;
    private int neutral;
    private int sad;
    private int angry;
    private int fearful;
    private int disgusted;
    private int surprised;

}
