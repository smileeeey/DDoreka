package com.eureka.face.repository;

import com.eureka.face.entity.Face;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FaceRepository extends JpaRepository<Face, Integer> {
    List<Face> findAllByUsername(String username);
    List<Face> findAllByProduct(int product);
}
