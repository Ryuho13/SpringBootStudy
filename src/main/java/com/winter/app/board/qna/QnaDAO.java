package com.winter.app.board.qna;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.winter.app.board.BoardDAO;
import com.winter.app.board.BoardDTO;
import com.winter.app.board.notice.NoticeDTO;
import com.winter.app.util.Pager;

@Mapper
public interface QnaDAO extends BoardDAO {
	
	public int refUpdate(BoardDTO boardDTO)throws Exception;
	
	public int stepUpdate(QnaDTO qnaDTO)throws Exception;

}
