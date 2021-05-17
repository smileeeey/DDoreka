package com.eureka.face.controller;

import com.eureka.face.dto.ProductSimpleDTO;
import com.eureka.face.entity.Face;
import com.eureka.face.service.FaceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @ApiOperation(value="tmp module for product info with face info", notes = "test", httpMethod = "GET")
    @GetMapping("/product/{userId}")
    public ResponseEntity<?> getFaceProductByUse (@ApiParam(value="구매자 고유값") @PathVariable int userId) {

        MultiValueMap<String, Object> header = new LinkedMultiValueMap<>();

        try {
            List<Face> faces = service.getFacesByUser(userId);

            List<Integer> productIds = new ArrayList<>();
            for(Face f : faces) {
                productIds.add(f.getProduct());
            }
            List<ProductSimpleDTO> productSimples = service.getProductSimpleByProductIds(productIds);
            Map<String, Object> result = new HashMap<>();
            result.put("faces", faces);
            result.put("products", productSimples);

            return new ResponseEntity(result, header, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(header, HttpStatus.BAD_REQUEST);
        }

    }

}
