package com.eureka.user.dto;

import com.eureka.user.Entity.UseraddressEntity;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

public class UserInfo {


    private String email;
    private String pw;
    private String phone;



}
