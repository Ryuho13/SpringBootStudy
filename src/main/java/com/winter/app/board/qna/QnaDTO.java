package com.winter.app.board.qna;

import com.winter.app.board.BoardDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class QnaDTO extends BoardDTO {
	
	private Long boardRef;
	private Long boardStep;
	private long boardDepth;
	
	public Long getBoardRef() {
		if(this.boardRef==null) {
			this.boardRef=0L;
		}
		return this.boardRef;
	}

}
