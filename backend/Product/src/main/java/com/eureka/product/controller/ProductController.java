package com.eureka.product.controller;

import com.eureka.product.dto.Response;
import com.eureka.product.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService productService) {
        service = productService;
    }

    @GetMapping("/all")
    public Response findAll() {
        Response response;
        try {
            response = new Response("success", "상품 전체 조회 성공", service.getProducts());
        } catch (Exception e) {
            response = new Response("error", "상품 전체 조회 실패", e.getMessage());
        }

        return response;
    }

    // 카테고리 선택 시 상품 list 가져오기
    @GetMapping("/findByCategory/{categoryId}/{depth}")
    public Response findByCategory(@PathVariable String categoryId, @PathVariable int depth) {
        Response response;
        try {
            response = new Response("success", "카테고리별 상품 조회 성공", service.getProductsByCategory(categoryId, depth));
        } catch (Exception e) {
            response = new Response("error", "카테고리별 상품 조회 실패", e.getMessage());
        }

        return response;
    }

    // 상품 검색 시 상품 list 가져오기
    @GetMapping("/search/{category1Id}/{keyword}")
    public Response findBySearch(@PathVariable String category1Id, @PathVariable String keyword) {
        Response response;
        try {
            response = new Response("success", "상품 검색 성공", service.getProductsByName(category1Id, keyword));
        } catch (Exception e) {
            response = new Response("error", "상품 검색 실패", e.getMessage());
        }

        return response;
    }

    // 상품 상세 정보 가져오기
    @GetMapping(value = "/detail/{productId}")
    public Response findDetail(@PathVariable int productId) {
        Response response;
        try {
            response = new Response("success", "상품 상세 조회 성공", service.getProductById(productId));
        } catch (Exception e) {
            response = new Response("error", "상품 상세 조회 실패", e.getMessage());
        }

        return response;
    }

    ///////// 필터 적용


    ////////// 추천 상품 가져오기

    ////////// 유사 상품 가져오기

    // 관리자 페이지에서 상품 정보 입력
    @PostMapping(value = "/add", produces = "application/json;charset=utf8")
    public Response saveProductAll(@RequestBody Map<String, Object> param) {
        try {
            service.saveProduct(param);
            return new Response("success", "상품 정보 저장 완료", null);
        } catch (Exception e) {
            return new Response("error", "상품 정보 저장 오류", e.getMessage());
        }
    }

}
