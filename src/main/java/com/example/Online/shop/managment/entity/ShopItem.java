package com.example.Online.shop.managment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

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

    private String title;

    private String description;

    private String tag;

    private Long price;

    @Column(nullable = true, length = 64)
    private String imageName;

    @Transient
    public String getPhotosImagePath() {
        if (imageName == null || id == null) return null;

        return "/item-images/" + id + "/" + imageName;
    }
}
