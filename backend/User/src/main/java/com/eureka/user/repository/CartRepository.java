package com.eureka.user.repository;

import com.eureka.user.Entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
<<<<<<< HEAD
public interface CartRepository extends JpaRepository<CartEntity, String> {


=======
public interface CartRepository extends JpaRepository<CartEntity,String> {
    CartEntity findTopByUserIdAndProductIdAndOptionId(String userid,String productid,String optionid);
    List<CartEntity> findAllByUserId(String userId);
    void deleteAllByUserId(String userid);
>>>>>>> a5d6b9746a8060369aa07a14ed58bd04ffdf5525
}
