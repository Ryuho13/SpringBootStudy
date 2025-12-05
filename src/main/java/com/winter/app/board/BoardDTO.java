package com.winter.app.board;

import java.util.List;

import com.winter.app.board.BoardFileDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//게시판관련 DTO의 부모로 사용
@Getter
@Setter
@ToString
public class BoardDTO extends CommentDTO{
	
	//@Size(min = 3, max = 15)
	@NotBlank(message = "필수 입니다.")
	private String boardTitle;    
	// private String boardWriter;   
	// private LocalDate boardDate;     
	private Long boardHit;
	private String boardContents;
	
	private List<BoardFileDTO> fileDTOs;
	
	
}
