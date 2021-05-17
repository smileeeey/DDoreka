package com.eureka.user.repository;

import com.eureka.user.Entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<CartEntity,String> {
    CartEntity findTopByUserIdAndProductIdAndOptionId(String userid,String productid,String optionid);
    List<CartEntity> findAllByUserId(String userId);
    void deleteAllByUserId(String userid);

    void deleteAllByIdIn(List<Integer> cartIds);
}
