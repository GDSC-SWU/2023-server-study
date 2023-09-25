package com.test.admin.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is; // bytebuddy 사용 시 에러나서 hamcrest로 변경
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest
public class HelloControllerTest {
    @Autowired
    private MockMvc mvc; // 웹 API 테스트를 위해 사용

    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello")) // 해당 주소로 HTTP GET 요청
                .andExpect(status().isOk()) // HTTP Header의 Status를 검증
                .andExpect(content().string(hello)); // 응답 본문 내용을 검증

    }

    @Test
    public void heloDto가_리턴된다() throws Exception{
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name", name) // API 테스트할 때 사용될 요청 파라미터를 설정
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is(name))) // JSON 응답값을 필드별로 검증할 수 있는 메소드
                .andExpect(jsonPath("$.amount", is(amount)));

    }
}
