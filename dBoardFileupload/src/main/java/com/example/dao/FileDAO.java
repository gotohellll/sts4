package com.example.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.FileVO;

@Mapper
public interface FileDAO {
//화면설계도를 보고 필요한 sql문장 먼저 뽑은 다음 Mapper보면서 DAO 완성 
	
	//parameterType="FileVO" => 인자 / resultType없음 => void 또는 int
	public void insertFile(FileVO fvo);
	
	//parameterType="FileVO" => 인자 /  resultType="FileVO" => 리턴값 
	public FileVO selectFile(FileVO fvo);
}
