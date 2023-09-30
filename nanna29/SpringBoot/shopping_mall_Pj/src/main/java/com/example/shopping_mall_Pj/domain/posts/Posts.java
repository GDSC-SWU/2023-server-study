package com.example.shopping_mall_Pj.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String product_name;

    @Column
    private String product_number;

    @Column(nullable = false)
    private int stock;

    @Column(nullable = false)
    private int price;

    @Column
    private String category;

    @Builder
    public Posts(String product_name, String product_number,
                 int stock, int price, String category){
        this.product_name=product_name;
        this.product_number=product_number;
        this.stock=stock;
        this.price=price;
        this.category=category;
    }

    public void update(String product_name, int stock, int price) {
        this.product_name = product_name;
        this.stock = stock;
        this.price = price;
    }
}
