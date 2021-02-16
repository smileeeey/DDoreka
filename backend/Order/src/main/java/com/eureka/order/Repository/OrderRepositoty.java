package com.eureka.order.Repository;

import com.eureka.order.Entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

@EnableJpaRepositories
public interface OrderRepositoty extends JpaRepository<OrderEntity,String> {
    Page<OrderEntity> findAllByUserId(String userid, Pageable pageable);
    List<OrderEntity> findAllByUserId(String userid);
//    Page<OrderEntity> findAllByUserId(String userid);
    Page<OrderEntity> findAllBySellerId(String sellerid,Pageable pageable);
    List<OrderEntity> findAllBySellerId(String sellerid);

    @Query(value = "SELECT * FROM orders m WHERE m.product_id=:productid  and DAYOFWEEK(m.datetime) = :day_of_week",nativeQuery = true)
    List<OrderEntity> findAllByDayOfWeek(@Param("day_of_week") String dayOfWeek,@Param("productid") String productid);

    @Query(value = "SELECT * FROM orders m WHERE m.seller_id=:sellerid  and dayofmonth(m.datetime) = :day and month(m.datetime) = :month",nativeQuery = true)
    List<OrderEntity> findAllBySelleridofday(@Param("day") String day, @Param("sellerid") String sellerid,@Param("month") String month);



    ////////////////////////   수민   /////////////////////////////
    @Query(value= "select product_id as id from orders where datetime > DATE_ADD(now(),INTERVAL -1 month) group by product_id order by sum(quantity) DESC limit 0,10",nativeQuery = true)
    List<Integer> findSteadySeller();

    @Query(value= "select product_id as id from orders where datetime > DATE_ADD(now(),INTERVAL -7 day) group by product_id order by sum(quantity) DESC limit 0,10",nativeQuery = true)
    List<Integer> findHotProduct();

    @Query(value= "select product_id as id,count(*) as cnt from orders where datetime > DATE_ADD(now(),INTERVAL -1 day) group by product_id order by cnt DESC limit 0,6",nativeQuery = true)
    List<Map<String,Object>> findTodayHot();
}
