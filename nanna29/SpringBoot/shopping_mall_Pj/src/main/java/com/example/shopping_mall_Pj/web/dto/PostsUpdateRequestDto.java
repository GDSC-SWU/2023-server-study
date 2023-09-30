package com.example.shopping_mall_Pj.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto {
    private String product_name;
    private int stock;
    private int price;

    @Builder
    public PostsUpdateRequestDto(String product_name, int stock, int price){
        this.product_name=product_name;
        this.stock=stock;
        this.price=price;
    }
}
