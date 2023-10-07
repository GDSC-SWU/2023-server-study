package com.example.ShoppingmallApi.service.posts;

import com.example.ShoppingmallApi.domain.posts.Posts;
import com.example.ShoppingmallApi.domain.posts.PostsRepository;
import com.example.ShoppingmallApi.web.dto.PostsCreateDto;
import com.example.ShoppingmallApi.web.dto.PostsListReadDto;
import com.example.ShoppingmallApi.web.dto.PostsReadDto;
import com.example.ShoppingmallApi.web.dto.PostsUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsCreateDto requestDto) { //Create
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateDto requestDto){ //Update
        Posts posts = postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 상품이 없습니다. id=" + id));
        posts.update(requestDto.getProduct_name(), requestDto.getPrice(), requestDto.getProduct_number(), requestDto.getStock(), requestDto.getCategory());
        return id;
    }

    @Transactional
    public PostsReadDto findById(Long id){ //Read
        Posts entity = postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new PostsReadDto(entity);
    }

    @Transactional
    public void delete(Long id){ //Delete
        Posts posts = postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        postsRepository.delete(posts);
    }
    @Transactional(readOnly = true)
    public List<PostsListReadDto> findAllDesc() {
        return postsRepository.findAllDesc().stream().map(PostsListReadDto::new).collect(Collectors.toList());
    }
}