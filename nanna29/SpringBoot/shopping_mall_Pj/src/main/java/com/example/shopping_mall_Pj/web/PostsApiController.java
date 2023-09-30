package com.example.shopping_mall_Pj.web;

import com.example.shopping_mall_Pj.service.posts.PostsService;
import com.example.shopping_mall_Pj.web.dto.PostsCreateRequestDto;
import com.example.shopping_mall_Pj.web.dto.PostsReadDto;
import com.example.shopping_mall_Pj.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;

    @PostMapping("/api/shopping/posts") //등록 (생성)
    public Long save(@RequestBody PostsCreateRequestDto requestDto){
        return postsService.save(requestDto);
    }

    @PutMapping("/api/shopping/posts/{id}") //수정
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/shopping/posts/{id}") //읽기 (조회)
    public PostsReadDto findById (@PathVariable Long id){
        return postsService.findById(id);
    }
//
//    @DeleteMapping("/api/shopping/posts/{id}") //삭제
//    public
}
