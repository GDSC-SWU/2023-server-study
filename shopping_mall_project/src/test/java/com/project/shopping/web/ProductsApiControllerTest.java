package com.project.shopping.web;

import com.project.shopping.domain.products.Products;
import com.project.shopping.domain.products.ProductsRepository;
import com.project.shopping.web.dto.ProductsSaveRequestDto;
import com.project.shopping.web.dto.ProductsUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProductsRepository productsRepository;

    @After
    public void tearDown() throws Exception{
        productsRepository.deleteAll();
    }


    // 상품 등록 테스트
    @Test
    public void Products_register() throws Exception{
        // given
        String product_name="반팔니트";
        String product_number="FA21547";
        Long stock=30L;
        String category="Fashion";
        Double price=30000D;

        ProductsSaveRequestDto requestDto = ProductsSaveRequestDto.builder()
                .product_name(product_name)
                .product_number(product_number)
                .stock(stock)
                .category(category)
                .price(price)
                .build();

        String url = "http://localhost:"+port+"/api/v1/products";

        // when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url,requestDto,Long.class);


        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        List<Products> all = productsRepository.findAll();
        assertThat(all.get(0).getProduct_name()).isEqualTo(product_name);
        assertThat(all.get(0).getProduct_number()).isEqualTo(product_number);
        assertThat(all.get(0).getStock()).isEqualTo(stock);
        assertThat(all.get(0).getCategory()).isEqualTo(category);
        assertThat(all.get(0).getPrice()).isEqualTo(price);
    }


    // 상품 수정 테스트
    @Test
    public void product_update()throws Exception{

        // given
        Products savedProduct = productsRepository.save(Products.builder()
                .product_name("반팔티")
                .product_number("FA05163")
                .stock(100L)
                .category("Fashion")
                .price(30000D)
                .build());

        Long updateId=savedProduct.getId();
        String expectedName="코카콜라";
        String expectedNumber="DR54821";
        Long expectedStock=99L;
        String expectedCategory="Drinks";
        Double expectedPrice=20000D;

        ProductsUpdateRequestDto requestDto=
                ProductsUpdateRequestDto.builder()
                        .product_name(expectedName)
                        .product_number(expectedNumber)
                        .stock(expectedStock)
                        .category(expectedCategory)
                        .price(expectedPrice).build();

        String url = "http://localhost:"+port+"/api/v1/products/"+updateId;

        HttpEntity<ProductsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        // when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT,requestEntity,Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        List<Products> all = productsRepository.findAll();
        assertThat(all.get(0).getProduct_name()).isEqualTo(expectedName);
        assertThat(all.get(0).getProduct_number()).isEqualTo(expectedNumber);
        assertThat(all.get(0).getStock()).isEqualTo(expectedStock);
        assertThat(all.get(0).getCategory()).isEqualTo(expectedCategory);
        assertThat(all.get(0).getPrice()).isEqualTo(expectedPrice);

    }

    @Test
    public void product_delete() throws Exception {
        // given
        Products savedProduct = productsRepository.save(Products.builder()
                .product_name("멸치볶음")
                .product_number("FO05163")
                .stock(50L)
                .category("Food")
                .price(15000D)
                .build());

        Long deleteId = savedProduct.getId();
        String url = "http://localhost:" + port + "/api/v1/products/" + deleteId;

        // when
        ResponseEntity<Void> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(productsRepository.findById(deleteId)).isEmpty();
    }


}
