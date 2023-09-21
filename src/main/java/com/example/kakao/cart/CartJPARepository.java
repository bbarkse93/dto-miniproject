package com.example.kakao.cart;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartJPARepository extends JpaRepository<Cart, Integer> {
    
    List<Cart> findAllByUserId(int userId);
    
    void deleteByUserId(int userId);

    @Query("select c from Cart c where c.user.id = :userId")
    List<Cart> mFindAllByUserId(@Param ("userId") Integer userId);
}
