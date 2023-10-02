package com.shopping.web.dto;

import com.shopping.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsCreateRequestDto {

    private String product_name;
    private String product_number;
    private String product_category;
    private int product_price;
    private int product_stock;

    @Builder
    public PostsCreateRequestDto(String product_name, String product_number, String product_category, int product_price, int product_stock){
        this.product_number = product_number;
        this.product_name = product_name;
        this.product_category = product_category;
        this.product_price = product_price;
        this.product_stock = product_stock;
    }

    public Posts toEntity() {
        return Posts.builder()
                .product_name(product_name)
                .product_number(product_number)
                .product_category(product_category)
                .product_price(product_price)
                .product_stock(product_stock)
                .build();
    }
}