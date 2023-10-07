package com.example.ShoppingmallApi.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void 상품_불러오기(){
        //given
        String product_name = "코트";
        int price = 50000;
        String product_number = "E98Y512";
        int stock = 100;
        String category = "Outer";

        postsRepository.save(Posts.builder().product_name(product_name).price(price).product_number(product_number)
                .stock(stock).category(category).build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getProduct_name()).isEqualTo(product_name);
        assertThat(posts.getPrice()).isEqualTo(price);
        assertThat(posts.getProduct_number()).isEqualTo(product_number);
        assertThat(posts.getStock()).isEqualTo(stock);
        assertThat(posts.getCategory()).isEqualTo(category);
    }
    @Test
    public void BaseTimeEntity_등록(){
        //given
        LocalDateTime now = LocalDateTime.of(2023,10,7,0,0,0);
        Posts savedPosts = postsRepository.save(Posts.builder().product_name("product_name").price(50000).product_number("product_number")
                .stock(100).category("category").build());
        //when
        List<Posts> postsList = postsRepository.findAll();
        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>> createDate="+posts.getCreatedDate()+", modifiedDate="+posts.getModifiedDate());
        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}