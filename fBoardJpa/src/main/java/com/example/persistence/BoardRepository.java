package com.example.persistence;

import org.springframework.data.repository.CrudRepository;

import com.example.domain.BoardVO;

//CrudRepository<엔티티클래스, PK자료형> 상속받기
														//무슨 테이블과 연결할건지 , PK의 타입이 무엇인지
public interface BoardRepository extends CrudRepository<BoardVO, Integer> {

}
