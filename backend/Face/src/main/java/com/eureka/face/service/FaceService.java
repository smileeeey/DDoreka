package com.eureka.face.service;

import com.eureka.face.dto.ProductSimpleDTO;
import com.eureka.face.entity.Face;
import com.eureka.face.repository.FaceRepository;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class FaceService {

    private FaceRepository repository;
    private RestTemplateService<JsonArray> restTemplateService;

    public Face saveFace(Face face) {return repository.save(face);}



    public String deleteFaceById(int id){
        repository.deleteById(id);
        return "Face removed!!" + id;
    }

    public List<Face> getFacesByUser(int user) {
        List<Integer> productIds = repository.findByUserGroupByProduct(user);

        List<Face> faces = new ArrayList<>();
        for (Integer productId : productIds) {
            faces.add(repository.findTop1ByProductAndUserOrderByCreatedAtDesc(productId,user));
        }
        return faces;
    }

    public List<ProductSimpleDTO> getProductSimpleByProductIds(List<Integer> productIds) {
        List<ProductSimpleDTO> productSimpleDTOS = new ArrayList<>();

        //fileIds들로 file가져오기
        Gson gson = new Gson();
        String productIdsParam = gson.toJson(productIds);
        System.out.println("product ids size : " + productIds.size());
        String getProductsURL = "http://localhost:8081/product/call-by-face";

        String productIdsConvertedListOfInteger = productIds.toString().substring(1, productIds.toString().length()-1);
        System.out.println(productIdsConvertedListOfInteger);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.set("productIdsParam",productIdsConvertedListOfInteger);

        ResponseEntity<?> responseEntityFile = restTemplateService.get(getProductsURL, httpHeaders);

        List<ProductSimpleDTO> products = (List<ProductSimpleDTO>)responseEntityFile.getBody();
        for(int i = 0; i < products.size(); i++) {
            Map<String, Object> product = (Map<String, Object>)products.get(i);

            ProductSimpleDTO dto = new ProductSimpleDTO();
            dto.setFileId((Integer)product.get("fileId"));
            dto.setThumbnail((String)product.get("thumbnail"));
            dto.setName((String)product.get("name"));
            dto.setPrice((Integer) product.get("price"));
            dto.setRating((Double)product.get("rating"));
            dto.setReview_cnt((Integer)product.get("review_cnt"));
            dto.setProductId((Integer)product.get("productId"));
            productSimpleDTOS.add(dto);
        }

        return productSimpleDTOS;
    }
}
