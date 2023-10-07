package com.example.ShoppingmallApi.web.dto;

import com.example.ShoppingmallApi.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsCreateDto {
    private String product_name;
    private int price;
    private String product_number;
    private int stock;
    private String category;
    @Builder
    public PostsCreateDto(String product_name, int price, String product_number, int stock, String category){
        this.product_name = product_name;
        this.price = price;
        this.product_number = product_number;
        this.stock = stock;
        this.category = category;
    }
    public Posts toEntity(){
        return Posts.builder().product_name(product_name).price(price).product_number(product_number).stock(stock)
                .category(category).build();
    }
}