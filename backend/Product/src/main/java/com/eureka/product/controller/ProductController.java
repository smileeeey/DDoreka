package com.eureka.product.controller;

import com.eureka.product.dto.Product;
import com.eureka.product.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService productService){
        service = productService;
    }

    // 카테고리 선택 시 상품 list 가져오기
    @GetMapping("/findByCategory/{categoryId}/{depth}")
    public List<Product> findByCategory(@PathVariable String categoryId,@PathVariable int depth){
        return service.getProductsByCategory(categoryId,depth);
    }

    // 상품 검색 시 상품 list 가져오기
    /*@GetMapping("/search/{categoryId}/{keyword}")
    public List<Product> findBySearch(@PathVariable String categoryId,@PathVariable String keyword){
        return service.getProductsByName(categoryId,keyword);
    }*/

    // 상품 상세 정보 가져오기


    ////////// 추천 상품 가져오기

    ////////// 유사 상품 가져오기

    // 관리자 페이지에서 상품 정보 입력

    // 관리자 페이지에서 상품 정보 수정

}
