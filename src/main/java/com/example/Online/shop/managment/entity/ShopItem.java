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

    private String category;
    private Long price;

//    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "item_category",
//            joinColumns = {@JoinColumn(name = "ITEM_ID", referencedColumnName = "ID")},
//            inverseJoinColumns = {@JoinColumn(name = "CATEGORY_ID", referencedColumnName = "ID")}
//    )
//    private List<Category> category;

    public Long getPrice() {
        return price;
    }

    @Column(nullable = true, length = 64)
    private String imageName;

    @Transient
    public String getPhotosImagePath() {
        if (imageName == null || id == null) return null;

        return "/item-images/" + id + "/" + imageName;
    }
}
