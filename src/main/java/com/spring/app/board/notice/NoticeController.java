package com.spring.app.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.app.board.BoardDTO;
import com.spring.app.util.Pager;

import lombok.extern.slf4j.Slf4j;



@Controller
@RequestMapping("/notice/")
@Slf4j
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@GetMapping("list")
	public void list(Pager pager, Model model)throws Exception{
		List<BoardDTO> list = noticeService.list(pager);
		model.addAttribute("list", list);
		model.addAttribute("pager", pager);
	}
	@GetMapping("detail")
	public String detail(NoticeDTO noticeDTO, Model model)throws Exception{
		BoardDTO boardDTO = noticeService.detail(noticeDTO);
		model.addAttribute("dto", boardDTO);
		
		return "notice/detail";
	}
	@GetMapping("add")
	public String add()throws Exception{
		return "notice/add";
	}
	
	@PostMapping("add")
	public String add(NoticeDTO noticeDTO)throws Exception{
		int result = noticeService.add(noticeDTO);
		return "redirect:./list";
	}
	
	
}
