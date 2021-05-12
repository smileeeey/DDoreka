package com.eureka.order.Repository;

import com.eureka.order.Entity.OrderDetailEntity;
import com.eureka.order.Entity.OrderEntity;
import com.eureka.order.util.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

/**
 * order detail repository JPA
 * @author refactored by shimjaehyuk, leesumin
 * @since 1.0
 */
@EnableJpaRepositories
public interface OrderDetailRepositoty extends JpaRepository<OrderDetailEntity,String> {
        /**
         * get all list of order detail by check date time
         * @param isnull
         * @return List<OrderDetailEntity>
         */
        List<OrderDetailEntity> findAllByCheckDatetime(String isnull);

        /**
         * get all list of order detail by order status
         * @param status
         * @return List<OrderDetailEntity>
         */
        List<OrderDetailEntity> findAllByOrderStatus(OrderStatus status);
}
