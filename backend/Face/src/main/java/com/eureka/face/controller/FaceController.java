package com.eureka.face.controller;

import com.eureka.face.entity.Face;
import com.eureka.face.service.FaceService;
import org.hibernate.usertype.UserVersionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/face")
@CrossOrigin
public class FaceController {

    @Autowired
    private FaceService service;

    @PostMapping("/add")
    public Face addFace(@RequestBody Face face) {
        return service.saveFace(face);
    }


    @GetMapping("/getAllByUser/{user}")
    public List<Face> getFacesByUser(@PathVariable int user) {return service.getFacesByUser(user);}

    @DeleteMapping("/deleteById/{id}")
    public String deleteFace(@PathVariable int id){
        return service.deleteFaceById(id);
    }

}
