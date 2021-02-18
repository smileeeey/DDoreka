package com.eureka.face.service;

import com.eureka.face.entity.Face;
import com.eureka.face.repository.FaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FaceService {

    @Autowired
    private FaceRepository repository;

    public Face saveFace(Face face) {return repository.save(face);}



    public String deleteFaceById(int id){
        repository.deleteById(id);
        return "Face removed!!" + id;
    }

    public List<Face> getFacesByUser(int user) {
        List<Integer> productIds = repository.findByUserGroupByProduct(user);

        List<Face> faces = new ArrayList<>();
        for (Integer productId : productIds) {
            faces.add(repository.findTop1ByProductAndUserOrderByCreatedAtDesc(productId,user));
        }
        return faces;
    }
}
