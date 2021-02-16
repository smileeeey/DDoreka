package com.eureka.product.controller;

import com.eureka.product.dto.Category;
import com.eureka.product.dto.Product;
import com.eureka.product.dto.Response;
import com.eureka.product.service.CategoryService;
import com.eureka.product.service.ProductService;
import com.eureka.product.service.SearchlogService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService service;
    private final SearchlogService searchlogService;
    private final CategoryService categoryService;


    public ProductController(ProductService productService,SearchlogService searchlogService,CategoryService categoryService) {
        this.service = productService;
        this.searchlogService = searchlogService;
        this.categoryService = categoryService;
    }

    /////////////////// 사용자 페이지  ///////////////////

    @GetMapping("/all")
    public Response findAll() {
        Response response;
        try {
            response = new Response("success", "상품 전체 조회 성공", service.getProducts());
        } catch (Exception e) {
            response = new Response("error", "상품 전체 조회 실패", e.getMessage());
        }

        return response;
    }

    // 카테고리 선택 시 상품 list 가져오기
    @GetMapping("/findByCategory/{categoryId}/{depth}")
    public Response findByCategory(@PathVariable String categoryId, @PathVariable int depth, @RequestParam Integer page, Integer size) {
        Response response;
        try {
            response = new Response("success", "카테고리별 상품 조회 성공", service.getProductsByCategory(categoryId, depth,page,size));

        } catch (Exception e) {
            response = new Response("error", "카테고리별 상품 조회 실패", e.getMessage());
        }
        return response;
    }

    // 상품 검색 시 상품 list 가져오기
    @GetMapping("/search/{category1Id}/{keyword}")
    public Response findBySearch(@PathVariable String category1Id, @PathVariable String keyword, @RequestParam Integer page, @RequestParam Integer size) {
        Response response;
        try {
            Page<Product> pages = service.getProductsByName(category1Id, keyword,page,size);
            System.out.println("hi");
            searchlogService.addLog(category1Id,keyword);
            System.out.println("bye");
            response = new Response("success", "상품 검색 성공", pages);
        } catch (Exception e) {
            response = new Response("error", "상품 검색 실패", e.getMessage());
        }

        return response;
    }

    // 상품 상세 정보 가져오기
    @GetMapping(value = "/detail/{productId}")
    public Response findDetail(@PathVariable int productId) {
        Response response;
        try {
            response = new Response("success", "상품 상세 조회 성공", service.getProductById(productId));
        } catch (Exception e) {
            response = new Response("error", "상품 상세 조회 실패", e.getMessage());
        }

        return response;
    }

    ///////// 필터 적용


    //추천 상품 아이디로 추천 상품 정보 가져오기
    @GetMapping(value = "/recommend/productids")
    public Response recommendProducts( @RequestParam(value="fileIds",required = true) List<Integer> productIds) {
        Response response;
        try {
            response = new Response("success", "추천 상품들 상세 조회 성공", service.getProductByIds(productIds));
        } catch (Exception e) {
            response = new Response("error", "추천 상품들 상세 조회 실패", e.getMessage());
        }

        return response;
    }


    // 실시간 검색어 가져오기
    @GetMapping(value="/recommend/realtimesearchword")
    public Response realtimeSearchWord() {
        Response response;
        try {
            List<Category> category1 = categoryService.getCategories1();
            response = new Response("success", "실시간 검색어 조회 성공", searchlogService.getRealtimeSearchWord(category1));
        } catch (Exception e) {
            response = new Response("error", "실시간 검색어 조회 실패", e.getMessage());
        }

        return response;
    }

    //카테고리별 추천상품 가져오기
    @GetMapping(value="/recommend/latestproduct")
    public Response latestProduct() {
        Response response;
        try {
            List<Category> category1 = categoryService.getCategories1();
            response = new Response("success", "카테고리별 최신 상품 조회 성공", service.getLatestProduct(category1));
        } catch (Exception e) {
            response = new Response("error", "카테고리별 최신 상품 조회 실패", e.getMessage());
        }

        return response;
    }

    ////////// 유사 상품 가져오기

    /////////////////// 판매자 페이지  ///////////////////

    // 상품 정보 입력
    @PostMapping(value = "/add", produces = "application/json;charset=utf8")
    public Response saveProductAll(@RequestBody Map<String, Object> param) {
        try {
            service.saveProduct(param);
            return new Response("success", "상품 정보 저장 완료", null);
        } catch (Exception e) {
            return new Response("error", "상품 정보 저장 오류", e.getMessage());
        }
    }

    // 관리자별 상품 리스트 가져오기
    @GetMapping(value = "/seller/all/{storeId}")
    public Response findByStoreId(@PathVariable int storeId) {
        Response response;
        try {
            response = new Response("success", "카테고리별 상품 조회 성공", service.getProductsByStore(storeId));
        } catch (Exception e) {
            response = new Response("error", "카테고리별 상품 조회 실패", e.getMessage());
        }

        return response;
    }

    @PutMapping(value="/seller/update")
    public Response updateProduct(@RequestBody Map<String,Object> param){
        Response response;
        try{
            response = new Response("success", "리뷰 업데이트 성공", service.updateProduct(param));
        }catch(Exception e){
            response = new Response("error", "리뷰 업데이트 실패", e.getMessage());
        }


        return response;
    }



}
