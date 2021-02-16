package com.eureka.product.controller;

import com.eureka.product.dto.Category;
import com.eureka.product.dto.Response;
import com.eureka.product.service.CategoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "*")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    // main 화면 카테고리 정보 가져오기
    @GetMapping("/mainCategory")
    public Response findMainCategories() {
        Response response;
        try {
            response = new Response("success", "메인 카테고리 조회 성공", service.getMainCategories());
        } catch (Exception e) {
            response = new Response("error", "메인 카테고리 조회 실패", e.getMessage());
        }

        return response;
    }

    // depth=3인 카테고리 id로 하위 카테고리 정보 가져오기
    @GetMapping("/subCategory/{categoryId}")
    public Response findSubCategories(@PathVariable String categoryId) {
        Response response;
        try {
            response = new Response("success", "하위 카테고리 조회 성공", service.getSubCategories(categoryId));
        } catch (Exception e) {
            response = new Response("error", "하위 카테고리 조회 실패", e.getMessage());
        }
        return response;
    }

    @PostMapping("/add")
    public Response addCategory(@RequestBody Category category) {
        Response response;
        try {
            response = new Response("success", "카테고리 등록 성공", service.saveCategory(category));
        } catch (Exception e) {
            response = new Response("error", "카테고리 등록 실패", e.getMessage());
        }

        return response;
    }

}
