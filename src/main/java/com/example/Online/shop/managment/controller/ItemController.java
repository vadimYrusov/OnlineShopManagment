package com.example.Online.shop.managment.controller;

import com.example.Online.shop.managment.entity.ShopItem;
import com.example.Online.shop.managment.fileUpload.FileUploadUtil;
import com.example.Online.shop.managment.repo.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Member;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

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
    public String saveMember(
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
        return "items";
    }
}
