package com.shopping.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

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
    public void 상품_저장_불러오기(){
        //given
        String product_name = "테스트 상품";
        String product_number = "F09E9";
        String product_category = "category";
        int product_price = 10000;
        int product_stock = 15;

        postsRepository.save(Posts.builder()
                .product_name(product_name)
                .product_number(product_number)
                .product_category(product_category)
                .product_price(product_price)
                .product_stock(product_stock)
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getProduct_name()).isEqualTo(product_name);
        assertThat(posts.getProduct_number()).isEqualTo(product_number);
        assertThat(posts.getProduct_category()).isEqualTo(product_category);
        assertThat(posts.getProduct_price()).isEqualTo(product_price);
        assertThat(posts.getProduct_stock()).isEqualTo(product_stock);
    }
}
