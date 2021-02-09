package com.eureka.user.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "shoppingcart") //무시할것 에러 아님
@Data
public class CartEntity {
    @Id
    private int id;
    private int user_id;
    private int product_id;
    private int option_id;
    private int quantity;


}
