package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.domain.BoardVO;

@Mapper
public interface BoardDAO {
	
	//<select id="getBoardList" resultType="BoardVO"> parameter없음
	@Select("SELECT * FROM board ORDER BY seq DESC") //어노테이션 사용하려면 application.properties에서 mapper location 주석
	public List<BoardVO> getBoardList();
	
	//<select id="getBoard" parameterType="BoardVO" resultType="BoardVO"> parameter가 인자로
	@Select("SELECT * FROM board WHERE seq=#{seq}")
	public BoardVO getBoard(BoardVO vo);
	
	//<insert id="saveBoard" parameterType="BoardVO"> result가없어 void 또는 Integer
	@Insert("INSERT INTO board(title, writer, content, regdate, cnt) "
			+ "		VALUES (#{title}, #{writer}, #{content}, now(), 0)")
	public void saveBoard(BoardVO vo);
	// public abstract 생략
	
	
	public void updateBoard(BoardVO vo);
	
	public void deleteBoard(BoardVO vo);
}
