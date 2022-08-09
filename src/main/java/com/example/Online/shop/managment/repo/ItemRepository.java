package com.example.Online.shop.managment.repo;

import com.example.Online.shop.managment.entity.ShopItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<ShopItem, Long> {
}
