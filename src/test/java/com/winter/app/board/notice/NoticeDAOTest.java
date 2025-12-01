package com.winter.app.board.notice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NoticeDAOTest {

	@Autowired
	private NoticeDAO noticeDAO;
	
	@Test
	void testDetail() throws Exception{
		NoticeDTO noticeDTO = new NoticeDTO();
		noticeDTO.setBoardNum(3L);
		
//		noticeDTO =noticeDAO.detail(noticeDTO);
		
		assertNotNull(noticeDTO);
	}
	
	
	@Test
	void testAdd() throws Exception{
		for(int i=0; i<120; i++) {
			NoticeDTO noticeDTO = new NoticeDTO();
			noticeDTO.setBoardWriter("writer" + i);
			noticeDTO.setBoardContents("contents" + i);
			noticeDTO.setBoardTitle("title" + i);
			noticeDAO.add(noticeDTO);			
			if(i%10==0) {
				Thread.sleep(500);
			}
		}
		
	}
//	
//	@Test
//	void testUpdate() throws Exception{
//		NoticeDTO noticeDTO = new NoticeDTO();
//		noticeDTO.setBoardNum(3L);
//		noticeDTO.setBoardContents("c1");
//		noticeDTO.setBoardTitle("t1");
//		int result = noticeDAO.update(noticeDTO);
//		assertEquals(1, result);
//		
//	}
//	
//	@Test
//	void testDelete() throws Exception{
//		NoticeDTO noticeDTO = new NoticeDTO();
//		noticeDTO.setBoardNum(12L);
//		int result = noticeDAO.delete(noticeDTO);
//		assertEquals(1, result);
//		
//	}
	

}
