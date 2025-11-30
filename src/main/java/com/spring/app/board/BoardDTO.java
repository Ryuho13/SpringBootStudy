package com.spring.app.board;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BoardDTO {
	private Long boardNum;
	private String boardTitle;
	private String boardWriter;
	private String boardContents;
	private LocalDate boardDate;
	private Long boardHit;

}
