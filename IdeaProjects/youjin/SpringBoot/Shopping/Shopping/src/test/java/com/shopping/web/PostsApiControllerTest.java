package com.shopping.web;

import com.shopping.domain.posts.Posts;
import com.shopping.domain.posts.PostsRepository;
import com.shopping.web.dto.PostsCreateRequestDto;
import com.shopping.web.dto.PostsUpdateRequestDto;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void tearDown() throws Exception{
        postsRepository.deleteAll();
    }

    @Test
    public void Posts_등록() throws Exception{
        //given
        String product_name = "product_name";
        String product_number = "product_number";
        String product_category = "product_category";
        int product_price = 10000;
        int product_stock = 15;

        PostsCreateRequestDto requestDto = PostsCreateRequestDto.builder()
                .product_name(product_name)
                .product_number(product_number)
                .product_category(product_category)
                .product_price(product_price)
                .product_stock(product_stock)
                .build();

        String url = "http://localhost:" + port + "/api/shopping/posts";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getProduct_name()).isEqualTo(product_name);
        assertThat(all.get(0).getProduct_number()).isEqualTo(product_number);
        assertThat(all.get(0).getProduct_category()).isEqualTo(product_category);
        assertThat(all.get(0).getProduct_price()).isEqualTo(product_price);
        assertThat(all.get(0).getProduct_stock()).isEqualTo(product_stock);
    }

    @Test
    public void Posts_수정() throws Exception{
        //given
        Posts savedPosts = postsRepository.save(Posts.builder()
                .product_name("product_name")
                .product_number("EDD578")
                .product_price(12000)
                .product_stock(10)
                .build());

        Long updateNum = savedPosts.getId();
        String expectedName="product_name2";
        String expectedNumber = "product_number2";
        int expectedStock=15;
        int expectedPrice=32000;

        PostsUpdateRequestDto requestDto =
                PostsUpdateRequestDto.builder()
                        .product_name(expectedName)
                        .product_number(expectedNumber)
                        .product_stock(expectedStock)
                        .product_price(expectedPrice)
                        .build();


        String url = "http://localhost:" + port + "/api/shopping/posts/" + updateNum;


        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        // when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT,
                requestEntity, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getProduct_name()).isEqualTo(expectedName);
        assertThat(all.get(0).getProduct_number()).isEqualTo(expectedNumber);
        assertThat(all.get(0).getProduct_price()).isEqualTo(expectedPrice);
        assertThat(all.get(0).getProduct_stock()).isEqualTo(expectedStock);
    }

//    @Test
//    public void Posts_삭제(){
//        //given
//        Posts savedPosts = postsRepository.save(Posts.builder()
//                .product_name("product_name")
//                .product_price(12000)
//                .product_stock(10)
//                .build());
//
//        Long id = savedPosts.getId();
//
//        String url = "http://localhost:" + port + "/api/shopping/posts/" + id;
//
//        HttpEntity<Posts> requestEntity = new HttpEntity<>(savedPosts);
//
//        // when
//        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Long.class);
//
//        // then
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(responseEntity.getBody()).isGreaterThan(0L);
//
//        postsRepository.deleteById(id);
//    }
}