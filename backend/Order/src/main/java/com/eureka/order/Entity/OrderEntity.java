package com.eureka.order.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "user_id")
    @NotNull
    private String userId;
    @Column(name = "product_id")
    @NotNull
    private String productId;
    @Column(name = "option_id")
    @NotNull
    private String optionId;
    @Column(name = "seller_id")
    private String sellerId;

    @Column(name = "address_main")
    @NotNull
    private String addressMain;
    @Column(name = "address_sub")
    @NotNull
    private String addressSub;
    @Column(name = "recipient_name")
    @NotNull
    private String recipientName;
    @NotNull
    private String zipcode;
    @Column(name = "delivery_msg")
    @NotNull
    private String deliveryMsg;
    @Column(name = "recipient_phone")
    @NotNull
    private String recipientPhone;
    @NotNull
    private String quantity;
    @NotNull
    private String price;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date datetime;
    @Column(name = "payment_method")
    private String paymentMethod;
    @OneToOne
    @JoinColumn(name ="id")
    @JsonManagedReference
    private OrderDetailEntity orderDetail;



}
