package com.eureka.order.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "orders")
public class OrderEntity {
    @Id
    private String id;

    @Column(name = "user_id")
    private String userId;
    @Column(name = "product_id")
    private String productId;
    @Column(name = "option_id")
    private String optionId;
    @Column(name = "seller_id")
    private String sellerId;
    @Column(name = "address_main")
    private String addressMain;
    @Column(name = "address_sub")
    private String addressSub;
    @Column(name = "recipient_name")
    private String recipientName;
    private String zipcode;
    @Column(name = "delivery_msg")
    private String deliveryMsg;
    @Column(name = "recipient_phone")
    private String recipientPhone;
    private String quantity;
    private String price;
    private String datetime;
    @Column(name = "payment_method")
    private String paymentMethod;

    @OneToOne
    @JoinColumn(name ="id")
    private OrderDetailEntity orderDetailEntity;



}
