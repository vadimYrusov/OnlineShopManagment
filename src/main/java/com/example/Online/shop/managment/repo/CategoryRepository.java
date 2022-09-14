package com.example.Online.shop.managment.repo;

import com.example.Online.shop.managment.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category getCategoryById(Long id);
}
