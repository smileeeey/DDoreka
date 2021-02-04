package com.eureka.user.dto.Request;

import lombok.Data;

@Data
public class RequestLoginUser {
    private String email;
    private String pw;

    public RequestLoginUser(String email, String password) {
        this.email = email;
        this.pw = password;
    }
}
