package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class) // JUnit에 내장된 실행자 외 다른 실행자 실행. SpringRunner 실행
@WebMvcTest(controllers = HelloController.class) // Web에 집중할 수 있음. @Controller 등 사용 가능
public class HelloControllerTest {
    
    @Autowired // Bean 주입 받기
    private MockMvc mvc;

    @Test
    public void returnHello() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) // MockMvc를 통해 /hello 주소로 HTTP GET 요청
                .andExpect(status().isOk()) // isOk()의 status가 200인지 아닌지 검증
                .andExpect(content().string(hello)); // Controller에서 "Hello"를 정상적으로 Return하는지 검증
    }
}
