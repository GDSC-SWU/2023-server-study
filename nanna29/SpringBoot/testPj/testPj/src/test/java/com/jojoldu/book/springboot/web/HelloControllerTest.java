package com.jojoldu.book.springboot.web;

import org.junit.Test;
import org.junit.runner. RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// 오류 발생: JPA metamodel must not be empty!
// 오류 원인: Application 파일의 @EnableJpaAuditing 와 Test Code 에 정의한 @WebMvcTest 어노테이션 때문
// 상세 설명: @EnableJpaAuditing 어노테이션은 JPA 에 관한 Bean입니다. 그러나 @WebMvcTest 는 JPA 관련 Bean 들을 로드하지 않음
//          (@WebMvcTest 는 대표적으로 @Controller , @ControllerAdvice 어노테이션을 Bean 으로 로드)
//          그래서 작성한 Test Code 에서 빈을 로드할 수 없기 때문에 JPA metamodel must not be empty! 에러가 발생
// 오류 해결: @WebMvcTest 를 @SpringBootTest 변경, @AutoConfigureMockMvc 어노테이션 추가
// @WebMvcTest (controllers = HelloController.class)
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HelloControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void hello가_리턴된다()throws Exception{
        String hello="hello";
        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                        get("/hello/dto")
                                .param("name", name)
                                .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}