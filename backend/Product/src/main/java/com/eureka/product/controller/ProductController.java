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

@Api(tags = {"1. Product"})
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
    @ApiOperation(value="전체 상품 조회", notes = "모든 상품 데이터를 반환", httpMethod = "GET")
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
    @ApiOperation(value="카테고리별 상품 조회", notes = "depth와 카테고리 id를 통해 조건을 만족하는 상품 데이터 반환", httpMethod = "GET")
    @GetMapping("/findByCategory/{categoryId}/{depth}")
    public Response findByCategory(@ApiParam(value="depth=1인 카테고리 고유값") @PathVariable String categoryId, @ApiParam(value="해당 카테고리의 depth 값") @PathVariable int depth, @RequestParam Integer page, Integer size) {
        Response response;
        try {
            response = new Response("success", "카테고리별 상품 조회 성공", service.getProductsByCategory(categoryId, depth,page,size));

        } catch (Exception e) {
            response = new Response("error", "카테고리별 상품 조회 실패", e.getMessage());
        }
        return response;
    }

    // 상품 검색 시 상품 list 가져오기
    @ApiOperation(value="검색 상품 조회", notes = "depth = 1과 키워드를 입력하면 해당 조건을 만족하는 상품 데이터 반환", httpMethod = "GET")
    @GetMapping("/search/{category1Id}/{keyword}")
    public Response findBySearch(@ApiParam(value="depth=1인 카테고리 고유값") @PathVariable String category1Id, @ApiParam(value="검색할 키워드") @PathVariable String keyword,@ApiParam(value="페이징 처리를 위한 몇 번째 페이지인지") @RequestParam Integer page, @ApiParam(value="페이징 처리할 경우 한 페이지의의 데이터 수") @RequestParam Integer size) {
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
    @ApiOperation(value="상품 상세 조회", notes = "상품id를 통해 상품 상세 데이터 반환", httpMethod = "GET")
    @GetMapping(value = "/detail/{productId}")
    public Response findDetail(@ApiParam(value="조회할 상품 id") @PathVariable int productId) {
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
    @ApiOperation(value="추천 상품 조회", notes = "추천 상품 아이디로 추천 상품 정보 가져오기", httpMethod = "GET")
    @GetMapping(value = "/recommend/productids")
    public Response recommendProducts(@ApiParam(value="주문할 상품 id")  @RequestParam(value="fileIds",required = true) List<Integer> productIds) {
        Response response;
        try {
            response = new Response("success", "추천 상품들 상세 조회 성공", service.getProductByIds(productIds));
        } catch (Exception e) {
            response = new Response("error", "추천 상품들 상세 조회 실패", e.getMessage());
        }

        return response;
    }


    // 실시간 검색어 가져오기
    @ApiOperation(value="실시간 검색어 조회", notes = "depth=1 카테고리당 검색어 top 10 조회", httpMethod = "GET")
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

    //카테고리별 최근 등록된 상품 조회
    @ApiOperation(value="최근 등록된 상품 조회", notes = "depth=1 카테고리별 최신 등록된 top 23 상품 조회", httpMethod = "GET")
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
    @ApiOperation(value="상품 등록", notes = "상품 정보 등록", httpMethod = "POST")
    @PostMapping(value = "/add", produces = "application/json;charset=utf8")
    public Response saveProductAll(@ApiParam(value="등록할 상품 정보")  @RequestBody Map<String, Object> param) {
        try {
            service.saveProduct(param);
            return new Response("success", "상품 정보 저장 완료", null);
        } catch (Exception e) {
            return new Response("error", "상품 정보 저장 오류", e.getMessage());
        }
    }

    // 판매자별 상품 리스트 가져오기
    @ApiOperation(value="판매자 상품 조회", notes = "판매자가 등록한 상품 정보 조회", httpMethod = "GET")
    @GetMapping(value = "/seller/all/{storeId}")
    public Response findByStoreId(@ApiParam(value="판매자 고유값")  @PathVariable int storeId) {
        Response response;
        try {
            response = new Response("success", "카테고리별 상품 조회 성공", service.getProductsByStore(storeId));
        } catch (Exception e) {
            response = new Response("error", "카테고리별 상품 조회 실패", e.getMessage());
        }

        return response;
    }

    @ApiOperation(value="상품 정보 수정", notes = "판매자의 상품 정보 수정", httpMethod = "PUT")
    @PutMapping(value="/seller/update")
    public Response updateProduct(@RequestBody Map<String,Object> param){
        Response response;
        try{
            response = new Response("success", "상품 업데이트 성공", service.updateProduct(param));
        }catch(Exception e){
            response = new Response("error", "상품 업데이트 실패", e.getMessage());
        }


        return response;
    }



}
