package com.example.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.BoardVO;
import com.example.service.BoardService;
/*
[Restful 방식] : url을 동일하게 쓰면서 CRUD 사용
의미				http	메소드
Create(insert)	POST(*)
Read(select)	GET(*)
Update			PUT
Delete			DELETE

(*)표준
put, delete는 표준안이 아님 


[기존 URL과 Restful 비교]

` 게시판 목록보기	/board/getBoardList				/board			GET
` 게시글 입력화면	/board/insertBoard				/board/write	GET
` 게시글 입력(작성)	/board/saveBoard				/board/write	POST
` 게시글 상세보기	/board/getBoard?seq=글번호		/board/글번호		GET
` 게시글 수정		/board/updateBoard?seq=글번호		/board/글번호		PUT
` 게시글 삭제		/board/deleteBoard?seq=글번호		/board/글번호		DELETE

GET : 화면에 나타내는것
POST :입력 
*/


@RestController // *******반드시 rest controller : 100% 비동기인 경우를 처리할 때 사용 (ex 댓글) -> 화면이없고 프론트에서 비동기로 넘어왔을때 사용 
@RequestMapping("/")
public class BoardRestfulController {
	//로그 파일로 저장
	Logger logger = LoggerFactory.getLogger(BoardRestfulController.class);
	
	@Autowired
	private BoardService service;
	
	@GetMapping("/board") //get방식은 브라우저에서 url입력해서 확인가능
	public List<BoardVO> getBoardList() { //화면이 없어 Model도 필요없음 (Model은 화면으로 끌고감)
		
		logger.info("[BoardController - getBoardList] 요청");
		
		List<BoardVO> list = service.getBoardList();
		
		return list;	
	}
	
	
	@PostMapping("/board") //화면에 form태그를 통해 전달받음 = post
	public void saveBoard(BoardVO vo) {
		
		logger.info("[BoardController - saveBoard] 요청");
				
		service.saveBoard(vo); //DB입력 
				
	}
	
	@GetMapping("/board/{seq}")
	public BoardVO getBoard(@PathVariable Integer seq) {
		
		logger.info("[BoardController - getBoard] 요청");
		BoardVO vo = new BoardVO();
		vo.setSeq(seq);
		BoardVO result = service.getBoard(vo);
		
		return result;
	}
	
	@PutMapping("/board/{seq}")
	public void updateBoard(BoardVO vo,@PathVariable Integer seq) {
		
		logger.info("[BoardController - updateBoard] 요청");
		
		service.updateBoard(vo);
		
		
		
	}
	
	@DeleteMapping("/board/{seq}")
	public void deleteBoard(BoardVO vo, @PathVariable Integer seq) {
		
		logger.info("[BoardController - deleteBoard] 요청");
		
		service.deleteBoard(vo);
		
		
		
	}
}
