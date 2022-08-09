package com.example.Online.shop.managment.service;

import com.example.Online.shop.managment.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User save(User user);
}
