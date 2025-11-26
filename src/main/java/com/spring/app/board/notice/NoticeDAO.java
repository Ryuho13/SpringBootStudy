package com.spring.app.board.notice;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.spring.app.util.Pager;

@Mapper
public interface NoticeDAO {

	public NoticeDTO detail(NoticeDTO noticeDTO)throws Exception;
	
	public int delete(NoticeDTO noticeDTO)throws Exception;
	
	public  List<NoticeDTO> list(Pager pager)throws Exception;
	
	public int add(NoticeDTO noticeDTO)throws Exception;
	
	public Long count()throws Exception;
}
