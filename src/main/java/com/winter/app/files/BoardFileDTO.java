package com.winter.app.files;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardFileDTO {

	private Long fileNum;
	private Long boardNum; // boardContents 대신 boardNum으로 변경
	private String fileName;
	private String oriName; // fileOrigin 대신 oriName으로 변경
	
}
