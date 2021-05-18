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

import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.lang.reflect.Type;
import java.util.*;
import java.util.List;


@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;
    private OptionRepository optionRepository;
    private ImageRepository imageRepository;
    private RestTemplateService<JsonArray> restTemplateFile;
    private RestTemplateService<JsonArray> restTemplateReview;
    private CategoryService categoryService;
    private SearchlogService searchlogService;

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

    //판매자의 최근10개 상품 가져오기
    public List<ProductSimpleDTO> getProductsByStore(int storeId) {
        System.out.println("ㅎㅇㅎㅇ"+storeId);
        List<Product> products =  productRepository.findIdTop10ByStoreIdOrderByRegisterDateDesc(storeId);

        return findProductSimpleByProduct(products);
    }


    public Map<String,List<Product>> getLatestProduct(List<Category> category1) {
        Map<String,List<Product>> map = new HashMap<>();

        for (Category category : category1) {
            map.put(category.getId(),productRepository.findTop23ByCategory1IdOrderByRegisterDateDesc(category.getId()));
        }

        return map;
    }

    public List<ProductSimpleDTO> findProductSimpleByProduct(List<Product> products){
        System.out.println("222:"+products.size());
        List<ProductSimpleDTO> result = new ArrayList<>();

        //상품의 썸네일 아이디 찾기
        List<Integer> fileIds = new ArrayList<>();
        Map<Integer,ProductSimpleDTO> map = new HashMap<>();

        int tmp;
        for (int i = 0; i < products.size(); ++i) {
            tmp = -1;
            ProductSimpleDTO productSimpleDTO = new ProductSimpleDTO();

            productSimpleDTO.setProductId(products.get(i).getId());
            productSimpleDTO.setName(products.get(i).getName());
            productSimpleDTO.setPrice(products.get(i).getOptions().get(0).getDiscountPrice());
            productSimpleDTO.setReview_cnt(products.get(i).getReviewCnt());
            productSimpleDTO.setRating(products.get(i).getRating());
            for (int j = 0; j < products.get(i).getImages().size(); ++j) {
                if (products.get(i).getImages().get(j).getImageType() == 'S') {
                    tmp = products.get(i).getImages().get(j).getFileId();
                    productSimpleDTO.setFileId(tmp);
                    fileIds.add(tmp);
                    break;
                }
            }
            map.put((tmp==-1)?tmp*i:tmp,productSimpleDTO);
        }

        Gson gson = new Gson();

        //fileIds들로 file가져오기
        String fileIdJson = gson.toJson(fileIds);
        String getFilesURL = "http://k4d104.p.ssafy.io:8082/file/fileServesss";

        HttpHeaders FileHttpHeaders = new HttpHeaders();
        FileHttpHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        FileHttpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        FileHttpHeaders.set("imagesParam",fileIdJson);

        ResponseEntity<?> responseEntityFile = restTemplateFile.get(getFilesURL, FileHttpHeaders);
        //String filebyte = ((Map<String, String>)((List<ImageDTO>)responseEntityFile.getBody()).get(0)).get("imageBytes");

        List<ImageDTO> images = (List<ImageDTO>)responseEntityFile.getBody();

        //해당하는 이미지 없을 때 뭔가 에러처리 해줘야 댐!! 지금은 그냥 5xx에러남!!!
        if(!images.isEmpty() && images != null){
            //Map
            int getFileId;
            for (int i = 0 ; i < images.size() ; ++i){
                Map<String,Object> imageMap = (Map<String,Object>)images.get(i);

                getFileId = (Integer)imageMap.get("fileId");
                map.get(getFileId).setThumbnail((String)imageMap.get("imageBytes"));
                result.add(map.get(getFileId));
                map.remove(getFileId);
            }
        }


        map.forEach((key,value)->result.add(value));
        return result;
    }

    public List<ProductSimpleDTO> findProductSimple(List<Integer> productIds) {
        System.out.println("JUN");
        if(productIds.size()==0)    return null;

        //아이디로 상품 정보 가져오기
        List<Product> products = productRepository.findByIdIn(productIds);  //주의! productIds개수랑 products 개수가 다를 수 있다. 중복이 제거되는 경우

        System.out.println("111:"+products.size());

        return findProductSimpleByProduct(products);
    }

    public Map<String,Object> findForMainPage() {
        Map<String,Object> result = new HashMap<>();
        System.out.println("hihi");

        String getOrdersURL = "http://k4d104.p.ssafy.io:8084/order/recommend/hots";

        HttpHeaders FileHttpHeaders = new HttpHeaders();
        FileHttpHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        FileHttpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        ResponseEntity<?> responseEntityFile = restTemplateFile.get(getOrdersURL, FileHttpHeaders);

        Map<String,List<String>> productIds = (Map<String,List<String>>)responseEntityFile.getBody();

        List<ProductSimpleDTO> dayHots = new ArrayList<>();
        List<ProductSimpleDTO> weekHots = new ArrayList<>();
        List<ProductSimpleDTO> monthHots = new ArrayList<>();

        //해당하는 이미지 없을 때 뭔가 에러처리 해줘야 댐!! 지금은 그냥 5xx에러남!!!
        List<String> dayHotIdsTmp = productIds.get("day-hot");
        List<String> weekHotIdsTmp = productIds.get("week-hot");
        List<String> monthHotIdsTmp = productIds.get("month-hot");

        List<Integer> dayHotIds = new ArrayList<>();
        List<Integer> weekHotIds = new ArrayList<>();
        List<Integer> monthHotIds = new ArrayList<>();
        for(String dayHotIdTmp : dayHotIdsTmp)  dayHotIds.add(Integer.valueOf(dayHotIdTmp));
        for(String weekHotIdTmp : weekHotIdsTmp)  weekHotIds.add(Integer.valueOf(weekHotIdTmp));
        for(String monthHotIdTmp : monthHotIdsTmp)  monthHotIds.add(Integer.valueOf(monthHotIdTmp));

        if(dayHotIds.size()!=0 && dayHotIds!=null){
            dayHots = findProductSimple(dayHotIds);
        }
        if(weekHotIds.size()!=0 && weekHotIds!=null){
            weekHots = findProductSimple(weekHotIds);
        }
        if(monthHotIds.size()!=0 && monthHotIds!=null){
            monthHots = findProductSimple(monthHotIds);
        }


        List<Category> category1 = categoryService.getCategories1();


        result.put("day-hot",dayHots);
        result.put("week-hot",weekHots);
        result.put("month-hot",monthHots);
        result.put("category-keyword",searchlogService.getRealtimeSearchWord(category1));
        result.put("category-recommend",getLatestProduct(category1));

        return result;
    }

    public List<OptionPriceDTO> getPriceFromOptionId(String param) {

        Gson gson = new Gson();

        Type listType = new TypeToken<ArrayList<String>>(){}.getType();
        List<String> optionIds = gson.fromJson(param, listType);

        List<OptionPriceDTO> res = new ArrayList<>();
        int len = optionIds.size();

        List<Productoption> productoptions = new ArrayList<>();

        List<Integer> optionIdsInt = new ArrayList<>();
        for (int i = 0 ; i < optionIds.size() ; ++i){
            optionIdsInt.add(Integer.parseInt(optionIds.get(i)));
        }

        productoptions = optionRepository.findAllByOptionIdIn(optionIdsInt);

        for (int i = 0 ; i < productoptions.size() ; ++i){
            OptionPriceDTO optionPriceDTO = OptionPriceDTO.builder()
                                            .optionId(String.valueOf(productoptions.get(i).getOptionId()))
                                            .price(productoptions.get(i).getPrice())
                                            .build();
            res.add(optionPriceDTO);
        }

        //        for(int i = 0; i < len; ++i) {
//
//            Productoption productoption = new Productoption();
//
//            int optionId = Integer.parseInt(optionIds.get(i));
//            productoption = optionRepository.findAllByOptionId(optionId);
//            System.out.println(optionIds.get(i));
//
//            productoptions.add(productoption);
//            //String price = optionRepository.findDiscountPriceByOptionId(optionId);
////            Integer price = optionRepository.findDiscountPriceByOptionId(2);
////            System.out.println("price : " + price);
////            OptionPriceDTO optionPriceDTO = OptionPriceDTO.builder()
////                                            .optionId(optionIds.get(i))
////                                            .price(price)
////                                            .build();
////            System.out.println("price :" + optionPriceDTO.getPrice());
////            res.add(optionPriceDTO);
//        }
//        System.out.println(productoptions.size());

        return res;
    }

    public List<ProductOptionInfoDTO> getPriceOptionInfo(List<Integer> productIds, List<Integer> optionIds) {

        List<ProductOptionInfoDTO> poInfo = new ArrayList<>();
        int len = productIds.size();

        List<ProductSimpleDTO> productSimpleDTOS = findProductSimple(productIds);

        Map<Integer,ProductSimpleDTO> productSimpleDTOMap = new HashMap<>();

        for (int i = 0; i < productSimpleDTOS.size(); i++) {
            productSimpleDTOMap.put(productSimpleDTOS.get(i).getProductId(), productSimpleDTOS.get(i));
        }

        for(int i = 0; i < len; ++i) {

            ProductSimpleDTO productSimpleDTO = productSimpleDTOMap.get(productIds.get(i));
            Productoption productoption = optionRepository.findTop1ByOptionId(optionIds.get(i));

            String productName = productSimpleDTO.getName();
            String thumbnail = productSimpleDTO.getThumbnail();
            String optionName = productoption.getName();
            int price = productoption.getDiscountPrice();
            int productId = productIds.get(i);
            int optionId = optionIds.get(i);

            ProductOptionInfoDTO dto = ProductOptionInfoDTO.builder()
                                    .productId(productId)
                                    .thumbnail(thumbnail)
                                    .optionId(optionId)
                                    .productName(productName)
                                    .optionName(optionName)
                                    .price(price)
                                    .build();
            poInfo.add(dto);
        }

        return poInfo;
    }
}
