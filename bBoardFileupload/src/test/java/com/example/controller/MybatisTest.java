package com.example.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.domain.BoardVO;

@SpringBootTest
public class MybatisTest {
	
	Logger log = LoggerFactory.getLogger(MybatisTest.class);
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Test
	public void selectBoardTest(){
		
		try {
			SqlSession sess = sqlSessionFactory.openSession();
			List<BoardVO> list = sess.selectList("com.example.dao.BoardDAO.getBoardList");
			for(BoardVO vo:list) {
				log.info("[BoardVO 결과]"+ vo.toString());
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	@Test
	public void insertBoardTest() {
		try {
			SqlSession sess = sqlSessionFactory.openSession();
			BoardVO vo = new BoardVO();
			vo.setTitle("제목");
			vo.setWriter("홍길동");
			vo.setContent("오늘도 ");
			
			int result = sess.insert("com.example.dao.BoardDAO.saveBoard", vo);
			//몇 개 행을 수행했는지 int값 리턴
			
			//확인작업
			assertEquals(1, result);
			
		}catch(Exception ex) {
			
		}
		
	}
}
