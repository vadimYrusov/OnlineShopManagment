package com.example.Online.shop.managment.controller;

import com.example.Online.shop.managment.entity.ShopItem;
import com.example.Online.shop.managment.repo.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.lang.reflect.Member;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;

    @GetMapping("/items")
    public String listItems(Model model) {

        List<ShopItem> items = itemRepository.findAll();

        model.addAttribute("items", items);
        return "items";
    }

    @GetMapping("/items/new")
    public String createMember(Model model) {
        ShopItem item = new ShopItem();
        model.addAttribute("item", item);
        return "create_item";
    }

    @PostMapping("/items")
    public String saveMember(@ModelAttribute("item") ShopItem shopItem, BindingResult result) {
        if (result.hasErrors()) {
            return "create_item";
        }
        itemRepository.save(shopItem);
        return "redirect:/items";
    }
}
