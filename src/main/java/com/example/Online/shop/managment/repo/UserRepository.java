package com.example.Online.shop.managment.repo;

import com.example.Online.shop.managment.entity.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findUserByEmail(String email);
}