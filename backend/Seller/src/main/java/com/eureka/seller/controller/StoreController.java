package com.eureka.seller.controller;

import com.eureka.seller.entity.Store;
import com.eureka.seller.service.StoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags="2. store")
@RestController
@RequestMapping("/store")
public class StoreController {

    @Autowired
    private StoreService service;

    @ApiOperation(value="스토어 추가", notes="스토어를 입력받아서 스토어를 추가하고 리턴한다", httpMethod = "POST")
    @PostMapping("/add")
    public Store addStore(@RequestBody Store store) {
        return service.saveStore(store);
    }

    @ApiOperation(value="입력받은 스토어들 추가x", notes = "입력받은 스토어의 정보를 저장하고, 리턴한다", httpMethod = "POST")
    @PostMapping("/addAll")
    public List<Store> addStores(@RequestBody List<Store> stores) {
        return service.saveStores(stores);
    }

    @ApiOperation(value = "모든 스토어 조회x", notes="존재하는 모든 스토어 정보를 리턴한다", httpMethod = "GET")
    @GetMapping("/getAll")
    public List<Store> findAllStores() {
        return service.getStores();
    }

    @ApiOperation(value = "아이디로 스토어 조회x", notes = "특정 아이디를 입력받아 해당 스토어의 정보를 리턴한다", httpMethod = "GET")
    @GetMapping("/getById/{id}")
    public Store findStoreById(@PathVariable int id) {
        return service.getStoreById(id);
    }

    @ApiOperation(value = "셀러 아이디로 스토어 조회", notes = "특정 셀러의 아이디를 입력받아 해당 스토어의 정보를 리턴한다", httpMethod = "GET")
    @GetMapping("/getBySellerId/{sellerId}")
    public Store findStoreBySellerId(@PathVariable int sellerId) {
        return service.getStoreBySellerId(sellerId);
    }

    @ApiOperation(value = "이름으로 스토어 조회x", notes = "이름으로 해당 스토어의 정보를 리턴한다", httpMethod = "GET")
    @GetMapping("/getByName/{name}")
    public Store findStoreByName(@PathVariable String name) {
        return service.getStoreByName(name);
    }

    @ApiOperation(value = "스토어 수정x", notes = "특정 스토어의 정보를 입력받아서 수정한다", httpMethod = "PUT")
    @PutMapping("/update")
    public Store updateStore(@RequestBody Store store) {
        return service.updateStore(store);
    }

    @ApiOperation(value = "아이디로 스토어 삭제x", notes = "아이디를 입력받고 해당 스토어를 삭제한다", httpMethod = "DELETE")
    @DeleteMapping("/deleteById/{id}")
    public String deleteStore(@PathVariable int id) {
        return service.deleteStore(id);
    }
}
