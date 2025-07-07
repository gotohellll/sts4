package com.example.service;

import java.util.HashMap;

import com.example.domain.MemberFileVO;
import com.example.domain.MemberVO;

public interface MemberService {
	void insertMember(MemberVO vo, MemberFileVO mvo);
	
	HashMap selectMember(MemberVO vo);
}
