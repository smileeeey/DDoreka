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
        List<UseraddressEntity> list = addressRepositoy.findAllByUserId(Integer.toString(id));
        if (list.size() == 0) throw new Exception("주소 정보 없음");
        return list;
    }

    @Override
    @Transactional
    public List<UseraddressEntity> saveaddress(UseraddressEntity useraddressEntity, String loginUser) throws Exception {
        System.out.println("Address : " + useraddressEntity);
        useraddressEntity.setUserId((Integer.toString(userRepository.findTop1ByEmail(loginUser).getId())));
        addressRepositoy.save(useraddressEntity);
        return getaddress(loginUser);
    }

    @Override
    @Transactional
    public List<UseraddressEntity> updateAddress(UseraddressEntity useraddressEntity, String email) throws Exception {
        if(addressRepositoy.existsById(useraddressEntity.getAddressId())==false) throw new  Exception("해당 장바구니 존재 하지 않음");
        String idByemail = Integer.toString(userRepository.findTop1ByEmail(email).getId());
        System.out.println(idByemail+" "+useraddressEntity.getUserId());
        if (!idByemail.equals(useraddressEntity.getUserId())) throw new Exception("장바구니 id가 사용자 아이디와 일치 하지 않습니다.");
        addressRepositoy.save(useraddressEntity);
        return getaddress(email);
    }

    @Override
    @Transactional
    public List<UseraddressEntity> delete(String addressId, String loginUser) throws Exception {

        String userId =Integer.toString( userRepository.findTop1ByEmail(loginUser).getId());

        System.out.println(addressId);
        UseraddressEntity useraddressEntity= addressRepositoy.findById(addressId).get();
        if(useraddressEntity==null) throw new Exception("해당 주소 데이타 없음");
        if(! userId.equals(useraddressEntity.getUserId())) throw  new Exception("유저 아이디와 일치하는 주소 정보 없음");
        addressRepositoy.deleteById(addressId);
        return getaddress(loginUser);
    }

}
