package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.domain.MemberVO;
import com.example.service.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("member")
public class MemberController {
	
	Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberService service;
	
	@RequestMapping("/{step}")
	public String main(@PathVariable String step) {
		
		logger.info("[MemberController] 요청 " + step);
		
		return "member/" + step;
	}
	
	@RequestMapping("/insertMember")
	public String insertMember(MemberVO vo) {
		logger.info("[MemberController - insertMember] 요청");
		
		// 비밀번호 암호화 
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); // 암호화의 알고리즘
		logger.info("[ 암호화 하기 전] 비밀번호" + vo.getPass()); //[ 암호화 하기 전] 작성자tester
		String securePass = encoder.encode(vo.getPass());
		logger.info("[ 암호화 하기 후] 비밀번호" + securePass);
		
		vo.setPass(securePass);
		service.insertMember(vo);
		
		return "redirect:saveMember";
	}
	
	//로그인
	@RequestMapping("/selectMember")
	public String selectMember(MemberVO vo, HttpSession session) {
		logger.info("[MemberController - selectMember] 요청");
		
		MemberVO result = service.selectMember(vo);
		
		if(result==null) {
			return "redirect:loginForm";
		}else {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			
			if(encoder.matches(vo.getPass(), result.getPass())) { //vo.getPass: 입력받은값, result.getPass: DB값
				session.setAttribute("logname", result.getId());
			}else {
				logger.info("로그인 실패");
		
				return "redirect:loginForm";
			}
			return "redirect:loginSuccess";
		}
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		logger.info("[MemberController - logout] 요청");
		return "redirect:/member/loginForm";
	}
	
}
