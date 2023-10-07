package com.example.ShoppingmallApi.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsUpdateDto {
    private String product_name;
    private int price;
    private String product_number;
    private int stock;
    private String category;

    @Builder
    public PostsUpdateDto(String product_name, int price, String product_number, int stock, String category){
        this.product_name = product_name;
        this.price = price;
        this.product_number = product_number;
        this.stock = stock;
        this.category = category;
    }
}