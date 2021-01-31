package com.eureka.product.controller;

import com.eureka.product.dto.Category1;
import com.eureka.product.service.Category1Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category1")
public class Category1Controller {

    private final Category1Service service;

    public Category1Controller(Category1Service category1Service){
        service = category1Service;
    }

    @PostMapping("/addcategory1")
    public Category1 addCategory1(@RequestBody Category1 category1){
        return service.saveCategory1(category1);
    }

    @GetMapping("/categories1")
    public List<Category1> findAllCategories1(){
        return service.getCategories1();
    }

    @GetMapping("/category1/{id}")
    public Category1 findCategory1ById(@PathVariable String id){
        return service.getCategory1ById(id);
    }
}
