package com.spring.app.board;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardDAO {
	
	// 목록
	public List<BoardDTO> list()throws Exception;
	
	// 상세정보
	public BoardDTO detail(BoardDTO boardDTO)throws Exception;
	
	// 글 추가 
	public int add(BoardDTO boardDTO)throws Exception;
	
	// 글 삭제
	public int delete(Long num)throws Exception;

	// 글 수정
	public int update(Map<String, Object> map)throws Exception;
}
