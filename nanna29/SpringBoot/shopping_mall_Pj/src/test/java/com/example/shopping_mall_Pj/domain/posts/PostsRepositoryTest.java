package com.example.shopping_mall_Pj.domain.posts;

import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void 상품저장_불러오기() {
        //given
        String product_name="라이더 자켓";
        String product_number="A401";
        int stock=10;

        postsRepository.save(Posts.builder()
                .product_name(product_name)
                .product_number(product_number)
                .stock(stock)
                .price(13800)
                .category("가을 신상 아우터")
                .build());

        //when
        List<Posts> postsList=postsRepository.findAll();

        //then
        Posts posts=postsList.get(0);
        assertThat(posts.getProduct_name()).isEqualTo(product_name);
        assertThat(posts.getProduct_number()).isEqualTo(product_number);
    }

}