package com.example.shopping_mall_Pj.web.dto;

import com.example.shopping_mall_Pj.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsCreateRequestDto {
    private String product_name;
    private String product_number;
    private int stock;
    private int price;
    private String category;

    @Builder
    public PostsCreateRequestDto(String product_name, String product_number,
                                 int stock, int price, String category){
        this.product_name=product_name;
        this.product_number=product_number;
        this.stock=stock;
        this.price=price;
        this.category=category;
    }

    public Posts toEntity(){
        return Posts.builder()
                .product_name(product_name)
                .product_number(product_number)
                .stock(stock)
                .price(price)
                .category(category)
                .build();

    }

}
