package com.example.Online.shop.managment.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class UserController {

    @GetMapping("/user")
    public String home(){
        return "registration";
    }
}
