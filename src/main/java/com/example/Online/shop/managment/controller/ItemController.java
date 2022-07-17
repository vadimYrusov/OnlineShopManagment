package com.example.Online.shop.managment.controller;

import com.example.Online.shop.managment.entity.ShopItem;
import com.example.Online.shop.managment.repo.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;

    @GetMapping("/items")
    public String listItems(Model model) {

        List<ShopItem> members = itemRepository.findAll();

        model.addAttribute("members", members);
        return "items";
    }
}
