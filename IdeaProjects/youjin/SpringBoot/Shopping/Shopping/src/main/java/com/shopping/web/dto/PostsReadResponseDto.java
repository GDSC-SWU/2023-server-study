package com.shopping.web.dto;

import com.shopping.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsReadResponseDto {

    private Long id;
    private String product_name;
    private String product_number;
    private String product_category;
    private int product_price;
    private int product_stock;

    public PostsReadResponseDto(Posts entity){
        this.id = entity.getId();
        this.product_name = entity.getProduct_name();
        this.product_number = entity.getProduct_number();
        this.product_category = entity.getProduct_category();
        this.product_price = entity.getProduct_price();
        this.product_stock = entity.getProduct_stock();
    }
}