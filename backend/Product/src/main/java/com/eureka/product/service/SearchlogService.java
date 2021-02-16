package com.eureka.product.service;

import com.eureka.product.dto.Category;
import com.eureka.product.dto.Searchlog;
import com.eureka.product.repository.SearchlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SearchlogService {

    @Autowired
    private SearchlogRepository repository;

    public Map<String,List<String>> getRealtimeSearchWord(List<Category> category1) {
        Map<String,List<String>> map = new HashMap<>();

        for (Category category : category1) {
            map.put(category.getId(),repository.findSearchWordTop10(category.getId()));
        }

        for (String s : map.keySet()) {
            System.out.println("맵의 키값인 카테고리아이디"+s);
            List<String> list = map.get(s);
            for (String s1 : list) {
                System.out.println(s1);
            }
        }
        System.out.println("map출력끝!이제 리턴이다!");
        return map;
    }

    public void addLog(String category1Id, String keyword) {
        Searchlog log = new Searchlog();
        log.setCategory1Id(category1Id);
        log.setSearchWord(keyword);
        repository.save(log);
    }
}
