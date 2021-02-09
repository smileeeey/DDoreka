package com.eureka.user.services;

import com.eureka.user.Entity.CartEntity;
import com.eureka.user.dto.Cart;

import java.util.List;


public interface CartService {


    List<CartEntity> saveCart(Cart cart) throws Exception;

    List<CartEntity> getCart(String userid);

    List<CartEntity> updateCart(CartEntity cart) throws  Exception;

    void deleteAll(String userid) throws Exception;

    void deleteCart(String cartId);
}
