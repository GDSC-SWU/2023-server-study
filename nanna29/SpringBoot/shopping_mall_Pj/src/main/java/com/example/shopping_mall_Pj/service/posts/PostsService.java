package com.example.shopping_mall_Pj.service.posts;

import com.example.shopping_mall_Pj.domain.posts.Posts;
import com.example.shopping_mall_Pj.domain.posts.PostsRepository;
import com.example.shopping_mall_Pj.web.dto.PostsCreateRequestDto;
import com.example.shopping_mall_Pj.web.dto.PostsReadDto;
import com.example.shopping_mall_Pj.web.dto.PostsUpdateRequestDto;
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
    public Long update(Long id, PostsUpdateRequestDto responseDto){
        Posts post=postsRepository.findById(id).
                orElseThrow(()->new IllegalArgumentException("해당 상품이 없습니다. id="+id));

        post.update(responseDto.getProduct_name(), responseDto.getStock(), responseDto.getPrice());

        return id;
    }

    public PostsReadDto findById (Long id){
        Posts entity=postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 상품이 없습니다. id="+id));

        return new PostsReadDto(entity);
    }

    @Transactional
    public void delete (Long id) {
        Posts posts=postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 상품이 없습니다. id=" + id));

        postsRepository.delete(posts);
    }
}
