package com.project.shopping.web.dto;

import com.project.shopping.domain.products.Products;
import lombok.Getter;

@Getter
public class ProductsResponseDto {

    private Long id;
    private String product_name;
    private String product_number;
    private Long stock;
    private String category;
    private Double price;

    public ProductsResponseDto(Products entity){
        this.id=entity.getId();
        this.product_name=entity.getProduct_name();
        this.product_number=entity.getProduct_number();
        this.stock=entity.getStock();
        this.category=entity.getCategory();
        this.price=entity.getPrice();
    }

}
