package com.example.service;


import java.util.HashMap;
import java.util.List;

import com.example.domain.BoardVO;
import com.example.domain.FileVO;

public interface BoardService {
	
	List<BoardVO> getBoardList();
	
	void saveBoard(BoardVO vo, FileVO fvo);
	
	HashMap getBoard(BoardVO vo);
	
	void deleteBoard(BoardVO vo);
	
	void updateBoard(BoardVO vo);
}
