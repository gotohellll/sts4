package com.example.service;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.BoardDAO;
import com.example.dao.FileDAO;
import com.example.domain.BoardVO;
import com.example.domain.FileVO;



@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDAO boardDAO;
	
	@Autowired
	private FileDAO fileDAO;
	
	Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);
	
	public List<BoardVO> getBoardList(){
		return boardDAO.getBoardList();
	}
		
	@Transactional //하려면 둘다 실행, 하나라도 문제 발생하면 둘다 실행하지 않게 트랜젝션 처리 
	public void saveBoard(BoardVO vo, FileVO fvo) {
		//파일 첨부가 없는 게시글 입력 
		boardDAO.saveBoard(vo);
		
		//파일 첨부가 있는 경우
		if(fvo != null) {
			fvo.setBoard_seq(boardDAO.selectId()); //방금입력한 게시글번호를 얻어와 지정(set)
			fileDAO.insertFile(fvo); // 파일 입력 
		}

	}
	
	public HashMap getBoard(BoardVO vo) {
		logger.info(">>>상세보기 내용 PK값 : "+vo);
		HashMap map = boardDAO.getBoard(vo);
		logger.info(">>>상세보기 내용 출력 : "+map);
		
		return map;
	}
	
	public void deleteBoard(BoardVO vo) {
		boardDAO.deleteBoard(vo);
	}
	
	public void updateBoard(BoardVO vo) {
		boardDAO.updateBoard(vo);
	}
}
