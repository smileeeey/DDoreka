package com.eureka.product.service;

import com.eureka.product.dto.Category;
import com.eureka.product.dto.Product;
import com.eureka.product.dto.Productimage;
import com.eureka.product.dto.Productoption;
import com.eureka.product.repository.ImageRepository;
import com.eureka.product.repository.ProductRepository;
import com.eureka.product.repository.OptionRepository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OptionRepository optionRepository;

    @Autowired
    private ImageRepository imageRepository;


    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    //카테고리별로 상품 정보 가져오기
    public Page<Product> getProductsByCategory(String categoryId, int depth, Integer page, Integer size) {
        switch (depth) {
            case 3:
                return productRepository.findByCategory3Id(categoryId, PageRequest.of(page,size, Sort.by("id").ascending()));
            case 4:
                return productRepository.findByCategory4Id(categoryId, PageRequest.of(page,size, Sort.by("id").ascending()));
            case 5:
                return productRepository.findByCategory5Id(categoryId, PageRequest.of(page,size, Sort.by("id").ascending()));
        }
        return null;
    }

    //상품 id로 상세정보 가져오기
    public Product getProductById(int productId) {

        Product product = productRepository.findById(productId).orElse(null);

        return product;
    }

    // 키워드로 상품 검색
    public Page<Product> getProductsByName(String category1Id, String keyword, Integer page, Integer size) {
        System.out.println(":::::" + category1Id);
        System.out.println(":::::" + keyword);
        //상품 전체에서 키워드로 상품 검색
        if (category1Id.equals("ALL"))
            return productRepository.findByNameContains(keyword, PageRequest.of(page,size, Sort.by("id").ascending()));

            //depth=1인 카테고리 안에서 키워드로 상품 검색
        else
            return productRepository.findByCategory1IdAndNameContains(category1Id, keyword, PageRequest.of(page,size, Sort.by("id").ascending()));

    }

    //상품 아이디 리스트 조회
    public List<Product> getProductByIds(List<Integer> productIds) {
        List<Product> products = new ArrayList<>();
        for (Integer productId : productIds) {
            products.add(getProductById(productId));
        }
        return products;
    }

    //상품 정보 입력하기
    public void saveProduct(Map<String, Object> param) {

        Gson GSON = new GsonBuilder().setPrettyPrinting().create();

        Product product = GSON.fromJson(param.get("product").toString(), Product.class);

        Date cur = new Date();
        product.setRegisterDate(cur);
        product.setUpdateDate(cur);
        Product productResult = productRepository.save(product);
        System.out.println("상품 저장 완료. 아이디: " + productResult.getId());

        Productoption[] optionArr = GSON.fromJson(param.get("option").toString(), Productoption[].class);
        List<Productoption> options = Arrays.asList(optionArr);

        Productimage[] imageArr = GSON.fromJson(param.get("image").toString(), Productimage[].class);
        List<Productimage> images = Arrays.asList(imageArr);

        for (int i = 0; i < options.size(); ++i) {
            options.get(i).setProduct(productResult);
        }
        for (int i = 0; i < images.size(); ++i) {
            images.get(i).setProduct(productResult);
        }

        optionRepository.saveAll(options);
        imageRepository.saveAll(images);
    }

    //상품 정보 수정하기
    public Product updateProduct(Map<String,Object> param) {

        Product product = (Product)param.get("product");
        Productimage images = (Productimage)param.get("image");
        List<Integer> deleteImages = (List<Integer>)param.get("deleteImages");
        Productoption options = (Productoption)param.get("option");

        List<Productimage> existingImages = imageRepository.findByProductId(product.getId());


        Product existingProduct = productRepository.findById(product.getId()).orElse(null);
        existingProduct.setName(product.getName());
        existingProduct.setCategory1Id(product.getCategory1Id());
        existingProduct.setCategory2Id(product.getCategory2Id());
        existingProduct.setCategory3Id(product.getCategory3Id());
        existingProduct.setCategory4Id(product.getCategory4Id());
        existingProduct.setCategory5Id(product.getCategory5Id());
        existingProduct.setUpdateDate(product.getUpdateDate());
        existingProduct.setDetailInfo(product.getDetailInfo());

        //사진 데이터 삭제
        for (Integer imageId : deleteImages) {
            imageRepository.deleteById(imageId);
        }




        return productRepository.save(existingProduct);
    }

    //상품 정보 삭제하기
    // [[[ issue1. ]]] fk 관계인 테이블들 어떻게 삭제?
    //  방법1.update cascade 처리
    //  방법2.다른 테이블들 delete 하고 난 뒤 처리
    public void deleteProduct(int productId) {


        productRepository.deleteById(productId);

    }

    //판매자의 모든 상품 가져오기
    public List<Product> getProductsByStore(int storeId) {
        return productRepository.findByStoreId(storeId);
    }


    public Map<String,List<Product>> getLatestProduct(List<Category> category1) {
        Map<String,List<Product>> map = new HashMap<>();

        for (Category category : category1) {
            map.put(category.getId(),productRepository.findTop23ByCategory1IdOrderByRegisterDateDesc(category.getId()));
        }

        return map;
    }
}
