package com.example.Online.shop.managment.repo;

import com.example.Online.shop.managment.entity.ShopItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<ShopItem, Long> {

    List<ShopItem> findByTitleContainsIgnoreCase(@Param("keyword") String keyword);


}
