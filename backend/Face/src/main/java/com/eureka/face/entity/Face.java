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
    private int user;
    private float happy;
    private float neutral;
    private float sad;
    private float angry;
    private float fearful;
    private float disgusted;
    private float surprised;
    private int time;
    private String createdAt;

}
