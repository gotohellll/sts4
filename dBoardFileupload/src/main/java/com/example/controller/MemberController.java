package com.example.controller;

import java.io.File;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.domain.MemberFileVO;
import com.example.domain.MemberVO;
import com.example.service.MemberService;
import com.example.util.MD5Generator;

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
	public String insertMember(MemberVO vo, @RequestParam("file") MultipartFile files) {
		logger.info("[MemberController - insertMember] 요청");
		
		// 비밀번호 암호화 
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); // 암호화의 알고리즘
		logger.info("[ 암호화 하기 전] 비밀번호" + vo.getPass()); //[ 암호화 하기 전] 작성자tester
		String securePass = encoder.encode(vo.getPass());
		logger.info("[ 암호화 하기 후] 비밀번호" + securePass);
		
		vo.setPass(securePass);
		
		
		try {
			//파일 원래 이름
			String originFilename = files.getOriginalFilename();
//				logger.info("원래 파일명"+originFilename);
			//파일 첨부한 경우 
			if(originFilename != null && !originFilename.equals("")) {
				//변경된 파일 이름
				String filename = new MD5Generator(originFilename).toString();
				
				String savepath = System.getProperty("user.dir");
				savepath+="\\src\\main\\resources\\static\\memberFiles";
				
				if(!new File(savepath).exists()) {
					new File(savepath).mkdir();
				}
				
				String filepath = savepath + "\\" + filename;
				files.transferTo(new File(filepath)); //실질적인 파일 저장 
				logger.info("[실제 저장 경로] : "+ filepath);
				//DB저장
				MemberFileVO mFileVO = new MemberFileVO();
				mFileVO.setFilename(filename);
				mFileVO.setOriginFilename(originFilename);
				mFileVO.setFilepath(filepath);
				
				
				service.insertMember(vo, mFileVO);
			}else {
				//파일 첨부하지 않은 경우 
				service.insertMember(vo, null);
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return "redirect:saveMember";
	}
	
	//로그인
	@RequestMapping("/selectMember")
	public String selectMember(MemberVO vo, HttpSession session) {
		logger.info("[MemberController - selectMember] 요청");
		
		HashMap result = service.selectMember(vo);
		
		
		if(result==null) {
			return "redirect:loginForm";
		}else {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			
			if(encoder.matches(vo.getPass(), (String) result.get("pass"))) { //vo.getPass: 입력받은값, result.getPass: DB값
				session.setAttribute("userinfo", result);
				
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
