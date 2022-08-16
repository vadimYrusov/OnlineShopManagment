package com.example.Online.shop.managment.controller;

import com.example.Online.shop.managment.repo.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final ItemRepository itemRepository;

    @GetMapping("/adminHome")
    public String adminPage() {
        return "admin_home";
    }
}
