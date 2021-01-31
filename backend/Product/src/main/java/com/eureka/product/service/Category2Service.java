package com.eureka.product.service;

import com.eureka.product.dto.Category2;
import com.eureka.product.repository.Category2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Category2Service {

    @Autowired
    private Category2Repository repository;

    public List<Category2> getCategories2(){
        return repository.findAll();
    }
    public Category2 getCategory2ById(String id){
        return repository.findById(id).orElse(null);
    }
    public Category2 getCategory2ByName(String name){
        return repository.findByName(name);
    }

}
