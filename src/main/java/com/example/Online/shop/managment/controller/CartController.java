package com.example.Online.shop.managment.controller;

import com.example.Online.shop.managment.entity.ShopItem;
import com.example.Online.shop.managment.global.GlobalData;
import com.example.Online.shop.managment.repo.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final ItemRepository itemRepository;

    @GetMapping("/addToCard/{id}")
    public String addToCart(@PathVariable Long id) {
        GlobalData.cart.add(itemRepository.findById(id).get());
        return "redirect:/items";
    }

    @GetMapping("/cart")
    public String cartGet(Model model) {
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("total", GlobalData.cart.stream().mapToDouble(ShopItem::getPrice));
        model.addAttribute("cart", GlobalData.cart);
        return "cart";
    }
}
