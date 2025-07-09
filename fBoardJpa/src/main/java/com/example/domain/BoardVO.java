package com.example.domain;

import java.util.Date;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

// VO != DTO != Entity 

@Data
@Entity //DB의 테이블과 매핑
//@Table(name="board") //기존 테이블과 매핑하려면 사용 없으면 클래스명과 똑같은테이블 생성
public class BoardVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer seq;
	
	private String title;
	
	@Column(updatable = false) //수정시 writer  null로 바뀌지 않게 
	private String writer;
	
	@Column(length = 500) // mysql에서는 글자수, 오라클에서는 byte 수
	private String content;
	
	//날짜 넣기 (둘다가능)
//	@CreationTimestamp
	@UpdateTimestamp
	private Date regdate;
	
//	@ColumnDefault("0") //기본값 0 적용
	@Column(columnDefinition = "integer default 0", insertable = false) // ColumnDefault와 같은 맥락이나 속성 하나를 더 줄수있다, insertable : insert할때 cnt를 넣지 않겠다  
	private Integer cnt;
}
