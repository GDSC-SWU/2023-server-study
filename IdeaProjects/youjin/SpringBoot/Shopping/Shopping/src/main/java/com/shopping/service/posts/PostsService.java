package com.shopping.service.posts;

import com.shopping.domain.posts.Posts;
import com.shopping.domain.posts.PostsRepository;
import com.shopping.web.dto.PostsUpdateRequestDto;
import com.shopping.web.dto.PostsReadResponseDto;
import com.shopping.web.dto.PostsCreateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsCreateRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다. id = "+id));

        posts.update(requestDto.getProduct_name(),requestDto.getProduct_number(), requestDto.getProduct_price(), requestDto.getProduct_stock());
        return id;
    }

    public PostsReadResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다. id = "+id));

        return new PostsReadResponseDto(entity);
    }
}
