package com.eureka.user.services.Impl;

import com.eureka.user.Entity.UserEntity;
import com.eureka.user.Entity.UseraddressEntity;
import com.eureka.user.dto.Request.RequestLoginUser;
import com.eureka.user.repository.AddressRepositoy;
import com.eureka.user.repository.UserRepository;
import com.eureka.user.services.AuthService;
import com.eureka.user.services.RedisUtil;
import com.eureka.user.services.SaltUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sound.midi.Soundbank;
import javax.transaction.Transactional;
import java.util.List;


@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private SaltUtil saltUtil;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public List<UserEntity> getUsers() {
        return (List<UserEntity>) userRepository.findAll();
    }

    @Override
    public UserEntity getUser(String userEmail, String pw) throws Exception {
        UserEntity user = userRepository.findTop1ByEmail(userEmail);
        if (user == null) throw new Exception("멤버 조회 되지 않음");
        String salt = user.getSalt();
        pw = saltUtil.encodePassword(salt, pw);
        if (!pw.equals(user.getPw())) throw new Exception("비밀번호 틀림");
        return (UserEntity) user;
    }


    @Override
    public void updateUser(UserEntity user) {

    }

    @Override
    public void deleteUser(UserEntity user) {
    }

    @Override
    public boolean isPasswordUuidValidate(String key) {
        String memberId = redisUtil.getData(key);
        return !memberId.equals("");
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
}
