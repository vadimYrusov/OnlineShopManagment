package com.example.Online.shop.managment.controller;

import com.example.Online.shop.managment.dto.makeDto.ItemDtoFactory;
import com.example.Online.shop.managment.entity.Category;
import com.example.Online.shop.managment.entity.ShopItem;
import com.example.Online.shop.managment.fileUpload.FileUploadUtil;
import com.example.Online.shop.managment.global.GlobalData;
import com.example.Online.shop.managment.repo.CategoryRepository;
import com.example.Online.shop.managment.repo.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;

    private final ItemDtoFactory itemDtoFactory;

    private final CategoryRepository categoryRepository;

    @GetMapping("/items")
    public String listItems(Model model, String keyword) {

        List<ShopItem> items;

        if (keyword == null) {
            items = itemRepository.findAll();
        } else {
            items = itemRepository.findByTitleContainsIgnoreCase(keyword);
        }

        model.addAttribute("items", items.stream()
                .map(itemDtoFactory::makeItemDto)
                .collect(Collectors.toList()));
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("category", new Category());
        model.addAttribute("keyword", "");

        return "items";
    }

    @GetMapping("/items/new")
    public String createItem(Model model) {
        ShopItem item = new ShopItem();
        model.addAttribute("item", item);
        model.addAttribute("categories", categoryRepository.findAll());
        return "create_item";
    }

    @PostMapping("/items")
    public String saveItem(
            @ModelAttribute("item") @Valid ShopItem shopItem,
            BindingResult result,
            @RequestParam("file") MultipartFile file
    ) throws IOException {

        if (result.hasErrors()) {
            return "create_item";
        }

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        shopItem.setImageName(fileName);

        ShopItem savedItem = itemRepository.save(shopItem);

        String uploadDir = "./item-images/" + savedItem.getId();

        FileUploadUtil.saveFile(uploadDir, fileName, file);

        itemRepository.save(shopItem);
        return "redirect:/items";
    }

    @GetMapping("/item/{id}")
    public String moreItem(@PathVariable Long id, Model model) {
        ShopItem item = itemRepository.getShopItemById(id);
        model.addAttribute("item", itemDtoFactory.makeItemDto(item));
        model.addAttribute("cartCount", GlobalData.cart.size());

        return "more_item";
    }

    @GetMapping("/items/{id}")
    public String editItemForm(@PathVariable Long id, Model model) {
        ShopItem item = itemRepository.getShopItemById(id);
        model.addAttribute("item", itemDtoFactory.makeItemDto(item));
        return "edit_item";
    }

    @PostMapping("/item/{id}")
    public String updateItem(@PathVariable Long id,
                               @ModelAttribute("item") @Valid ShopItem item,
                               BindingResult result,
                               @RequestParam("file") MultipartFile file
    ) throws IOException {

        if (result.hasErrors()) {
            return "edit_item";
        }

        ShopItem existingItem = itemRepository.getShopItemById(id);
        existingItem.setId(id);
        existingItem.setTitle(item.getTitle());
        existingItem.setPrice(item.getPrice());
        existingItem.setDescription(item.getDescription());
        existingItem.setCategory(item.getCategory());

        if (file.getName().equals("")) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());

            existingItem.setImageName(fileName);

            ShopItem savedItem = itemRepository.save(existingItem);

            String uploadDir = "./item-images/" + savedItem.getId();

            FileUploadUtil.saveFile(uploadDir, fileName, file);
        } else {
            String fileName = StringUtils.cleanPath(existingItem.getImageName());

            existingItem.setImageName(fileName);

            ShopItem savedItem = itemRepository.save(existingItem);

            String uploadDir = "./item-images/" + savedItem.getId();

            FileUploadUtil.saveFile(uploadDir, fileName, file);
        }

        itemRepository.save(existingItem);

        return "redirect:/items";
    }

    @GetMapping("/delete/{id}")
    public String deleteItem(@PathVariable Long id) {
        itemRepository.deleteById(id);
        return "redirect:/items";
    }
}
