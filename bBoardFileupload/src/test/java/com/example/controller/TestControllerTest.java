package com.example.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

/*
	JUnit : 자바 테스팅 프레임워크
		- 단위테스트
		- 통합테스트
		
	별도의 서버 동작없이 모조품 : Mock 
	
	- JUnit 테스트 메소드 규칙 
		` 무조건 public
		` 무조건 void
		` 매개변수 없어야 함
		` 앞에 @Test 부착해야 함 

*/

@WebMvcTest(TestController.class) //TestController를 테스트하는 클래스
public class TestControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	

//	@Test
//	public void test() throws Exception{
//		mockMvc.perform(get("/test.do"))
//			.andExpect(status().isOk()); //test.do를 요청했을때 잘 호출이 되었는지 (상태가 isOk인지) 
//	}
	
	// 인자가 있는 경우
//	@Test
//	public void test() throws Exception{
//		mockMvc.perform(get("/test.do")
//			.param("number", "1004"))
//			.andExpect(status().isOk()); //test.do를 요청했을때 잘 호출이 되었는지 (상태가 isOk인지) 
//	}
	
	// 인자가 2개인 경우
	@Test
	public void test() throws Exception{
		mockMvc.perform(get("/test.do")
			.param("number", "1004")
			.param("number2", "1234"))
//			.andDo(print())
			.andExpect(status().isOk()); //test.do를 요청했을때 잘 호출이 되었는지 (상태가 isOk인지) 
	}
	
	@Test
	public void test2() throws Exception{
		mockMvc.perform(get("/happyPage.do")
				.param("name", "홍길동"))
//				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(view().name("happyPage")) //view페이지 name이 happyPage가 맞는지 확인 
				.andExpect(model().attribute("greeting", "홍길동님 반갑습니다"));
	
	}	
	
}
