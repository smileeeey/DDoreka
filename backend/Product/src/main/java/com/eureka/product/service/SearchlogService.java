package com.eureka.product.service;

import com.eureka.product.dto.Category;
import com.eureka.product.dto.Searchlog;
import com.eureka.product.repository.SearchlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Date cur = new Date();
        Searchlog searchlog = new Searchlog();
        searchlog.setSearchDate(cur);
        searchlog.setSearchWord(keyword);
        repository.save(searchlog);
    }
}
