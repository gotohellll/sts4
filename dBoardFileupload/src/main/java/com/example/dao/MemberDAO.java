package com.example.dao;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.MemberVO;

@Mapper
public interface MemberDAO {
	
//	@Insert("INSERT INTO member(id, name, pass) "
//			+ "	VALUES(#{id}, #{name}, #{pass})")
	public void insertMember(MemberVO vo);
	
//	@Select("SELECT * FROM member WHERE id=#{id}")
	public HashMap selectMember(MemberVO vo);
	
	public String selectId();
}
