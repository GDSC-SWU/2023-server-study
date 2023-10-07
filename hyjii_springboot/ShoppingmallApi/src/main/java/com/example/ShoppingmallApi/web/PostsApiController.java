package com.example.ShoppingmallApi.web;

import com.example.ShoppingmallApi.service.posts.PostsService;
import com.example.ShoppingmallApi.web.dto.PostsCreateDto;
import com.example.ShoppingmallApi.web.dto.PostsReadDto;
import com.example.ShoppingmallApi.web.dto.PostsUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts") //Create
    public Long save(@RequestBody PostsCreateDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}") //Update
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateDto requestDto){
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}") //Read
    public PostsReadDto findById (@PathVariable Long id) {
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}") //Delete
    public Long delete(@PathVariable long id){
        postsService.delete(id);
        return id;
    }
}