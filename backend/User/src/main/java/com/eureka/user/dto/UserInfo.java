package com.eureka.user.dto;

import com.eureka.user.Entity.UseraddressEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Data
public class UserInfo {

    private String email;
    private String name;
    private String phone;

}
