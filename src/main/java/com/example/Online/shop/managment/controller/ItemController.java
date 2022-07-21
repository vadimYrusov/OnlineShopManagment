package com.example.Online.shop.managment.controller;

import com.example.Online.shop.managment.entity.ShopItem;
import com.example.Online.shop.managment.repo.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Member;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;

    @Value("${upload.path}")
    private String uploadPath;

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
    public String saveMember(
            @ModelAttribute("item") ShopItem shopItem,
            BindingResult result,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        if (result.hasErrors()) {
            return "create_item";
        }

        if (file != null && !file.getOriginalFilename().isEmpty()) {

            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            shopItem.setFileName(resultFilename);
        }

        itemRepository.save(shopItem);
        return "redirect:/items";
    }
}
