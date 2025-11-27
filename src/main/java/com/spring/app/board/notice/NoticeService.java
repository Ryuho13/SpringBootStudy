package com.spring.app.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.spring.app.util.Pager;

@Service
public class NoticeService {

	@Autowired
	private NoticeDAO noticeDAO;
	
	public List<NoticeDTO> list(Pager pager) throws Exception {
	    // 1. 전체 글의 갯수 DB에서 조회
	    Long totalCount = noticeDAO.count();

	    // 2. 조회된 totalCount를 Pager에 전달해서 페이지 계산하기
	    pager.pageing(totalCount);

	    // 3. 계산된 Pager 정보를 DAO로 전달해서 목록 가져오기
	    return noticeDAO.list(pager);
	}
	public NoticeDTO detail(NoticeDTO noticeDTO)throws Exception{
		return noticeDAO.detail(noticeDTO);
	}
	public int add(NoticeDTO noticeDTO)throws Exception{
		
		return noticeDAO.add(noticeDTO);
	}
	public int delete(NoticeDTO noticeDTO)throws Exception{
		return noticeDAO.delete(noticeDTO);
	}
	
	
	
}
