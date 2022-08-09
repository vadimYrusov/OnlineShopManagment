package com.example.Online.shop.managment.repo;

import com.example.Online.shop.managment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
    User findByName(String name);
}
