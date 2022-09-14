package com.example.Online.shop.managment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {

    private Long id;

    private String title;

    private String description;

    private String category;

    private Long price;

    private String imageName;

    private String photoImagePath;
}
