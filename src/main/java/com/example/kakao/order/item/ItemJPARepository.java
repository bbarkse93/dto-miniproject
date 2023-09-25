package com.example.kakao.order.item;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItemJPARepository extends JpaRepository<Item, Integer> {

    @Query(value = "select i from Item i join i.order o where o.user.id = :userId")
    List<Item> findByUserId(@Param("userId") Integer userId);

}
