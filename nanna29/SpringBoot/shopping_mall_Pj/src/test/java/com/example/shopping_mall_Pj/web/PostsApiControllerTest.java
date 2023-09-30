package com.example.shopping_mall_Pj.web;

import com.example.shopping_mall_Pj.domain.posts.Posts;
import com.example.shopping_mall_Pj.domain.posts.PostsRepository;
import com.example.shopping_mall_Pj.web.dto.PostsCreateRequestDto;
import com.example.shopping_mall_Pj.web.dto.PostsUpdateRequestDto;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void tearDown() throws Exception {
        postsRepository.deleteAll();
    }

    @Test
    public void Posts_쇼핑몰_등록된다() throws Exception {
        //given
        String product_name="product_name";
        String product_number="product_number";
        int stock=5;
        PostsCreateRequestDto requestDto = PostsCreateRequestDto.builder()
                .product_name(product_name)
                .product_number(product_number)
                .stock(stock)
                .price(15000)
                .category("category")
                .build();

        String url="http://localhost:" + port + "/api/shopping/posts";

        //when
        ResponseEntity<Long> responseEntity=restTemplate.postForEntity(url, requestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all =postsRepository.findAll();
        assertThat(all.get(0).getProduct_name()).isEqualTo(product_name);
        assertThat(all.get(0).getProduct_number()).isEqualTo(product_number);
        assertThat(all.get(0).getStock()).isEqualTo(stock);
    }

    @Test
    public void Posts_쇼핑몰_수정된다() throws Exception {
        //given
        Posts savedPosts=postsRepository.save(Posts.builder()
                .product_name("product_name")
                .stock(50)
                .price(18000)
                .build());

        Long updateId=savedPosts.getId();
        String expectedProduct_name="product_name2";
        int expectedStock=15;
        int expectedPrice=32000;

        PostsUpdateRequestDto requestDto=PostsUpdateRequestDto.builder()
                .product_name(expectedProduct_name)
                .stock(expectedStock)
                .price(expectedPrice)
                .build();

        String url="http://localhost:" + port + "/api/shopping/posts/" + updateId;

        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        ResponseEntity<Long> responseEntity=restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all =postsRepository.findAll();
        assertThat(all.get(0).getProduct_name()).isEqualTo(expectedProduct_name);
        assertThat(all.get(0).getStock()).isEqualTo(expectedStock);
        assertThat(all.get(0).getPrice()).isEqualTo(expectedPrice);
    }


}