package com.example.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.BoardVO;

@Mapper
public interface BoardDAO {
	
	// 게시글 전체 목록보기
	//<select id="getBoardList" resultType="BoardVO"> parameter없음
//	@Select("SELECT * FROM board ORDER BY seq DESC") //어노테이션 사용하려면 application.properties에서 mapper location 주석
	public List<BoardVO> getBoardList();
	
	// 게시글 번호의 상세 검색 
	//<select id="getBoard" parameterType="BoardVO" resultType="BoardVO"> parameter가 인자로
//	@Select("SELECT * FROM board WHERE seq=#{seq}")
	public HashMap getBoard(BoardVO vo);
	
	// 게시글 입력
	//<insert id="saveBoard" parameterType="BoardVO"> result가없어 void 또는 Integer
//	@Insert("INSERT INTO board(title, writer, content, regdate, cnt) "
//			+ "		VALUES (#{title}, #{writer}, #{content}, now(), 0)")
	public void saveBoard(BoardVO vo);
	// public abstract 생략
	
	// 게시글 수정
	public void updateBoard(BoardVO vo);
	
	// 게시글 삭제
	public void deleteBoard(BoardVO vo);
	
	// 최근 입력한 게시글 번호 검색 
	public Integer selectId();
}
