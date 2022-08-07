package com.example.Online.shop.managment.repo;

import com.example.Online.shop.managment.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserAccount, Long> {
}
