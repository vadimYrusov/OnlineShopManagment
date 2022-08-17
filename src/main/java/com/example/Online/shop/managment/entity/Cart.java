//package com.example.Online.shop.managment.entity;
//
//import lombok.*;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//public class Cart {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @OneToOne(mappedBy = "cart")
//    private User user;
//
//    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "user_items",
//            joinColumns = {@JoinColumn(name = "CART_ID", referencedColumnName = "ID")},
//            inverseJoinColumns = {@JoinColumn(name = "ITEM_ID", referencedColumnName = "ID")}
//    )
//    private List<ShopItem> items;
//}
