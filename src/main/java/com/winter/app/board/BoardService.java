package com.winter.app.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.board.qna.QnaDAO;
import com.winter.app.board.notice.NoticeDAO;
import com.winter.app.board.notice.NoticeDTO;
import com.winter.app.board.qna.QnaDTO;
import com.winter.app.util.Pager;

public interface BoardService {
	
	public List<BoardDTO> list (Pager pager)throws Exception;
	
	public BoardDTO detail(BoardDTO boardDTO)throws Exception;
	
	public int update(BoardDTO boardDTO)throws Exception;
	
	public int delete(BoardDTO boardDTO)throws Exception;

	int add(BoardDTO boardDTO, MultipartFile[] attach) throws Exception;
	
	public BoardFileDTO fileDetail(BoardFileDTO boardFileDTO)throws Exception;
}
