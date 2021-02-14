package com.eureka.face.service;

import com.eureka.face.entity.Face;
import com.eureka.face.repository.FaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FaceService {

    @Autowired
    private FaceRepository repository;

    public Face saveFace(Face face) {return repository.save(face);}

    public List<Face> getFacesByUsername(String username) {return repository.findAllByUsername(username);}

    public List<Face> getFacesByProduct_id(int product) {return repository.findAllByProduct(product);}

    public String deleteFaceById(int id){
        repository.deleteById(id);
        return "Face removed!!" + id;
    }
}
