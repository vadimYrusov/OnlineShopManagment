package com.example.Online.shop.managment.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class MvcConfig implements WebMvcConfigurer {


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("index");
        registry.addViewController("/").setViewName("items");
        registry.addViewController("/").setViewName("more_item");
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path itemUploadDir = Paths.get("./item-images/");
        String itemUploadPath = itemUploadDir.toFile().getAbsolutePath();

        registry.addResourceHandler("/item-images/**").addResourceLocations("file:/" + itemUploadPath + "/");

    }
}
