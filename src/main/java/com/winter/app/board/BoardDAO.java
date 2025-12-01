package com.winter.app.board;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.winter.app.board.notice.NoticeDTO;
import com.winter.app.board.qna.QnaDTO;
import com.winter.app.util.Pager;

@Mapper
public interface BoardDAO {
	
	public BoardDTO detail(BoardDTO boardDTO) throws Exception;
	
	public List<BoardDTO> list(Pager pager) throws Exception;
	
	public Long getTotalCount(Pager pager) throws Exception;
	
	public Long count(Pager pager) throws Exception;
	
	public int add(BoardDTO boardDTO) throws Exception;
	
	public int update(BoardDTO boardDTO) throws Exception;
	
	public int delete(BoardDTO boardDTO) throws Exception;

}
