package com.eureka.user.services;

import com.eureka.user.dto.User;

import java.util.List;

public interface AuthService {

    public void saveUser(User user) throws Exception;


    public List<User> getUsers ();

    public User getUser (String userEmail, String pw) throws Exception;

    public void updateUser(User user);

    public void deleteUser(User user);

    boolean isPasswordUuidValidate(String key);
}
