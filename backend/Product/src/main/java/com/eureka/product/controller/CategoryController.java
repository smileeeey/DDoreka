package com.eureka.product.controller;

import com.eureka.product.entity.Category;
import com.eureka.product.dto.Response;
import com.eureka.product.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;


@Api(tags = {"1. Category"})
@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "*")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    // main 화면 카테고리 정보 가져오기
    @ApiOperation(value="메인 카테고리 조회", notes = "depth 1~3의 카테고리 정보", httpMethod = "GET")
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
    @ApiOperation(value="하위 카테고리 조회x", notes = "depth=3의 카테고리 고유값으로 그에 포함된 depth 4~5 카테고리 정보", httpMethod = "GET")
    @GetMapping("/subCategory/{categoryId}")
    public Response findSubCategories(@ApiParam(value="depth=3인 카테고리 고유값") @PathVariable String categoryId) {
        Response response;
        try {
            response = new Response("success", "하위 카테고리 조회 성공", service.getSubCategories(categoryId));
        } catch (Exception e) {
            response = new Response("error", "하위 카테고리 조회 실패", e.getMessage());
        }
        return response;
    }

    @ApiOperation(value="카테고리 등록x", notes = "카테고리 정보 등록", httpMethod = "POST")
    @PostMapping("/add")
    public Response addCategory(@ApiParam(value="카테고리 정보") @RequestBody Category category) {
        Response response;
        try {
            response = new Response("success", "카테고리 등록 성공", service.saveCategory(category));
        } catch (Exception e) {
            response = new Response("error", "카테고리 등록 실패", e.getMessage());
        }

        return response;
    }

}
