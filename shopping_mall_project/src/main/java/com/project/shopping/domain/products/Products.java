package com.project.shopping.domain.products;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 상품 아이디

    @Column(length = 500, nullable = false)
    private String product_name;

    @Column(nullable = false)
    private String product_number;

    @Column(nullable = false)
    private Long stock;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private Double price;

    @Builder
    public Products(String product_name, String product_number, Long stock, String category, Double price) {
        this.product_name = product_name;
        this.product_number = product_number;
        this.stock = stock;
        this.category = category;
        this.price = price;
    }

    public void update(String product_name, String product_number, Long stock, String category, Double price) {
        this.product_name = product_name;
        this.product_number = product_number;
        this.stock = stock;
        this.category = category;
        this.price = price;
    }
}
