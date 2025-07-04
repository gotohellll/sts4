package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	
	Logger log = LoggerFactory.getLogger(TestController.class); 
	
//	@RequestMapping("/test.do")
//	public void test() {
//		log.info("[TestController] test.do 요청");	
//	}

	// 인자가 있는 경우
//	@RequestMapping("/test.do")
//	public void test(String number) {
//		log.info("[TestController] test.do 요청"+number);	
//	}
	
	// 인자가 2개인 경우
	@RequestMapping("/test.do")
	public void test(String number, String number2) {
		log.info("[TestController] test.do 요청"+number+"/"+number2);	
	}
	
	@RequestMapping("/happyPage.do") //자동으로 happyPage가 열려 greeting과 message를 끌고감 
	public void test2(String name, Model m) {
		m.addAttribute("greeting", name+"님 반갑습니다");
		m.addAttribute("message" , "오늘도 행복합시다");
	}
	
}
