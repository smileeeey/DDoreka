package com.eureka.product.service;

import com.eureka.product.dto.Category1;
import com.eureka.product.repository.Category1Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Category1Service {
    @Autowired
    private Category1Repository repository;

    public Category1 saveCategory1(Category1 category1){
        return repository.save(category1);
    }
    public List<Category1> saveCategories1(List<Category1> categories){
        return repository.saveAll(categories);
    }

    public List<Category1> getCategories1(){
        return repository.findAll();
    }
    public Category1 getCategory1ById(String id){
        return repository.findById(id).orElse(null);
    }
    public Category1 getCategory1ByName(String name){
        return repository.findByName(name);
    }

    public String deleteCategory(String id){
        repository.deleteById(id);
        return "category removed!!"+id;
    }
    public Category1 updateCategory(Category1 category1){
        Category1 existingCategory = repository.findById(category1.getId()).orElse(null);
        existingCategory.setName(category1.getName());
        return repository.save(existingCategory);
        //if(existingCategory == null)
        //    repository.save(category1);
    }
}
