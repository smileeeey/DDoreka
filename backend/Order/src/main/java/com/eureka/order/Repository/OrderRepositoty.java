package com.eureka.order.Repository;

import com.eureka.order.Entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepositoty extends JpaRepository<OrderEntity,String> {
}
