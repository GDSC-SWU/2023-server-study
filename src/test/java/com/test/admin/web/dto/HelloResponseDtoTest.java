package com.test.admin.web.dto;

import org.junit.Test;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.assertj.core.api.Assertions.assertThat;


public class HelloResponseDtoTest {

    @Test
    public void 룸복_기능_테스트(){
        String name = "test";
        int amount =1000;

        HelloResponseDto dto = new HelloResponseDto(name, amount);

        // 테스트 검증 메소드로, 검증하고 싶은 대상을 메소드 인자로 받음
        assertThat(dto.getName()).isEqualTo(name); // 동등 비교 메소드
        assertThat(dto.getAmount()).isEqualTo(amount);
    }

}
