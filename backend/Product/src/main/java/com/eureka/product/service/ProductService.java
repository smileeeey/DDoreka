package com.eureka.product.service;

import com.eureka.product.dto.Product;
import com.eureka.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> getProducts() {
        return repository.findAll();
    }

    //카테고리별로 상품 정보 가져오기
    public List<Product> getProductsByCategory(String categoryId, int depth){
        switch(depth){
            case 3:
                return repository.findByCategory3Id(categoryId);
            case 4:
                return repository.findByCategory4Id(categoryId);
            case 5:
                return repository.findByCategory5Id(categoryId);
        }
        return null;
    }

    //상품 id로 상세정보 가져오기
    public Product getProductById(int id) {
        return repository.findById(id).orElse(null);
    }

    // 키워드로 상품 검색
    public List<Product> getProductsByName(String categoryId,String keyword){

        //상품 전체에서 키워드로 상품 검색
        if(categoryId.equals("ALL"))
            return repository.findByName(keyword);

        //depth=1인 카테고리 안에서 키워드로 상품 검색
        else
            return repository.findByCategory1IdAndNameLike(categoryId,keyword);
    }





    //상품 정보 입력하기
    public Product saveProduct(Product product){
        return repository.save(product);
    }

    //상품 정보 수정하기
    public Product updateProduct(Product product){
        Product existingProduct = repository.findById(product.getId()).orElse(null);
        existingProduct.setName(product.getName());
        existingProduct.setCategory1Id(product.getCategory1Id());
        existingProduct.setCategory2Id(product.getCategory2Id());
        existingProduct.setCategory3Id(product.getCategory3Id());
        existingProduct.setCategory4Id(product.getCategory4Id());
        existingProduct.setCategory5Id(product.getCategory5Id());
        existingProduct.setRegisterDateTime(product.getRegisterDateTime());
        existingProduct.setUpdateDateTime(product.getUpdateDateTime());
        existingProduct.setDetailInfo(product.getDetailInfo());
        return repository.save(existingProduct);
    }

    //상품 정보 삭제하기
    // [[[ issue1. ]]] fk 관계인 테이블들 어떻게 삭제?
    //  방법1.update cascade 처리
    //  방법2.다른 테이블들 delete 하고 난 뒤 처리
    public void deleteProduct(int id){
        repository.deleteById(id);
    }


}
