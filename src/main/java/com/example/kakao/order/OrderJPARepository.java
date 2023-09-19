package com.example.kakao.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.example.kakao.user.User;

public interface OrderJPARepository extends JpaRepository<Order, Integer> {

    List<Order> findByUser(User user);

    List<Order> findByUserId(@Param("userId") Integer userId);
}
