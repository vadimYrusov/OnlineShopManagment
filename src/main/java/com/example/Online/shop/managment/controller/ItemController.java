package com.example.Online.shop.managment.controller;

import com.example.Online.shop.managment.entity.ShopItem;
import com.example.Online.shop.managment.fileUpload.FileUploadUtil;
import com.example.Online.shop.managment.repo.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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
    public String createItem(Model model) {
        ShopItem item = new ShopItem();
        model.addAttribute("item", item);
        return "create_item";
    }

    @PostMapping("/items")
    public String saveItem(
            @ModelAttribute("item") ShopItem shopItem,
//            BindingResult result,
            @RequestParam("file") MultipartFile file
//            RedirectAttributes attributes
    ) throws IOException {
//        if (result.hasErrors()) {
//            return "create_item";
//        }

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        shopItem.setImageName(fileName);

        ShopItem savedItem = itemRepository.save(shopItem);

        String uploadDir = "./item-images/" + savedItem.getId();

        FileUploadUtil.saveFile(uploadDir, fileName, file);

        itemRepository.save(shopItem);
        return "redirect:/items";
    }

    @GetMapping("/more/{id}")
    public String editItem(@PathVariable Long id, Model model) {
        model.addAttribute("item", itemRepository.findById(id));
        return "more_item";
    }

    @GetMapping("/delete/{id}")
    public String deleteItem(@PathVariable Long id) {
        itemRepository.deleteById(id);
        return "redirect:/items";
    }
}
