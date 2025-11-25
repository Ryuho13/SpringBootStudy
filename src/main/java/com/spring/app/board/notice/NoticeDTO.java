package com.spring.app.board.notice;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class NoticeDTO {
	private Long boardNum;
	private String boardTitle;
	private String boardwWrriter;
	private String boardContnts;
	private LocalDate boardDate;
	private Long boardHit;
	
	
}
