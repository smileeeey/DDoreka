package com.eureka.seller.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int sellerId;
    private String phone;
    private String name;
    private String main_address;
    private String sub_address;
    private int zipcode;

}
