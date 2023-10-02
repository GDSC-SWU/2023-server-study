package com.shopping.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto {
    private String product_name;
    private String product_number;
    private int product_price;
    private int product_stock;

    @Builder
    public PostsUpdateRequestDto(String product_name, String product_number, int product_price, int product_stock){
        this.product_name = product_name;
        this.product_number = product_number;
        this.product_price = product_price;
        this.product_stock = product_stock;
    }
}