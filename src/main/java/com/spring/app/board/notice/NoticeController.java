package com.spring.app.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		//1. totalCount 구하기
		List<NoticeDTO> list = noticeService.list(pager);
		model.addAttribute("list", list);
		model.addAttribute("pager", pager);
	}
	@GetMapping
	public String detail(NoticeDTO noticeDTO, Model model)throws Exception{
		noticeDTO = noticeService.detail(noticeDTO);
		
		return "notice/detail";
	}
	
	
	
}
