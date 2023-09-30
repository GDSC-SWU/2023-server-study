package com.example.shopping_mall_Pj.web.dto;

import com.example.shopping_mall_Pj.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsReadDto {
    private Long id;
    private String product_name;
    private String product_number;
    private int stock;
    private int price;
    private String category;

    public PostsReadDto(Posts entity) {
        this.id=entity.getId();
        this.product_name=entity.getProduct_name();
        this.product_number=entity.getProduct_number();
        this.stock=entity.getStock();
        this.price=entity.getPrice();
        this.category=entity.getCategory();
    }



}
