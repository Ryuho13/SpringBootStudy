package com.spring.app.board;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.notNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;


@SpringBootTest
@Slf4j
class BoardDAOTest {
	
	@Autowired
	private BoardDAO boardDAO;
	
	//@Test
	void testList()throws Exception{
		List<BoardDTO> ar = boardDAO.list();
		assertNotEquals(0, ar.size());
	}
	
	//@Test
	void testAdd() throws Exception {
	    BoardDTO baoBoardDTO = new BoardDTO();
	    baoBoardDTO.setTitle("12");
	    baoBoardDTO.setWriter("12");
	    baoBoardDTO.setContents("1123");

	    int result = boardDAO.add(baoBoardDTO);

	    assertEquals(1, result);
	}
	
	@Test
	void testUpdate()throws Exception{
		Map<String, Object> map = new HashMap<>();
		map.put("t", "update T");
		map.put("c", "update c");
		map.put("n", 25L);
		
		int result = boardDAO.update(map);
		assertEquals(1, result);
	}
	
	//@Test
	void testDelete()throws Exception{
		int result = boardDAO.delete(10L);
		assertEquals(1, result);
	}

	//@Test
	void testDetail()throws Exception{
		// int age=10;
		// double ki = 18.23;
		// log.error("age: {}, ki : {}", age, ki);
		
		BoardDTO baoBoardDTO = new BoardDTO();
		baoBoardDTO.setNum(2L);
		baoBoardDTO=boardDAO.detail(baoBoardDTO);
		// log.info(baoBoardDTO.toString());
		
		// assertNotNull(baoBoardDTO);
		//assertNull(baoBoardDTO);
	}

}
