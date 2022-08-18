package com.example.Online.shop.managment.controller;

import com.example.Online.shop.managment.entity.Category;
import com.example.Online.shop.managment.entity.ShopItem;
import com.example.Online.shop.managment.fileUpload.FileUploadUtil;
import com.example.Online.shop.managment.global.GlobalData;
import com.example.Online.shop.managment.repo.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;

    @GetMapping("/items")
    public String listItems(Model model) {

        List<ShopItem> items = itemRepository.findAll();

        model.addAttribute("items", items);
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("category", new Category());

        return "items";
    }

    @GetMapping("/items/new")
    public String createItem(Model model) {
        ShopItem item = new ShopItem();
        model.addAttribute("item", item);
        return "create_item";
    }

    @PostMapping("/addCategory")
    public String addCategory() {
        return "redirect:/items";
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
    public String moreItem(@PathVariable Long id, Model model) {
        model.addAttribute("item", itemRepository.findById(id));
        model.addAttribute("cartCount", GlobalData.cart.size());

        return "more_item";
    }

    @GetMapping("/item/edit/{id}")
    public String editItemForm(@PathVariable Long id, Model model) {
        ShopItem item = itemRepository.findById(id).get();
        model.addAttribute("item", item);
        return "edit_item";
    }

    @PostMapping("/item/{id}")
    public String updateItem(@PathVariable Long id,
                               @ModelAttribute("item") ShopItem item,
                               @RequestParam("file") MultipartFile file
//                               BindingResult result,
    ) throws IOException {

//        if (result.hasErrors()) {
//            return "edit_member";
//        }

        ShopItem existingItem = itemRepository.findById(id).get();
        existingItem.setId(id);
        existingItem.setTitle(item.getTitle());
        existingItem.setPrice(item.getPrice());
        existingItem.setDescription(item.getDescription());
        existingItem.setTag(item.getTag());
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        existingItem.setImageName(fileName);

        ShopItem savedItem = itemRepository.save(existingItem);

        String uploadDir = "./item-images/" + savedItem.getId();

        FileUploadUtil.saveFile(uploadDir, fileName, file);

        itemRepository.save(existingItem);

        return "redirect:/items";
    }

    @GetMapping("/delete/{id}")
    public String deleteItem(@PathVariable Long id) {
        itemRepository.deleteById(id);
        return "redirect:/items";
    }
}
