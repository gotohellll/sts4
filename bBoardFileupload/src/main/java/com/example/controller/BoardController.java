package com.example.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.domain.BoardVO;
import com.example.domain.FileVO;
import com.example.service.BoardService;
import com.example.util.MD5Generator;



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
	
	/*
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
	*/
	
	@RequestMapping("/saveBoard")       //RequestParam("file") => jsp의 name이 file
	public String saveBoard(BoardVO vo, @RequestParam("file") MultipartFile files) {
		
		logger.info("[BoardController - saveBoard] 요청");
		
		try {
			// 파일의 원래 이름
			String originFilename = files.getOriginalFilename();
//			logger.info("[원래 파일명] : "+originFilename);
			
			// 파일을 첨부한 경우
			if(originFilename != null && !originFilename.equals("")) {
				// 변경된 파일 이름
				String filename = new MD5Generator(originFilename).toString();
//				logger.info("[변경된 파일명] : "+filename);
				
				//********추후 작업 : 확장자 붙이기 
				
				// 정해진 폴더를 지정 (절대경로 쓰면 안됨)
				String savepath = System.getProperty("user.dir");
				savepath+="\\src\\main\\resources\\static\\files"; //카테고리별로 변수잡아서 처리 
//				logger.info("[저장경로] : "+ savepath);
				
				if(!new File(savepath).exists()) { //files 폴더가 존재하지 않으면
					new File(savepath).mkdir(); //생성 
				}
				
				//실제 파일을 저장하고 디비에 파일정보를 입력하기 
				String filepath = savepath + "\\" + filename;
				files.transferTo(new File(filepath)); //실질적인 파일 저장 
				logger.info("[실제 저장 경로] : "+ filepath);
				
				// 디비 저장
				FileVO fileVO = new FileVO();
				fileVO.setFilename(filename);
				fileVO.setOriginFilename(originFilename);
				fileVO.setFilepath(filepath); //추후에 savepath (원래는 경로만 넣는것을 권장) => 리눅스베이스에서 어떻게 넣어야할지 고민
				
				service.saveBoard(vo, fileVO);
			}else {
				//파일을 첨부하지 않은 경우
				service.saveBoard(vo, null);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		
		return "redirect:getBoardList";
	}
	
	@RequestMapping("/getBoard")
	public void getBoard(BoardVO vo, Model m) {
		
		logger.info("[BoardController - getBoard] 요청");
		
//		BoardVO result = service.getBoard(vo); //HashMap으로 변경 
		HashMap result = service.getBoard(vo);
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
