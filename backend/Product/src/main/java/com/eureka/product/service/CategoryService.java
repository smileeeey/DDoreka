package com.eureka.product.service;

import com.eureka.product.dto.Category;
import com.eureka.product.dto.MainCategory;
import com.eureka.product.dto.SubCategory;
import com.eureka.product.repository.CategoryRepository;
import com.google.gson.JsonArray;
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

    public List<MainCategory> getMainCategories(){
        List<Category> list = repository.findByDepthBetweenOrderByDepthAsc(1,3);
        List<MainCategory> result = new ArrayList<>();

        //category depth별 3중 만들어서 FE로 넘기기
        int size = list.size();
        for (int i = 0 ; i < size ; ++i){
            Category cur = list.get(0);

            switch(cur.getDepth()){
                case 1:
                    result.add(new MainCategory(cur.getId(),cur.getName(),cur.getDepth(),new ArrayList<MainCategory>()));
                    break;
                case 2:
                    for (int j = 0 ; j < result.size() ; ++j){
                        if(result.get(j).getId().equals(cur.getParentId())){
                            result.get(j).getSubCategory().add(new MainCategory(cur.getId(),cur.getName(),cur.getDepth(),new ArrayList<MainCategory>()));
                        }
                    }
                    break;
                case 3:
                    for (int j = 0 ; j < result.size() ; ++j){
                        for (int k = 0 ; k < result.get(j).getSubCategory().size() ; ++k){
                            if(result.get(j).getSubCategory().get(k).getId().equals(cur.getParentId())){
                                result.get(j).getSubCategory().get(k).getSubCategory().add(new MainCategory(cur.getId(),cur.getName(),cur.getDepth(),new ArrayList<MainCategory>()));
                            }
                        }
                    }
                    break;
            }

            list.remove(0);
        }



        return result;
    }

    public String getSubCategories(String categoryId){

        String q = "select c1.id as D4Id,c1.name as D4Name,c2.id as D5Id,c2.name as D5Name from category as c1 left outer join category as c2 on c1.id = c2.parent_id where c1.parent_id = "+categoryId;

        Query query = em.createNativeQuery(q,"SubCategoryMapping");

        List<SubCategory> list = query.getResultList();


        //category depth별 2중 json으로 만들어서 FE로 넘기기
        JsonObject result = new JsonObject();
         try{
             JsonArray resultArray = new JsonArray();

             JsonObject D4Object,D5Object;
             JsonArray D5Array;


             if(list.size()!=0) {
                 D4Object = new JsonObject();
                 D5Array = new JsonArray();
                 D4Object.addProperty("D4Id",list.get(0).getD4Id());
                 D4Object.addProperty("D4Name",list.get(0).getD4Name());
                 D5Object = new JsonObject();
                 if(list.get(0).getD5Id()!=null) {
                     D5Object.addProperty("D5Id", list.get(0).getD5Id());
                     D5Object.addProperty("D5Name", list.get(0).getD5Name());
                 }
                 D5Array.add(D5Object);

                 String prevId = list.get(0).getD4Id();

                 for (int i = 1 ; i < list.size() ; ++i){

                     SubCategory cur = list.get(i);

                     if(cur.getD4Id().equals(prevId)){
                         if(cur.getD5Id()!=null){
                             D5Object = new JsonObject();
                             if(cur.getD5Id()!=null) {
                                 D5Object.addProperty("D5Id", cur.getD5Id());
                                 D5Object.addProperty("D5Name", cur.getD5Name());
                             }
                             D5Array.add(D5Object);
                         }
                     }
                     else{
                         D4Object.add("D5", D5Array);
                         resultArray.add(D4Object);

                         D4Object = new JsonObject();
                         D5Array = new JsonArray();
                         D4Object.addProperty("D4Id",cur.getD4Id());
                         D4Object.addProperty("D4Name",cur.getD4Name());
                         D5Object = new JsonObject();
                         if(list.get(i).getD5Id()!=null) {
                             D5Object.addProperty("D5Id", cur.getD5Id());
                             D5Object.addProperty("D5Name", cur.getD5Name());
                         }
                         D5Array.add(D5Object);

                         prevId = cur.getD4Id();
                     }


                 }
                 D4Object.add("D5", D5Array);
                 resultArray.add(D4Object);
             }

             result.add("subCategory",resultArray);
         }catch(Exception e){
             e.printStackTrace();
         }


        return result.toString();
    }

    public Category saveCategory(Category category) {
        return repository.save(category);
    }
}
