package com.jojoldu.book.springboot.service.posts;
import com.jojoldu.book.springboot.domain.posts.Posts;
import com.jojoldu.book.springboot.domain.posts.PostsRepository;
import com.jojoldu.book.springboot.web.dto.PostsListResponseDto;
import com.jojoldu.book.springboot.web.dto.PostsResponseDto;
import com.jojoldu.book.springboot.web.dto.PostsSaveRequestDto;
import com.jojoldu.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service

public class PostsService {
    private final PostsRepository postsRepository;

    //생성
    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    //수정
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto responseDto){
        Posts post=postsRepository.findById(id).
                orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        post.update(responseDto.getTitle(), responseDto.getContent());

        return id;
    }

    //수정 후 재조회
    public PostsResponseDto findById (Long id){
        Posts entity=postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        return new PostsResponseDto(entity);
    }

    @Transactional (readOnly=true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    //삭제
    @Transactional
    public void delete(Long id){
        Posts posts=postsRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        postsRepository.delete(posts);
    }


}
