package com.example.Online.shop.managment.dto.makeDto;

import com.example.Online.shop.managment.dto.CategoryDto;
import com.example.Online.shop.managment.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryDtoFactory {

    public CategoryDto makeCategoryDto(Category category) {

        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
