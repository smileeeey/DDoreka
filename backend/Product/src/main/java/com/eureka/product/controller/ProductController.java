package com.eureka.product.controller;

import com.eureka.product.dto.Product;
import com.eureka.product.dto.Response;
import com.eureka.product.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService productService){
        service = productService;
    }

    @GetMapping("/all")
    public List<Product> findAll(){
        return service.getProducts();
    }

    // 카테고리 선택 시 상품 list 가져오기
    @GetMapping("/findByCategory/{categoryId}/{depth}")
    public List<Product> findByCategory(@PathVariable String categoryId,@PathVariable int depth){
        return service.getProductsByCategory(categoryId,depth);
    }

    // 상품 검색 시 상품 list 가져오기
    @GetMapping("/search/{category1Id}/{keyword}")
    public List<Product> findBySearch(@PathVariable String category1Id,@PathVariable String keyword){
        return service.getProductsByName(category1Id,keyword);
    }

    // 상품 상세 정보 가져오기
    @GetMapping(value = "/detail/{productId}")
    public Product findDetail(@PathVariable int productId){
        return service.getProductById(productId);
    }

    ///////// 필터 적용


    ////////// 추천 상품 가져오기

    ////////// 유사 상품 가져오기

    // 관리자 페이지에서 상품 정보 입력
    @PostMapping(value = "/add",produces = "application/json;charset=utf8")
    public Response saveProductAll(@RequestBody Map<String,Object> param){

        try{
            System.out.println(param);
            System.out.println("hi");
            service.saveProduct(param);
            return new Response("success","상품 정보 저장 완료",null);
        }catch (Exception e){
            return new Response("error","상품 정보 저장 오류",e.getMessage());
        }
    }

}
