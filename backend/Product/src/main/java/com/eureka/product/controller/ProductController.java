package com.eureka.product.controller;

import com.eureka.product.dto.*;
import com.eureka.product.entity.Category;
import com.eureka.product.entity.Product;
import com.eureka.product.service.CategoryService;
import com.eureka.product.service.ProductService;
import com.eureka.product.service.SearchlogService;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Api(tags = {"2. Product"})
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
    @ApiOperation(value="전체 상품 조회x", notes = "모든 상품 데이터를 반환", httpMethod = "GET")
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
    @GetMapping("/findByCategory/{categoryId}/{depth}/{page}/{size}")
    public Response findByCategory(@ApiParam(value="depth=1인 카테고리 고유값") @PathVariable String categoryId, @ApiParam(value="해당 카테고리의 depth 값") @PathVariable int depth, @ApiParam(value="몇 번째 page인지") @PathVariable Integer page, @ApiParam(value="한 페이지에 포함될 데이터 개수") @PathVariable Integer size) {
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
    @GetMapping("/search/{category1Id}/{keyword}/{page}/{size}")
    public Response findBySearch(@ApiParam(value="depth=1인 카테고리 고유값") @PathVariable String category1Id, @ApiParam(value="검색할 키워드") @PathVariable String keyword , @ApiParam(value="몇 번째 page인지") @PathVariable Integer page, @ApiParam(value="한 페이지에 포함될 데이터 개수") @PathVariable Integer size) {
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

    // 메인페이지에 한번에 넘겨줄 데이터 : 오늘의 상품, 요즘뜨는상품, 스테디셀러, 카테고리별 추천상품, 카테고리별 실시간키워드
    @ApiOperation(value="메인페이지에서 필요한 데이터 한번에 넘기기", notes = "메인페이지에 한번에 넘겨줄 데이터 : 오늘의 상품, 요즘뜨는상품, 스테디셀러, 카테고리별 추천상품, 카테고리별 실시간키워드에 해당하는 상품 기본정보 넘겨주기", httpMethod = "GET")
    @GetMapping(value = "/mainPage")
    public ResponseEntity<?> findForMainPage() {

        Map<String,Object> result = null;
        MultiValueMap<String, Object> header = new LinkedMultiValueMap<>();

        try {
            result = service.findForMainPage();
            return new ResponseEntity(result,header, HttpStatus.OK);
        } catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(result,header,HttpStatus.BAD_REQUEST); //이거맞나?
        }
    }

    // 상품아이디, 상품명, 파일 가져와서 face 서버에 넘겨주기
    @ApiOperation(value="상품 id들을 받아 상품 간단 정보(상품이름, 가격, review개수, 별점, 썸네일) 넘겨주기", notes = "상품id를 통해 상품명, 상품 이미지 전달", httpMethod = "GET")
    @GetMapping(value = "/call-by-face")
    public ResponseEntity<?> findProductNameAndThumbnail(@RequestHeader(value="productIdsParam") List<Integer> productIds ) {
        List<ProductSimpleDTO> result = null;
        MultiValueMap<String, Object> header = new LinkedMultiValueMap<>();

        try {
            result = service.findProductSimple(productIds);
            return new ResponseEntity(result,header, HttpStatus.OK);
        } catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(result,header,HttpStatus.BAD_REQUEST); //이거맞나?
        }
    }

    // 상품 상세 정보 가져오기
    @ApiOperation(value="상품 상세 조회 (한번에 상품,옵션,리뷰,사진 다줘!)", notes = "상품id를 통해 상품 상세,옵션, 리뷰,사진의 모든 데이터 반환", httpMethod = "GET")
    @GetMapping(value = "/detailAll/{productId}")
    public ResponseEntity<?> findDetailAll(@ApiParam(value="조회할 상품 id") @PathVariable int productId) {
        Map<String,Object> result = null;
        MultiValueMap<String, Object> header = new LinkedMultiValueMap<>();

        try {
            result = service.getProductAllById(productId);
            return new ResponseEntity(result,header, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(result,header, HttpStatus.BAD_REQUEST);
        }
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
    public Response saveProductAll(@ApiParam(value="등록할 상품 정보")  @RequestBody ProductAndOptionAndImage productAndOptionAndImage) {
        System.out.println("여기는 왔음!");
        try {
            service.saveProduct(productAndOptionAndImage);
            return new Response("success", "상품 정보 저장 완료", null);
        } catch (Exception e) {
            return new Response("error", "상품 정보 저장 오류", e.getMessage());
        }
    }

    // 판매자별 상품 리스트 가져오기
    @ApiOperation(value="판매자 상품 조회", notes = "판매자가 등록한 상품 정보 조회", httpMethod = "GET")
    @GetMapping(value = "/seller/latest10/{storeId}")
    public ResponseEntity<?> findByStoreId(@ApiParam(value="판매자 고유값")  @PathVariable int storeId) {

        List<ProductSimpleDTO> result = null;
        MultiValueMap<String, Object> header = new LinkedMultiValueMap<>();

        try {
            result = service.getProductsByStore(storeId);
            return new ResponseEntity(result,header, HttpStatus.OK);
        } catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(result,header,HttpStatus.BAD_REQUEST); //이거맞나?
        }
    }

    @ApiOperation(value="상품 정보 수정(세모)", notes = "판매자의 상품 정보 수정", httpMethod = "PUT")
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

    @ApiOperation(value="option-id, price를 제공", notes = "option-id로 price를 제공", httpMethod = "GET")
    @GetMapping(value="/prices")
    public List<OptionPriceDTO> getPrices(@ApiParam(value="옵션 고유값 리스트") @RequestHeader(value="option-ids") String param) {
        System.out.println("hihi");
        try {
            return service.getPriceFromOptionId(param);
        } catch (Exception e ) {
            e.printStackTrace();
            return null;
        }
    }

    @ApiOperation(value="product id, option id로 주문 내역 정보 제공 (이름과 가격 제공)", notes = "이름과 가격 제공", httpMethod = "GET")
    @GetMapping(value="/name")
    //@ApiParam(value="프로덕트 id와 옵션 id") @RequestHeader(value="") String param
    public List<ProductOptionInfoDTO> getProductInfo(@ApiParam(value="프로덕트 id와 옵션 id") @RequestHeader(value="product-ids") List<Integer> productIds, @RequestHeader(value="option-ids") List<Integer> optionIds) {
        System.out.println("!1");
        try {
            return service.getPriceOptionInfo(productIds, optionIds);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
