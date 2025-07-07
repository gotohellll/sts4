package com.example.domain;

import lombok.Data;

@Data
public class BoardVO {
	
	private Integer seq;
	private String title;
	private String writer;
	private String content;
	private String regdate;
	private Integer cnt;
}

/*
    @Data 또는 
    
    필요한것만 사용 
 	@Setter
 	@Getter
 	@ToString
 	@NoArgsConstructor
 	@AllArgsConstructor
 */
