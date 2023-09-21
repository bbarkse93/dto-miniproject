package com.example.kakao.order;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.kakao.cart.Cart;
import com.example.kakao.cart.CartJPARepository;

@DataJpaTest
public class OrderJPARepository_test {

    @Autowired
    private CartJPARepository cartJPARepository;

    @Test
    public void mFindAllByUserId_test(){
        List<Cart> carts = cartJPARepository.mFindAllByUserId(1);
        System.out.println(carts);
    }
}
    

