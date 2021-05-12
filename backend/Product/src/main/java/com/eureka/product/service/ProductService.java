package com.eureka.product.service;

import com.eureka.product.dto.*;
import com.eureka.product.entity.Category;

import com.eureka.product.entity.Product;
import com.eureka.product.entity.Productimage;
import com.eureka.product.entity.Productoption;
import com.eureka.product.repository.ImageRepository;
import com.eureka.product.repository.ProductRepository;
import com.eureka.product.repository.OptionRepository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.spring.web.json.Json;

import java.awt.*;
import java.lang.reflect.Type;
import java.util.*;
import java.util.List;


@Service
public class ProductService {

    private ProductRepository productRepository;
    private OptionRepository optionRepository;
    private ImageRepository imageRepository;
    private RestTemplateService<JsonArray> restTemplateFile;
    private RestTemplateService<JsonArray> restTemplateReview;

    @Autowired
    public ProductService(ProductRepository productRepository, OptionRepository optionRepository, ImageRepository imageRepository, RestTemplateService<JsonArray> restTemplateService, RestTemplateService<JsonArray> restTemplateReview){
        this.productRepository = productRepository;
        this.optionRepository = optionRepository;
        this.imageRepository = imageRepository;
        this.restTemplateFile = restTemplateService;
        this.restTemplateReview = restTemplateReview;
    }


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

    //상품 id로 파일1개 rest로 가져오는거 성공한 코드
//    public Product getProductAllById(int productId) {
//
//
//        Product product = productRepository.findById(productId).orElse(null);
//
//        List<ImageDTO> images = new ArrayList<>();
//
//        //String getFileURL = "http://k4d104.p.ssafy.io:8082/file/";
//        //String getFilesURL = "http://localhost:8082/file/fileServes";
//        String getFileURL = "http://localhost:8082/file/fileServeOne/"+product.getImages().get(0).getFileId()+"/"+product.getImages().get(0).getImageType();
//
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
//        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
//
//        System.out.println("file서버를 갔다왔는데 과연 값이 제대로 나왔을까???");
//        System.out.println(restTemplateFile.get(getFileURL, httpHeaders).toString());
//
//        return product;
//    }

    //파일 여러개 가져오는 코드 만들기
//    public Map<String,Object> getProductAllById(int productId) {
//
//        Product product = productRepository.findById(productId).orElse(null);
//
//        List<ImageDTO> images = new ArrayList<>();
//
//        for (int i = 0 ; i < product.getImages().size() ; ++i) {
//            ImageDTO imageDTO = new ImageDTO();
//
//            imageDTO.setImageType(product.getImages().get(i).getImageType());
//            imageDTO.setFileId(product.getImages().get(i).getFileId());
//            images.add(imageDTO);
//        }
//
//        //String getFileURL = "http://k4d104.p.ssafy.io:8082/file/";
//        String getFilesURL = "http://localhost:8082/file/fileServesss";
//        String getReviewsURL = "http://localhost:8082/revuew/getReviewsAll/"+Integer.toString(product.getId());
//
//        Gson gson = new Gson();
//
//        String imageJson = gson.toJson(images);
//
//        HttpHeaders FileHttpHeaders = new HttpHeaders();
//        FileHttpHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
//        FileHttpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
//        FileHttpHeaders.set("imagesParam",imageJson);
//
//        HttpHeaders ReviewHttpHeaders = new HttpHeaders();
//        ReviewHttpHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
//        ReviewHttpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
//
//        ResponseEntity<JsonArray> responseEntityFile = restTemplateFile.get(getFilesURL, FileHttpHeaders);
//
//        ResponseEntity<JsonArray> responseEntityReview = restTemplateFile.get(getReviewsURL, ReviewHttpHeaders);
//
//
//        Map<String,Object> answer = new HashMap<>();
//
//        answer.put("product",product);
//        answer.put("images",responseEntityFile.getBody());
//        //answer.put("reviews",)
//
//        return answer;
//    }

    public Map<String,Object> getProductAllById(int productId) {

        Product product = productRepository.findById(productId).orElse(null);
        List<ImageDTO> images = new ArrayList<>();

        List<Integer> fileIds = new ArrayList<>();
        for (int i = 0 ; i < product.getImages().size() ; ++i) {
            fileIds.add(product.getImages().get(i).getFileId());
        }

        //String getFileURL = "http://k4d104.p.ssafy.io:8082/file/";
        String getFilesURL = "http://localhost:8082/file/fileServesss";
        String getReviewsURL = "http://localhost:8083/review/getReviewsAll/"+Integer.toString(product.getId());

        Gson gson = new Gson();

        String imageJson = gson.toJson(fileIds);

        HttpHeaders FileHttpHeaders = new HttpHeaders();
        FileHttpHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        FileHttpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        FileHttpHeaders.set("imagesParam",imageJson);

        HttpHeaders ReviewHttpHeaders = new HttpHeaders();
        ReviewHttpHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        ReviewHttpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        ResponseEntity<JsonArray> responseEntityFile = restTemplateFile.get(getFilesURL, FileHttpHeaders);

        ResponseEntity<JsonArray> responseEntityReview = restTemplateFile.get(getReviewsURL, ReviewHttpHeaders);



        Map<String,Object> answer = new HashMap<>();

        answer.put("product",product);
        answer.put("images",responseEntityFile.getBody());
        answer.put("reviews",responseEntityReview.getBody());

        return answer;
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
    public void saveProduct(ProductAndOptionAndImage productAndOptionAndImage) {
        Product product = productAndOptionAndImage.getProduct();

        Date cur = new Date();
        product.setRegisterDate(cur);
        product.setUpdateDate(cur);
        Product productResult = productRepository.save(product);
        System.out.println("상품 저장 완료. 아이디: " + productResult.getId());

        List<Productoption> options = productAndOptionAndImage.getOption();
        List<Productimage> images = productAndOptionAndImage.getImage();

        for (int i = 0 ; i < options.size() ; ++i){
            options.get(i).setProduct(productResult);
        }
        for (int i = 0 ; i < images.size() ; ++i){
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
