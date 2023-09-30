package com.example.shopping_mall_Pj.web.dto;

import com.example.shopping_mall_Pj.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsDeleteRequestDto {
    private String product_number;
    private String category;

    @Builder
    public PostsDeleteRequestDto(String product_number, String category) {
        this.product_number=product_number;
        this.category=category;
    }
}
