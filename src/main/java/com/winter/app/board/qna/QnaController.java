package com.winter.app.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.board.BoardDTO;
import com.winter.app.board.BoardFileDTO;
import com.winter.app.board.notice.NoticeDTO;
import com.winter.app.util.Pager;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/qna/*")
@Slf4j
public class QnaController {
	
	@Autowired
	private QnaService qnaService;

	@Value("${category.board.qna}")
	private String category;
	
	@ModelAttribute("category")
	public String getCategory() {
		return this.category;
	}
	
	@GetMapping("list")
	public String list(Pager pager, Model model)throws Exception{
		

		List<BoardDTO> list= qnaService.list(pager);
	
		if (list.size()>0) {
			throw new NullPointerException();
		}
		
		model.addAttribute("list", list);
		model.addAttribute("pager", pager);
		
		return "board/list";
	}
	
	@GetMapping("detail")
	public String detail(QnaDTO qnaDTO, Model model)throws Exception{
		qnaDTO =(QnaDTO) qnaService.detail(qnaDTO);
		
		model.addAttribute("dto", qnaDTO);
		
		return "board/detail";
	}
	

	
	@GetMapping("add")
	public String add()throws Exception{
		return "board/add";
	}
	
	@PostMapping("add")
	public String add(QnaDTO qnaDTO, MultipartFile [] attach)throws Exception{
		qnaDTO.setBoardRef(0L);
		qnaDTO.setBoardDepth(0L);
		qnaDTO.setBoardStep(0L);
		int result = qnaService.add(qnaDTO, attach);
		
		return "redirect:./list";
		
	}
	
	@GetMapping("update")
	public String update(QnaDTO qnaDTO, Model model)throws Exception{
		qnaDTO = (QnaDTO)qnaService.detail(qnaDTO);
		model.addAttribute("dto", qnaDTO);
		model.addAttribute("sub", "Update");
		return "board/add";
	}
	
	@PostMapping("update")
	public String update(QnaDTO qnaDTO)throws Exception{
		int result= qnaService.update(qnaDTO);
		
		return "redirect:./detail?boardNum="+qnaDTO.getBoardNum();
		
	}
	
	@PostMapping("delete")
	public String delete(QnaDTO qnaDTO)throws Exception{
		int result = qnaService.delete(qnaDTO);
		
		return "redirect:./list";
	}
	
	
	@GetMapping("reply")
	public String reply(QnaDTO qnaDTO, Model model)throws Exception{
		model.addAttribute("dto", qnaDTO);
		
		return "board/add";
	}
	
	@PostMapping("reply")
	public String reply(QnaDTO qnaDTO)throws Exception{
		int result = qnaService.reply(qnaDTO);
		
		return "redirect:./list";
	}
	
	@GetMapping("fileDown")
	public void fileDown(BoardFileDTO boardFileDTO)throws Exception{
		boardFileDTO = qnaService.fileDetail(boardFileDTO);
	}	
	
	// 예외 처리 메서드
	@ExceptionHandler(NullPointerException.class)
	public String exc1(Model model) {
		
		return "error/errorPage";
	}
	@ExceptionHandler(Exception.class)
	public String exc2(Model model) {
		
		return "error/errorPage";
	}
	@ExceptionHandler(Throwable.class)
	public String exc3(Model model) {
		
		return "error/errorPage";
	}

}







