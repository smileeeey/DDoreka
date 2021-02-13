package com.eureka.user.repository;


import com.eureka.user.Entity.UseraddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepositoy extends JpaRepository<UseraddressEntity, String> {
    List<UseraddressEntity> findAllByUserId(String userId);

    UseraddressEntity findFirstByAddressIdAndUserId(int addressId, int userId);


}
