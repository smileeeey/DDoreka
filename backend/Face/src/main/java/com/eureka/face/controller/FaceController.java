package com.eureka.face.controller;

import com.eureka.face.entity.Face;
import com.eureka.face.service.FaceService;
import org.hibernate.usertype.UserVersionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/face")
public class FaceController {

    @Autowired
    private FaceService service;

    @PostMapping("/add")
    public Face addFace(@RequestBody Face face) {
        return service.saveFace(face);
    }

    @GetMapping("/getAllByUsername/{username}")
    public List<Face> findFacesByUsername(@PathVariable String username) {
        return service.getFacesByUsername(username);
    }

    @GetMapping("/getAllByProduct_id/{product_id}")
    public List<Face> findFacesByProduct_id(@PathVariable int product_id){
        return service.getFacesByProduct_id(product_id);
    }

    @DeleteMapping("/deleteById/{id}")
    public String deleteFace(@PathVariable int id){
        return service.deleteFaceById(id);
    }

}
