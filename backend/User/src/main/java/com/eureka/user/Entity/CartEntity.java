package com.eureka.user.Entity;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "shoppingcart") //무시할것 에러 아님
@Data
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "option_id")
    private String optionId;

    private String quantity;



}
