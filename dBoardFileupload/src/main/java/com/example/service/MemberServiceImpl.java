package com.example.service;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.MemberDAO;
import com.example.dao.MemberFileDAO;
import com.example.domain.MemberFileVO;
import com.example.domain.MemberVO;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private MemberFileDAO memberFileDAO;
	
	Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
	
	@Transactional
	public void insertMember(MemberVO vo, MemberFileVO mvo) {
		//파일 첨부가 없는 회원가입
		memberDAO.insertMember(vo);
		
		//파일 첨부 있는 경우
		if(mvo != null) {
			mvo.setMember_id(vo.getId());
			logger.info(vo.getId());
			memberFileDAO.insertMemberFile(mvo);//파일입력
		}
	}
	
	public HashMap selectMember(MemberVO vo) {
		logger.info(">>>상세보기 내용 PK값 : "+vo); //입력값만 출력됨 
		HashMap map = memberDAO.selectMember(vo);
		logger.info(">>>상세보기 내용 출력 : "+map);
		return map;
	}
}
