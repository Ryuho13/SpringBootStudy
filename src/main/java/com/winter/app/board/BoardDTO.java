package com.winter.app.board;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//게시판관련 DTO의 부모로 사용
@Setter
@Getter
@ToString
public class BoardDTO extends CommentDTO {
	
	@NotBlank(message = "필수입니다")
	private String boardTitle;
	private String boardWriter;
	
	
	private Long boardHit;
	
	private List<BoardFileDTO> fileDTOs;

}
