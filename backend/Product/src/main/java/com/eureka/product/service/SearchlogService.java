package com.eureka.product.service;

import com.eureka.product.entity.Category;
import com.eureka.product.dto.Searchlog;
import com.eureka.product.repository.SearchlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        return map;
    }

    public void addLog(String category1Id, String keyword) {
        Searchlog log = new Searchlog();
        log.setCategory1Id(category1Id);
        log.setSearchWord(keyword);
        repository.save(log);
    }


}
