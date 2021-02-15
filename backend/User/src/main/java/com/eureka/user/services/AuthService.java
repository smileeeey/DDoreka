package com.eureka.user.services;

import com.eureka.user.Entity.UserEntity;
import com.eureka.user.dto.UserInfo;

import java.util.List;

public interface AuthService {

    public void saveUser(UserEntity user) throws Exception;


    public List<UserEntity> getUsers();

<<<<<<< HEAD
    public UserEntity getUser(String userEmail, String pw) throws Exception;
=======
    public UserInfo getUser (String userEmail, String pw) throws Exception;
>>>>>>> a5d6b9746a8060369aa07a14ed58bd04ffdf5525

    public void updateUser(UserEntity user) throws Exception;

    public void deleteUser(UserEntity user);



    String getUserId(String userEmail) throws Exception;

    void updateUserPhone(UserInfo user);
}
