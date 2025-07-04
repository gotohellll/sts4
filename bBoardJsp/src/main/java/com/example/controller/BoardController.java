package com.example.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.BoardVO;
import com.example.service.BoardService;



@Controller
@RequestMapping("board")
public class BoardController {
	//로그 파일로 저장
	Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService service;
	
	@RequestMapping("/getBoardList")
	public void getBoardList(Model m) {
		
		logger.info("[BoardController - getBoardList] 요청");
		
		List<BoardVO> list = service.getBoardList();
		
		m.addAttribute("boardList", list);
		
		
		
	}
	
	@RequestMapping("/{step}")
	public String insertBoard(@PathVariable String step) {
		
		logger.info("[BoardController] 요청 " + step);
		
		return "board/" + step;
	}
	
	@RequestMapping("/saveBoard")
	public String saveBoard(BoardVO vo) {
		
		logger.info("[BoardController - saveBoard] 요청");
		
		// [예시] writer의 암호화 => 회원가입에서 비밀번호 암호화할 때 사용 
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); // 암호화의 알고리즘
		logger.info("[ 암호화 하기 전] 작성자" + vo.getWriter()); //[ 암호화 하기 전] 작성자tester
		String secureWriter = encoder.encode(vo.getWriter());
		logger.info("[ 암호화 하기 후] 작성자" + secureWriter); //[ 암호화 하기 후] 작성자$2a$10$woKvWE3q7LUuone/N0frVuitdAN5XSEQfvQNt/B1pizb8jvPuNm5q
		// 암호화 한 값을 DB에 입력 
		
		logger.info("1>" + vo.getWriter());
		logger.info("2>" + secureWriter);

		// DB에 입력하기 
//		vo.setWriter(secureWriter);
//		service.saveBoard(vo); //DB입력 
		
		// -------------------------------------------
		// [예시] secureWriter : DB에서 가져온 값이라고 가정 => 로그인작업시 사용 
		BCryptPasswordEncoder encoder2 = new BCryptPasswordEncoder(); // 암호화의 알고리즘
		if(encoder2.matches(vo.getWriter(), secureWriter)) {
			logger.info(">>>>>> 매치");
		}else {
			logger.info(">>>>>> 불매치");
		}
		
		
		return "redirect:getBoardList";
	}
	
	@RequestMapping("/getBoard")
	public void getBoard(BoardVO vo, Model m) {
		
		logger.info("[BoardController - getBoard] 요청");
		
		BoardVO result = service.getBoard(vo);
		m.addAttribute("board", result);
	}
	
	@RequestMapping("/updateBoard")
	public String updateBoard(BoardVO vo) {
		
		logger.info("[BoardController - updateBoard] 요청");
		
		service.updateBoard(vo);
		
		return "redirect:getBoard?seq="+vo.getSeq();
		
	}
	
	@RequestMapping("/deleteBoard")
	public String deleteBoard(BoardVO vo) {
		
		logger.info("[BoardController - deleteBoard] 요청");
		
		service.deleteBoard(vo);
		
		return "redirect:getBoardList";
	}
}
