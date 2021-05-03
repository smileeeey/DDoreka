package com.eureka.seller.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sun.reflect.CallerSensitive;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

@Table
public class Seller {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String pw;
    private String email;
    private String phone;
    private String bank_company;
    private String bank_account;

}
