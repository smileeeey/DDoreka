package com.eureka.product.repository;

import com.eureka.product.dto.Searchlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SearchlogRepository extends JpaRepository<Searchlog, Integer> {

    @Query(value= "select search_word from searchlog where category1_id= ?1 and search_date > DATE_ADD(now(),INTERVAL -1 hour) group by category1_id,search_word order by count(*) DESC limit 0,10",nativeQuery = true)
    List<String> findSearchWordTop10(String category1Id);


}
