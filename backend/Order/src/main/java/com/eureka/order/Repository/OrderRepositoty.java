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

/**
 * order repository JPA
 * @author refactored by shimjaehyuk, leesumin
 * @since 1.0
 */
@EnableJpaRepositories
public interface OrderRepositoty extends JpaRepository<OrderEntity,String> {
    /**
     * find all by user id
     * @param userId
     * @param pageable
     * @return Page<OrderEntity>
     */
    Page<OrderEntity> findAllByUserId(String userId, Pageable pageable);

    /**
     * find all by user id
     * @param userId
     * @return List<OrderEntity>
     */
    List<OrderEntity> findAllByUserId(String userId);

    /**
     * find all by seller id
     * @param sellerId
     * @param pageable
     * @return Page<OrderEntity>
     */
    Page<OrderEntity> findAllBySellerId(String sellerId,Pageable pageable);

    /**
     * find all by seller id
     * @param sellerId
     * @return List<OrderEntity>
     */
    List<OrderEntity> findAllBySellerId(String sellerId);

    /**
     * get all list of order by day of week
     * @param dayOfWeek
     * @param productid
     * @return list of order entity
     */
    @Query(value = "SELECT * FROM orders m WHERE m.product_id=:productid  and DAYOFWEEK(m.datetime) = :day_of_week",nativeQuery = true)
    List<OrderEntity> findAllByDayOfWeek(@Param("day_of_week") String dayOfWeek,@Param("productid") String productid);

    /**
     * get all list of order by seller id of day of month
     * @param day
     * @param sellerid
     * @param month
     * @return list of order entity
     */
    @Query(value = "SELECT * FROM orders m WHERE m.seller_id=:sellerid  and dayofmonth(m.datetime) = :day and month(m.datetime) = :month",nativeQuery = true)
    List<OrderEntity> findAllBySelleridofday(@Param("day") String day, @Param("sellerid") String sellerid,@Param("month") String month);

    /**
     * get all list of product id by day of month(Top 10) sorted by sum(quantity)
     * @return
     */
    @Query(value= "select product_id as id from orders where datetime > DATE_ADD(now(),INTERVAL -1 month) group by product_id order by sum(quantity) DESC limit 0,10",nativeQuery = true)
    List<Integer> findSteadySeller();

    /**
     * get all list of product id by day of week(Top 10) sorted by sum(quantity)
     * @return
     */
    @Query(value= "select product_id as id from orders where datetime > DATE_ADD(now(),INTERVAL -7 day) group by product_id order by sum(quantity) DESC limit 0,10",nativeQuery = true)
    List<Integer> findHotProduct();

    /**
     * get all list of product id and count of Today's hot produts(Top 6) sorted by count
     * @return
     */
    @Query(value= "select product_id as id,count(*) as cnt from orders where datetime > DATE_ADD(now(),INTERVAL -1 day) group by product_id order by cnt DESC limit 0,6",nativeQuery = true)
    List<Map<String,Object>> findTodayHot();
}
