package com.eureka.user.Entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "useraddress")
@Data
public class UseraddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private String addressId;

    private String nickname;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "main_address")
    private String mainAddress;

    @Column(name = "sub_address")
    private String subAddress;

    private int zipcode;

    @Column(name = "recipient_phone")
    private String recipientPhone;

    @Column(name = "recipient_name")
    private String recipientName;

    @Column(name = "delivery_msg")
    private String deliveryMsg;


}
