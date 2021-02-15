package com.eureka.user.services.Impl;

import com.eureka.user.Entity.UserEntity;
import com.eureka.user.dto.UserInfo;
import com.eureka.user.repository.UserRepository;
import com.eureka.user.services.AuthService;

import com.eureka.user.services.SaltUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.List;


@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

<<<<<<< HEAD

=======
>>>>>>> a5d6b9746a8060369aa07a14ed58bd04ffdf5525
    @Autowired
    private SaltUtil saltUtil;

    @Override
    public List<UserEntity> getUsers() {
        return (List<UserEntity>) userRepository.findAll();
    }

    @Override
    public String getUserId(String userEmail) throws Exception {

        try {
            return Integer.toString(userRepository.findTop1ByEmail(userEmail).getId());
        }catch (Exception e){
            throw new Exception("해당아이디 조회 실패");
        }
    }

    @Override
    @Transactional
    public void updateUserPhone(UserInfo user) {
        UserEntity updateUser= userRepository.findTop1ByEmail(user.getEmail());
        updateUser.setPhone(user.getPhone());
        userRepository.save(updateUser);
    }

    @Override
    public UserInfo getUser(String userEmail, String pw) throws Exception {
        UserEntity user = userRepository.findTop1ByEmail(userEmail);
        if (user == null) throw new Exception("멤버 조회 되지 않음");
        String salt = user.getSalt();
        pw = saltUtil.encodePassword(salt, pw);
        if (!pw.equals(user.getPw())) throw new Exception("비밀번호 틀림");
        UserInfo userInfo=new UserInfo();
        userInfo.setEmail(userEmail);
        userInfo.setName(user.getName());
        userInfo.setPhone(user.getPhone());
        userInfo.setId(Integer.toString(user.getId()));
        return userInfo;
    }

    @Override
    @Transactional
    public void saveUser(UserEntity user) throws Exception {

        if (userRepository.findTop1ByEmail(user.getEmail()) != null) throw new Exception("해당아이디 존재");

        String pw = user.getPw();
        String salt = saltUtil.genSalt();
        user.setSalt(salt);
        user.setPw(saltUtil.encodePassword(salt, pw));

        userRepository.save(user);
    }

    @Override
    public void updateUser(UserEntity user) throws Exception{
        UserEntity userInfoChanged = userRepository.findTop1ByEmail(user.getEmail());

        String pw = user.getPw();
        String salt = userInfoChanged.getSalt();
        user.setSalt(salt);
        user.setPw(saltUtil.encodePassword(salt, pw));

        userInfoChanged.setPw(user.getPw());
        userInfoChanged.setName(user.getName());
        userInfoChanged.setPhone(user.getPhone());
        userRepository.save(userInfoChanged);
    }

    @Override
    public void deleteUser(UserEntity user) {
        UserEntity userInfoChanged = userRepository.findTop1ByEmail(user.getEmail());
        userRepository.delete(userInfoChanged);
    }


<<<<<<< HEAD
    @Override
    @Transactional
    public void saveUser(UserEntity user) throws Exception {
=======

>>>>>>> a5d6b9746a8060369aa07a14ed58bd04ffdf5525




}
