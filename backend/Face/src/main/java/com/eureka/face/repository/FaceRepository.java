package com.eureka.face.repository;

import com.eureka.face.entity.Face;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FaceRepository extends JpaRepository<Face, Integer> {
    List<Face> findAllByUser(int user);
    List<Face> findAllByProduct(int product);

    @Query(value = "select distinct product from face where user = ?1",nativeQuery = true)
    List<Integer> findByUserGroupByProduct(int user);

    Face findTop1ByProductAndUserOrderByCreatedAtDesc(Integer productId, int user);
}
