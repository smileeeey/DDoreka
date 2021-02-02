package com.eureka.product.service;

import com.eureka.product.dto.Category;
import com.eureka.product.dto.SubCategory;
import com.eureka.product.repository.CategoryRepository;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @PersistenceContext
    EntityManager em;

    public List<Category> getMainCategories(){
        List<Category> list = repository.findByDepthBetweenOrderByDepthAsc(1,3);
        List<Category> result = new ArrayList<>();

        //category depth별 3중 json으로 만들어서 FE로 넘기기

        return list;
    }

    public List<SubCategory> getSubCategories(String categoryId){

        String q = "select c1.id as D4Id,c1.name as D4Name,c2.id as D5Id,c2.name as D5Name from Category as c1 left outer join Category as c2 on  c1.id = c2.parent_id where c1.parent_id = "+categoryId;

        Query query = em.createNativeQuery(q,"SubCategoryMapping");

        List<SubCategory> list = query.getResultList();



        //category depth별 2중 json으로 만들어서 FE로 넘기기

        return list;
    }

}
