package com.example.ShoppingmallApi.web;

import com.example.ShoppingmallApi.domain.posts.Posts;
import com.example.ShoppingmallApi.domain.posts.PostsRepository;
import com.example.ShoppingmallApi.web.dto.PostsCreateDto;
import com.example.ShoppingmallApi.web.dto.PostsUpdateDto;
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
public class PostsApiControllerTest {

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
    public void 등록() throws Exception {
        //given
        String product_name = "코트";
        int price = 50000;
        String product_number = "E98Y512";
        int stock = 100;
        String category = "Outer";
        PostsCreateDto requestDto = PostsCreateDto.builder().product_name(product_name).price(price).product_number(product_number).stock(stock)
                .category(category).build();
        String url = "http://localhost:" + port + "/api/v1/posts";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getProduct_name()).isEqualTo(product_name);
        assertThat(all.get(0).getPrice()).isEqualTo(price);
    }
    @Test
    public void 수정() throws Exception{
        //given
        Posts savedPosts = postsRepository.save(Posts.builder().product_name("product_name").price(50000).product_number("product_number").stock(100)
                .category("category").build());

        Long updateId = savedPosts.getId();
        String expectedProduct_name = "product_name";
        int expectedPrice = 40000;
        int expectedStock = 50;

        PostsUpdateDto requestDto = PostsUpdateDto.builder().product_name(expectedProduct_name).price(expectedPrice).stock(expectedStock).build();
        String url = "http://localhost:" + port + "/api/v1/posts/" + updateId;
        HttpEntity<PostsUpdateDto> requestEntity = new HttpEntity<>(requestDto);

        // when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getProduct_name()).isEqualTo(expectedProduct_name);
        assertThat(all.get(0).getPrice()).isEqualTo(expectedPrice);
    }
}