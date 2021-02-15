package com.eureka.order.Repository;

import com.eureka.order.Entity.OrderDetailEntity;
import com.eureka.order.Entity.OrderEntity;
import com.eureka.order.util.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@EnableJpaRepositories
public interface OrderDetailRepositoty extends JpaRepository<OrderDetailEntity,String> {
        List<OrderDetailEntity> findAllByCheckDatetime(String isnull);

        List<OrderDetailEntity> findAllByOrderStatus(OrderStatus status);
}
