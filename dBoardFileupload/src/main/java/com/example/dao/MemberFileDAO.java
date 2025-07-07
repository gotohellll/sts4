package com.example.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.MemberFileVO;

@Mapper
public interface MemberFileDAO {
	
	public void insertMemberFile(MemberFileVO mvo);
	
}
