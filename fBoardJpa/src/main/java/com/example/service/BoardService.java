package com.example.service;

import java.util.List;

import com.example.domain.BoardVO;

public interface BoardService {
	
	public List<BoardVO> getBoardList();
	
	public void saveBoard(BoardVO vo);
	
	public BoardVO getBoard(BoardVO vo);
	
	public void deleteBoard(BoardVO vo);
	
	public void updateBoard(BoardVO vo);
}
