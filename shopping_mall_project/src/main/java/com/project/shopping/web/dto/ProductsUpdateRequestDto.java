package com.project.shopping.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductsUpdateRequestDto {

    private String product_name;
    private String product_number;
    private Long stock;
    private String category;
    private Double price;

    @Builder
    public ProductsUpdateRequestDto(String product_name, String product_number, Long stock, String category, Double price){
        this.product_name=product_name;
        this.product_number=product_number;
        this.stock=stock;
        this.category=category;
        this.price=price;
    }
}
