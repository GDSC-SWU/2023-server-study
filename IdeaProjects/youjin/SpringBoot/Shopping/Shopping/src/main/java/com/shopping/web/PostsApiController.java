package com.shopping.web;

import com.shopping.service.posts.PostsService;
import com.shopping.web.dto.PostsReadResponseDto;
import com.shopping.web.dto.PostsCreateRequestDto;
import com.shopping.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;

    @PostMapping("/api/shopping/posts") //Create
    public Long save(@RequestBody PostsCreateRequestDto requestDto){
        return postsService.save(requestDto);
    }

    @PutMapping("/api/shopping/posts/{id}") //Update
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {

        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/shopping/posts/{id}") //Read
    public PostsReadResponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }

//    @DeleteMapping("/api/shopping/posts/delete/{id}") //Delete
//    public Long delete(@PathVariable Long id) {
//        System.out.println(id);
//    }
}
