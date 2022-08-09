package com.example.Online.shop.managment.controller;

import com.example.Online.shop.managment.entity.User;
import com.example.Online.shop.managment.repo.UserRepository;
import com.example.Online.shop.managment.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/registration")
public class UserController {

    private final UserService userService;

    @GetMapping
    @RequestMapping("/new")
    public String registrationForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping
    @RequestMapping("/save")
    public String registrationUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/registration?success";
    }


}
