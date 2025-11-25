package com.spring.app.board.notice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NoticeDAOTest {

	@Autowired
	private NoticeDAO noticeDAO;
	
	//@Test
	void testDetail() throws Exception {
		NoticeDTO noticeDTO = new NoticeDTO();
		noticeDTO.setBoardNum(1L);
		noticeDTO=noticeDAO.detail(noticeDTO);
		assertNotNull(noticeDTO);
	}
	// @Test
	void testDelete() throws Exception {
	    NoticeDTO noticeDTO = new NoticeDTO();
	    noticeDTO.setBoardNum(2L);

	    int result = noticeDAO.delete(noticeDTO);

	    assertEquals(1, result);
	}
	//@Test
	void testList()throws Exception{
		List<NoticeDTO> ar = noticeDAO.list();
		assertNotEquals(0, ar.size());
	}
	
	
	
	
}
