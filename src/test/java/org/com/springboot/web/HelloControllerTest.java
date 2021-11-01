package org.com.springboot.web;

import org.com.springboot.web.dto.HelloResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class HelloControllerTest{

    @Autowired
    private MockMvc mockMvc; //웹 API 테스트

    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";

        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    @Test
    public void helloDto가_리턴된다() throws Exception{
        String name = "test";
        int amount = 1000;

        HelloResponseDto helloResponseDto = new HelloResponseDto(name, amount);

        mockMvc.perform(get("/hello/dto")
                .param("name", name)
                .param("amount", String.valueOf(amount))) //param 값은 String 만 허용
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is(name))) //JSON 응답값을 필드별로 검증할 수 있는 메소드
                .andExpect(jsonPath("$.amount",is(amount)));

    }


}