package com.eureka.face.controller;

import com.eureka.face.entity.Face;
import com.eureka.face.service.FaceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.hibernate.usertype.UserVersionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"1. Face"})
@RestController
@RequestMapping("/face")
@CrossOrigin
public class FaceController {

    @Autowired
    private FaceService service;

    @ApiOperation(value="감정분석 결과 저장", notes = "구매자의 감정분석 결과 저장", httpMethod = "POST")
    @PostMapping("/add")
    public Face addFace(@ApiParam(value="감정분석 데이터") @RequestBody Face face) {
        return service.saveFace(face);
    }


    @ApiOperation(value="감정분석 결과 조회", notes = "구매자의 모든 감정분석 데이터 반환", httpMethod = "GET")
    @GetMapping("/getAllByUser/{user}")
    public List<Face> getFacesByUser(@ApiParam(value="구매자 고유값") @PathVariable int user) {return service.getFacesByUser(user);}

    @ApiOperation(value="감정분석 결과 삭제(세모)", notes = "구매자 감정분석 개별 데이터 삭제", httpMethod = "DELETE")
    @DeleteMapping("/deleteById/{id}")
    public String deleteFace(@ApiParam(value="감정분석 고유값") @PathVariable int id){
        return service.deleteFaceById(id);
    }

}
