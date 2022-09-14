package com.example.Online.shop.managment.dto.makeDto;

import com.example.Online.shop.managment.dto.ItemDto;
import com.example.Online.shop.managment.entity.ShopItem;
import org.springframework.stereotype.Component;

@Component
public class ItemDtoFactory {

    public ItemDto makeItemDto(ShopItem item) {

        return ItemDto.builder()
                .id(item.getId())
                .title(item.getTitle())
                .description(item.getDescription())
                .category(item.getCategory())
                .price(item.getPrice())
                .imageName(item.getImageName())
                .photoImagePath(item.getPhotosImagePath())
                .build();
    }
}
