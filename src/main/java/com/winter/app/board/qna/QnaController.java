package com.winter.app.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.winter.app.board.BoardDTO;
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
	
		model.addAttribute("list", list);
		model.addAttribute("pager", pager);
		
		return "board/list";
	}
	
	@GetMapping("add")
	public String add() throws Exception {
		return "board/add";
	}
	@PostMapping("add")
	public ModelAndView add(QnaDTO qnaDTO, RedirectAttributes redirectAttributes) throws Exception {
	    ModelAndView mv = new ModelAndView();
	    int result = qnaService.add(qnaDTO); 
	    
	    if(result > 0) {
	        redirectAttributes.addFlashAttribute("result", "질문 등록 성공"); 
	    } else {
	        redirectAttributes.addFlashAttribute("result", "질문 등록 실패");
	    }
	    mv.setViewName("redirect:./list"); 
	    
	    return mv;
	}
	
	@GetMapping("reply")
	public String reply(QnaDTO qnaDTO, Model model) throws Exception{
		model.addAttribute("dto", qnaDTO);
		
		return "board/add";
	} 
	@PostMapping("reply") 
	public String reply(QnaDTO qnaDTO) throws Exception {
	    qnaService.reply(qnaDTO);
	    return "redirect:./list";
	}
	
	@GetMapping("detail")
	public ModelAndView detail(BoardDTO boardDTO) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		boardDTO = qnaService.detail(boardDTO);
		
		mv.addObject("dto", boardDTO);
		mv.setViewName("board/detail");
		
		return mv;	
	}
	
	@GetMapping("update") 
	public ModelAndView update(BoardDTO boardDTO) throws Exception {
	    ModelAndView mv = new ModelAndView();
	    
	    boardDTO = qnaService.detail(boardDTO); 
	    
	    mv.addObject("dto", boardDTO);
	    mv.setViewName("board/update"); 
	    
	    return mv;
	}
	@PostMapping("update")
	public ModelAndView update(QnaDTO qnaDTO, RedirectAttributes redirectAttributes) throws Exception {
	    ModelAndView mv = new ModelAndView();
	    
	    int result = qnaService.update(qnaDTO); 
	    
	    if (result > 0) {
	        redirectAttributes.addFlashAttribute("result", "게시물이 성공적으로 수정되었습니다.");
	    } else {
	        redirectAttributes.addFlashAttribute("result", "게시물 수정에 실패했습니다.");
	    }
	    
	    mv.setViewName("redirect:./detail?boardNum=" + qnaDTO.getBoardNum());
	    
	    return mv;
	}
	
	@PostMapping("delete")
	public ModelAndView delete(QnaDTO qnaDTO, RedirectAttributes redirectAttributes) throws Exception {
	    ModelAndView mv = new ModelAndView();
	    
	    int result = qnaService.delete(qnaDTO); 
	    
	    if (result > 0) {
	        redirectAttributes.addFlashAttribute("result", "게시물이 성공적으로 삭제되었습니다.");
	    } else {
	        redirectAttributes.addFlashAttribute("result", "게시물 삭제에 실패했습니다.");
	    }
	    
	    mv.setViewName("redirect:./list");
	    
	    return mv;
	}

}