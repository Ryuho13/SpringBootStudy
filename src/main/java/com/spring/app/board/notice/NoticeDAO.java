package com.spring.app.board.notice;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoticeDAO {

	public NoticeDTO detail(NoticeDTO noticeDTO)throws Exception;
	
	public int delete(NoticeDTO noticeDTO)throws Exception;
	
	public  List<NoticeDTO> list()throws Exception;
	
	public int add(NoticeDTO noticeDTO)throws Exception;
	
	
}
