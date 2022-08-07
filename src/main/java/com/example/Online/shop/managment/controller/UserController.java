package com.example.Online.shop.managment.controller;

import com.example.Online.shop.managment.entity.UserAccount;
import com.example.Online.shop.managment.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/user")
    public String home(){
        return "registration";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserAccount userAccount) {
        userRepository.save(userAccount);
        return "redirect:/items";
    }
}
