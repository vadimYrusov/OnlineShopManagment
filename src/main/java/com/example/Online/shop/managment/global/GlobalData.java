package com.example.Online.shop.managment.global;

import com.example.Online.shop.managment.entity.ShopItem;

import java.util.ArrayList;
import java.util.List;

public class GlobalData {

    public static List<ShopItem> cart;
    static {
        cart = new ArrayList<ShopItem>();
    }
}
