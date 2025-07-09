package com.example.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.BoardVO;
import com.example.persistence.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardRepository boardRepo;
	
	Logger log = LoggerFactory.getLogger(BoardServiceImpl.class);
	
	public List<BoardVO> getBoardList() {
		
		List<BoardVO> list = (List<BoardVO>) boardRepo.findAll();
		//결과 출력
		log.info("[BoardServiceImpl - getBoardList 요청]");
		
		return list;
	}
	
	public void saveBoard(BoardVO vo) {
		boardRepo.save(vo);
		
		log.info("[BoardServiceImpl - saveBoard 요청]"+vo);
	}
	
	public BoardVO getBoard(BoardVO vo) {
		BoardVO result = boardRepo.findById(vo.getSeq()).get();
		log.info("[BoardServiceImpl - getBoard 요청]" + result);
				
		return result;
		
	}
	
	public void deleteBoard(BoardVO vo) {
		boardRepo.deleteById(vo.getSeq());
		
		log.info("[BoardServiceImpl - deleteBoard 요청]");
		
	}
	
	public void updateBoard(BoardVO vo) {
		boardRepo.save(vo);
		
		log.info("[BoardServiceImpl - updateBoard 요청]");
	}
	
	/*
	  메서드 정리
	  - findAll()		: 전체 목록 검색
	  - findById()		: pk 게시글 검색
	  - save()			: 입력, 수정
	  - deleteById()	: pk 게시글 삭제 
	*/
}
