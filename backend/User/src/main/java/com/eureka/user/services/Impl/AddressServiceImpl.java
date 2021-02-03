package com.eureka.user.services.Impl;

import com.eureka.user.Entity.UserEntity;
import com.eureka.user.Entity.UseraddressEntity;
import com.eureka.user.repository.AddressRepositoy;
import com.eureka.user.repository.UserRepository;
import com.eureka.user.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepositoy addressRepositoy;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public List<UseraddressEntity> getaddress(String loginUser) throws Exception {
        System.out.println("Email : " + loginUser);
        int id = userRepository.findTop1ByEmail(loginUser).getId();
        System.out.println("id : " + id);
        List<UseraddressEntity> lst = addressRepositoy.findAllByUserId(Integer.toString(id));
        if (lst.size() == 0) throw new Exception("주소 정보 없음");
        return lst;
    }

    @Override
    @Transactional
    public List<UseraddressEntity> putaddress(UseraddressEntity useraddressEntity, String loginUser) throws Exception {
        System.out.println("Address : " + useraddressEntity);
        useraddressEntity.setUserId((Integer.toString(userRepository.findTop1ByEmail(loginUser).getId())));
        addressRepositoy.save(useraddressEntity);
        return getaddress(loginUser);
    }

    @Override
    @Transactional
    public List<UseraddressEntity> delete(String addressId, String loginUser) throws Exception {
        int id = userRepository.findTop1ByEmail(loginUser).getId();
        System.out.println(addressId);
        List<UseraddressEntity> list = addressRepositoy.findAllByUserId(Integer.toString(id));
        if (list.size() == 0) throw new Exception("일치하는 주소 없음");
        System.out.println(123123);
        addressRepositoy.deleteById("1");
        System.out.println(123123);
        return getaddress(loginUser);
    }


}
