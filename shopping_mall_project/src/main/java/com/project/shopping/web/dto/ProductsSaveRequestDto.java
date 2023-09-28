package com.project.shopping.web.dto;

import com.project.shopping.domain.products.Products;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductsSaveRequestDto {

    private String product_name;
    private String product_number;
    private Long stock;
    private String category;
    private Double price;

    @Builder
    public ProductsSaveRequestDto(String product_name, String product_number, Long stock, String category, Double price){
        this.product_name=product_name;
        this.product_number=product_number;
        this.stock=stock;
        this.category=category;
        this.price=price;
    }

    public Products toEntity(){
        return Products.builder()
                .product_name(product_name)
                .product_number(product_number)
                .stock(stock)
                .category(category)
                .price(price)
                .build();
    }


}
