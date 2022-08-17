package com.example.Online.shop.managment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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

    public Long getPrice() {
        return price;
    }

    @Column(nullable = true, length = 64)
    private String imageName;

//    @ManyToMany(mappedBy = "items")
//    private List<Cart> carts;

    @Transient
    public String getPhotosImagePath() {
        if (imageName == null || id == null) return null;

        return "/item-images/" + id + "/" + imageName;
    }
}
