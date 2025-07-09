package com.example.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.BoardVO;
import com.example.service.BoardService;


@RestController // *******반드시 rest controller : 100% 비동기인 경우를 처리할 때 사용 (ex 댓글) -> 화면이없고 프론트에서 비동기로 넘어왔을때 사용 
@RequestMapping("board")
public class BoardController {
	//로그 파일로 저장
	Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService service;
	
	@GetMapping("/getBoardList") //get방식은 브라우저에서 url입력해서 확인가능
	public List<BoardVO> getBoardList() { //화면이 없어 Model도 필요없음 (Model은 화면으로 끌고감)
		
		logger.info("[BoardController - getBoardList] 요청");
		
		List<BoardVO> list = service.getBoardList();
		
		return list;
		
		
		
	}
	
	
	@PostMapping("/saveBoard") //화면에 form태그를 통해 전달받음 = post
	public void saveBoard(BoardVO vo) {
		
		logger.info("[BoardController - saveBoard] 요청");
				
		service.saveBoard(vo); //DB입력 
				
	}
	
	@GetMapping("/getBoard")
	public BoardVO getBoard(BoardVO vo) {
		
		logger.info("[BoardController - getBoard] 요청");
		
		BoardVO result = service.getBoard(vo);
		
		return result;
	}
	
	@RequestMapping("/updateBoard")
	public void updateBoard(BoardVO vo) {
		
		logger.info("[BoardController - updateBoard] 요청");
		
		service.updateBoard(vo);
		
	}
	
	@RequestMapping("/deleteBoard")
	public void deleteBoard(BoardVO vo) {
		
		logger.info("[BoardController - deleteBoard] 요청");
		
		service.deleteBoard(vo);
		
		
	}
}
