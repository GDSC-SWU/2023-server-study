package com.example.ShoppingmallApi.domain.posts;

import com.example.ShoppingmallApi.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String product_name;

    @Column
    private int price;

    @Column
    private String product_number;

    @Column
    private int stock;

    @Column
    private String category;

    @Builder
    public Posts(String product_name, int price, String product_number, int stock, String category){
        this.product_name = product_name;
        this.price = price;
        this.product_number = product_number;
        this.stock = stock;
        this.category = category;
    }

    public void update(String product_name, int price, String product_number, int stock, String category){
        this.product_name = product_name;
        this.price = price;
        this.product_number = product_number;
        this.stock = stock;
        this.category = category;
    }
}