package com.eureka.user.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "User")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String pw;
    @Column(nullable = false)
    private String name;
    private String phone;
    private String salt;
    private String enabled="1";

}
