package com.example.dependency;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data /*get, set 만들어줌*/
@Component /*여기까지만 쓰면 초기화 생성자 만들어줌*/
//@NoArgsConstructor /*기본 생성자 생성 어노테이션*/
public class Coding {

//    필드 주입은 생성자 호출 후 주입이 된다.
    @Autowired
    private final Computer computer; /*오류*/

}
