package com.example.Online.shop.managment.controller;

import com.example.Online.shop.managment.entity.Category;
import com.example.Online.shop.managment.repo.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryRepository categoryRepository;

    @GetMapping("/addCategory")
    public String addCategory(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);
        return "category";
    }

    @GetMapping("/editCategory")
    public String editCategory(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "categories";
    }

    @PostMapping("/addCategory")
    public String saveCategory(@ModelAttribute Category category) {
        categoryRepository.save(category);
        return "redirect:/items";
    }

    @GetMapping("/category/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryRepository.deleteById(id);
        return "redirect:/categories";
    }
}
