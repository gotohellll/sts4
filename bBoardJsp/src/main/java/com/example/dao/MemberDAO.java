package com.example.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.domain.MemberVO;

@Mapper
public interface MemberDAO {
	
	@Insert("INSERT INTO member(id, name, pass) "
			+ "	VALUES(#{id}, #{name}, #{pass})")
	public void insertMember(MemberVO vo);
	
	@Select("SELECT * FROM member WHERE id=#{id}")
	public MemberVO selectMember(MemberVO vo);
}
