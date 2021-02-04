package com.eureka.product.controller;

import com.eureka.product.dto.Category;
import com.eureka.product.dto.MainCategory;
import com.eureka.product.dto.SubCategory;
import com.eureka.product.repository.CategoryRepository;
import com.eureka.product.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service){
        this.service = service;
    }

    // main 화면 카테고리 정보 가져오기
    @GetMapping("/mainCategory")
    public List<MainCategory> findMainCategories(){
        return service.getMainCategories();
    }

    // depth=3인 카테고리 id로 하위 카테고리 정보 가져오기
    @GetMapping("/subCategory/{categoryId}")
    public String findSubCategories(@PathVariable String categoryId){

        return service.getSubCategories(categoryId);
    }
}
