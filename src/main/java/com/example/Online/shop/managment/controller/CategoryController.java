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

    @GetMapping("/category")
    public String addCategory(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);
        return "category";
    }

    @GetMapping("/categories")
    public String editCategory(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "categories";
    }

    @PostMapping("/category")
    public String saveCategory(@ModelAttribute Category category) {
        categoryRepository.save(category);
        return "redirect:/items";
    }

    @GetMapping("/categories/{id}")
    public String updateCategory(@PathVariable Long id, Model model) {
        Category category = categoryRepository.findById(id).get();
        model.addAttribute("category", category);
        return "category_edit";
    }

    @PostMapping("/category/{id}")
    public String saveUpdateItem(@ModelAttribute Category category, @PathVariable Long id) {
        Category existCategory = categoryRepository.findById(id).get();
        existCategory.setId(category.getId());
        existCategory.setName(category.getName());
        categoryRepository.save(existCategory);
        return "redirect:/items";
    }

    @GetMapping("/category/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryRepository.deleteById(id);
        return "redirect:/categories";
    }
}
