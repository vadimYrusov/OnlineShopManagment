package com.example.Online.shop.managment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "shop_item")
public class ShopItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Item's title cannot be empty")
    @Size(min = 2, max = 100, message = "Title should be between 2 and 100 characters")
    private String title;

    @NotEmpty(message = "Item's description cannot be empty")
    @Size(min = 2,max = 500, message = "Description should be between 2 and 500 characters")
    private String description;

    private String category;

    @NotNull(message = "Item's price cannot be empty")
    private Long price;

    public Long getPrice() {
        return price;
    }

    private String imageName;

    @Transient
    public String getPhotosImagePath() {
        if (imageName == null || id == null) return null;

        return "/item-images/" + id + "/" + imageName;
    }
}
