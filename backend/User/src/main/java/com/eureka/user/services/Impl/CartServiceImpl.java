package com.eureka.user.services.Impl;


import com.eureka.user.Entity.CartEntity;
import com.eureka.user.dto.Cart;
import com.eureka.user.repository.CartRepository;
import com.eureka.user.repository.UserRepository;
import com.eureka.user.services.AuthService;
import com.eureka.user.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserRepository userRepository;

    private  final AuthService authService;

    public CartServiceImpl(AuthService authService) {
        this.authService = authService;
    }

    @Override
    @Transactional
    public List<CartEntity> saveCart(Cart cart) throws Exception {
        String userid=authService.getUserId(cart.getUserEmail());
        CartEntity cartEntity= new CartEntity();
        cartEntity.setUserId(userid);
        cartEntity.setProductId(cart.getProductId());
        cartEntity.setOptionId(cart.getOptionId());
        cartEntity.setQuantity(cart.getQuantity());
        System.out.println(cartEntity);

        if(cartRepository.findTopByUserIdAndProductIdAndOptionId(cartEntity.getUserId(), cart.getProductId(),cart.getOptionId())!=null) throw new Exception("해당 상품 장바구니에 존재");
        cartRepository.save(cartEntity);
        return cartRepository.findAllByUserId(cartEntity.getUserId());
    }

    @Override
    @Transactional
    public List<CartEntity> getCart(String userid) {
        return cartRepository.findAllByUserId(userid);
    }

    @Override
    @Transactional
    public List<CartEntity> updateCart(CartEntity cart) throws Exception {
        if(cartRepository.save(cart)==null) throw new Exception("해당 상품 장바구니 업데이트 실패");
        return cartRepository.findAllByUserId(cart.getUserId());
    }

    @Override
    @Transactional
    public void deleteAll(String userEmail) throws Exception {
        try {
            String userid=Integer.toString(userRepository.findTop1ByEmail(userEmail).getId());
            System.out.println(userid);
            cartRepository.deleteAllByUserId(userid);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public void deleteCart(String cartId) {
        try {
            cartRepository.deleteById(cartId);
        }catch (Exception e){
            throw e;
        }
    }


}
