package com.eureka.user.services.Impl;

import com.eureka.user.dto.User;
import com.eureka.user.repository.UserRepository;
import com.eureka.user.services.AuthService;
import com.eureka.user.services.RedisUtil;
import com.eureka.user.services.SaltUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User getUser(String userEmail, String pw) throws Exception {
        User user = userRepository.findTop1ByEmail(userEmail);
        if (user == null) throw new Exception("멤버 조회 되지 않음");
        String salt = user.getSalt();
        pw = saltUtil.encodePassword(salt, pw);
        if (!pw.equals(user.getPw())) throw new Exception("비밀번호 틀림");
        return (User) user;
    }


    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(User user) {
    }

    @Override
    public boolean isPasswordUuidValidate(String key) {
        String memberId = redisUtil.getData(key);
        return !memberId.equals("");
    }

    @Override
    @Transactional
    public void saveUser(User user) throws Exception {
        if(userRepository.findTop1ByEmail(user.getEmail())!=null) throw new Exception("해당아이디 존재");
        String pw = user.getPw();
        String salt = saltUtil.genSalt();
        user.setSalt(salt);
        user.setPw(saltUtil.encodePassword(salt, pw));
        userRepository.save(user);
    }
}
