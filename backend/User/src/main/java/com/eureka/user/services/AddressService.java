package com.eureka.user.services;

import com.eureka.user.Entity.UseraddressEntity;

import java.util.List;

public interface AddressService {
    List<UseraddressEntity> getaddress(String loginUser) throws Exception;

    List<UseraddressEntity> saveaddress(UseraddressEntity useraddressEntity, String loginUser) throws Exception;

    List<UseraddressEntity> delete(String addressId, String loginUser) throws Exception;

    List<UseraddressEntity> updateAddress(UseraddressEntity useraddressEntity, String email) throws Exception;
}
