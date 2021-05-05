package com.eureka.seller.controller;

import com.eureka.seller.entity.Seller;
import com.eureka.seller.service.SellerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hibernate.usertype.UserVersionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"1. seller"})
@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private SellerService service;

    @ApiOperation(value="셀러 등록", notes = "셀러의 정보를 등록하고 리턴한다", httpMethod = "POST")
    @PostMapping("/add")
    public Seller addSeller(@RequestBody Seller seller) {
        return service.saveSeller(seller);
    }

    @ApiOperation(value="입력받은 모든 셀러 등록", notes = "입력받은 셀러의 정보를 저장하고, 리턴한다", httpMethod = "POST")
    @PostMapping("/addAll")
    public List<Seller> addSellers(@RequestBody List<Seller> sellers) {
        return service.saveSellers(sellers);
    }

    @ApiOperation(value="모든 셀러를 조회", notes = "존재하는 모든 셀러 정보를 리턴한다", httpMethod = "GET")
    @GetMapping("/getAll")
    public List<Seller> findAllSellers() {
        return service.getSellers();
    }

    @ApiOperation(value="id로 셀러를 조회", notes="특정 아이디를 입력받아 해당 셀러의 정보를 리턴한다", httpMethod = "GET")
    @GetMapping("/getById/{id}")
    public Seller findSellerById(@PathVariable int id) {
        return service.getSellerById(id);
    }

    @ApiOperation(value="email로 셀러를 조회", notes="특정 이메일을 입력받아 해당 셀러의 정보를 리턴한다", httpMethod = "GET")
    @GetMapping("/getByEmail/{email}")
    public Seller findSellerByEmail(@PathVariable String email) {
        return service.getSellerByEmail(email);
    }

    @ApiOperation(value="셀러 정보 수정", notes="셀러 정보를 입력받아 해당 셀러 정보를 수정한다", httpMethod = "PUT")
    @PutMapping("/update")
    public Seller updateSeller(@RequestBody Seller seller) {
        return service.updateSeller(seller);
    }

    @ApiOperation(value="id로 셀러 정보 삭제", notes="셀러 아이디를 입력받아 해당 셀러의 정보를 삭제한다", httpMethod = "DELETE")
    @DeleteMapping("/deleteById/{id}")
    public String deleteSeller(@PathVariable int id) {
        return service.deleteSeller(id);
    }
}
