package com.project.shopping.domain.products;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductsRepositoryTest {

    @Autowired
    ProductsRepository productsRepository;

    @After
    public void cleanup(){
        productsRepository.deleteAll();
    }

    @Test
    public void get_product(){
        // given
        String product_name="상품명";
        String product_number="상품 번호";
        Long stock=11L;
        String category="상품 카테고리";
        Double price=20000D;

        productsRepository.save(Products.builder()
                .product_name(product_name)
                .product_number(product_number)
                .stock(stock)
                .category(category)
                .price(price)
                .build());

        // when
        List<Products> productsList = productsRepository.findAll();


        // then
        Products products = productsList.get(0);
        System.out.println(products);
        assertThat(products.getProduct_name()).isEqualTo(product_name);
        assertThat(products.getProduct_number()).isEqualTo(product_number);
        assertThat(products.getStock()).isEqualTo(stock);
        assertThat(products.getCategory()).isEqualTo(category);
        assertThat(products.getPrice()).isEqualTo(price);


    }
}
