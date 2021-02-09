package com.eureka.user.services;

import com.eureka.user.Entity.UserEntity;
import com.eureka.user.Entity.UseraddressEntity;
import com.eureka.user.dto.Request.RequestLoginUser;

import java.util.List;

public interface AuthService {

    public void saveUser(UserEntity user) throws Exception;


    public List<UserEntity> getUsers();

    public UserEntity getUser(String userEmail, String pw) throws Exception;

    public void updateUser(UserEntity user);

    public void deleteUser(UserEntity user);

    boolean isPasswordUuidValidate(String key);


}
