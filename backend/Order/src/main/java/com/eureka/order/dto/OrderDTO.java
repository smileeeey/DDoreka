package com.eureka.order.dto;

import com.eureka.order.Entity.OrderDetailEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
public class OrderDTO {
    private int id;
    private String userId;
    private String addressMain;
    private String addressSub;
    private String recipientName;
    private String zipcode;
    private String deliveryMsg;
    private String recipientPhone;
    private String paymentMethod;
}
