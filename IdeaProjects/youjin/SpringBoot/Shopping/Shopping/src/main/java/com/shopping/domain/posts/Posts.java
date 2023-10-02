package com.shopping.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Posts{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String product_name;

    @Column(nullable = false)
    private String product_number;

    @Column
    private String product_category;

    @Column(nullable = false)
    private int product_price;

    @Column
    private int product_stock;

    @Builder
    public Posts(String product_name, int product_price, String product_number, int product_stock, String product_category){
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_number = product_number;
        this.product_stock = product_stock;
        this.product_category = product_category;
    }

    public void update(String product_name, String product_number, int product_price, int product_stock){
        this.product_name = product_name;
        this.product_number = product_number;
        this.product_price = product_price;
        this.product_stock = product_stock;
    }
}