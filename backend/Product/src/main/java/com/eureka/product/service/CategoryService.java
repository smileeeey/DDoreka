package com.eureka.product.service;

import com.eureka.product.dto.Category;
import com.eureka.product.dto.MainCategory;
import com.eureka.product.dto.SubCategory;
import com.eureka.product.repository.CategoryRepository;
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

    public List<MainCategory> getMainCategories() {

        List<Category> list = repository.findByDepthBetweenOrderByIdAsc(1, 3);
        List<MainCategory> result = new ArrayList<>();

        //category depth별 3중 만들어서 FE로 넘기기
        int size = list.size();
        for (int i = 0; i < size; ++i) {
            Category cur = list.get(0);

            switch (cur.getDepth()) {
                case 1:
                    result.add(new MainCategory(cur.getId(), cur.getName(), cur.getDepth(), new ArrayList<MainCategory>()));
                    break;
                case 2:
                    for (int j = 0; j < result.size(); ++j) {
                        if (result.get(j).getId().equals(cur.getParentId())) {
                            result.get(j).getSubCategory().add(new MainCategory(cur.getId(), cur.getName(), cur.getDepth(), new ArrayList<MainCategory>()));
                        }
                    }
                    break;
                case 3:
                    for (int j = 0; j < result.size(); ++j) {
                        for (int k = 0; k < result.get(j).getSubCategory().size(); ++k) {
                            if (result.get(j).getSubCategory().get(k).getId().equals(cur.getParentId())) {
                                result.get(j).getSubCategory().get(k).getSubCategory().add(new MainCategory(cur.getId(), cur.getName(), cur.getDepth(), new ArrayList<MainCategory>()));
                            }
                        }
                    }
                    break;
            }

            list.remove(0);
        }


        return result;
    }

    public List<MainCategory> getSubCategories(String categoryId) {

        String q = "select c1.id as D4Id,c1.name as D4Name, c2.id as D5Id,c2.name as D5Name from category as c1 left outer join category as c2 on c1.id = c2.parent_id where c1.parent_id = " + categoryId + " order by D4Id,D5Id";

        Query query = em.createNativeQuery(q, "SubCategoryMapping");
        List<SubCategory> list = query.getResultList();

        System.out.println(list.get(0));

        List<MainCategory> result = new ArrayList<>();

        String prev = "";
        int idx = -1;

        for (SubCategory subCategory : list) {
            if (subCategory.getD4Id().equals(prev)) {
                result.get(idx).getSubCategory().add(new MainCategory(subCategory.getD5Id(), subCategory.getD5Name(), 5, new ArrayList<MainCategory>()));
            } else {
                List<MainCategory> main = new ArrayList<>();
                main.add(new MainCategory(subCategory.getD5Id(), subCategory.getD5Name(), 5, new ArrayList<MainCategory>()));
                result.add(new MainCategory(subCategory.getD4Id(), subCategory.getD4Name(), 4, main));
                prev = subCategory.getD4Id();
                idx++;
            }
        }
        return result;
    }

    public Category saveCategory(Category category) {
        return repository.save(category);
    }

    public List<Category> getCategories1() {
        return repository.findByDepth(1);
    }
}
