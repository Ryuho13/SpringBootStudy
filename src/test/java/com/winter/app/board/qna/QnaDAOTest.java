package com.winter.app.board.qna;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QnaDAOTest {

	@Autowired
	private QnaDAO qnaDAO;
	
	@Test
	void testList() {
		fail("Not yet implemented");
	}

	@Test
	void testAdd() throws Exception{
		for (int i = 0; i < 100; i++) {
			QnaDTO qnaDTO = new QnaDTO();
			qnaDTO.setBoardWriter("writer" + i);
			qnaDTO.setBoardTitle("title" + i);
			qnaDTO.setBoardContents("contents" + i);
			qnaDAO.add(qnaDTO);
		}
		
	}

}
