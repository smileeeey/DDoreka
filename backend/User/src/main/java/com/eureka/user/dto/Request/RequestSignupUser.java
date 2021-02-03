package com.eureka.user.dto.Request;


import lombok.Data;

@Data
public class RequestSignupUser {
    private String email;
    private String pw;
    private String phone;
    private String address;


    public RequestSignupUser(String email, String pw, String phone, String address) {
        this.email = email;
        this.pw = pw;
        this.phone = phone;
        this.address = address;
    }
}
