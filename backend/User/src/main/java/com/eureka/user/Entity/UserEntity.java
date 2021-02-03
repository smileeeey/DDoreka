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
    private String phone;

//    @OneToOne()
//    @JoinColumn(name ="user_id")
//    private UseraddressEntity UseraddressEntity ;

    private String salt;

    private String enabled="1";
}
