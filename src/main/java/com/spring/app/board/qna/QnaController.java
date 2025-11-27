package com.spring.app.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.app.util.Pager;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/qna/")
@Slf4j
public class QnaController {

    @Autowired
    private QnaService qnaService;

    @GetMapping("list")
    public String list(Pager pager, Model model) throws Exception {
        List<QnaDTO> list = qnaService.list(pager);
        model.addAttribute("list", list);
        model.addAttribute("pager", pager);
        return "qna/list";
    }

    @GetMapping("detail")
    public String detail(QnaDTO qnaDTO, Model model) throws Exception {
        qnaDTO = qnaService.detail(qnaDTO);
        model.addAttribute("dto", qnaDTO);
        return "qna/detail";
    }

    @GetMapping("add")
    public String add() throws Exception {
        return "qna/add";
    }

    @PostMapping("add")
    public String add(QnaDTO qnaDTO) throws Exception {
        qnaService.add(qnaDTO);
        return "redirect:./list";
    }

    @GetMapping("reply")
    public String reply(QnaDTO qnaDTO, Model model) throws Exception {
        model.addAttribute("dto", qnaDTO);
        return "qna/reply";
    }
    
    @PostMapping("reply")
    public String reply(QnaDTO qnaDTO, String test) throws Exception {
        qnaService.reply(qnaDTO);
        return "redirect:./list";
    }

    @GetMapping("update")
    public String update(QnaDTO qnaDTO, Model model) throws Exception {
        qnaDTO = qnaService.detail(qnaDTO);
        model.addAttribute("dto", qnaDTO);
        return "qna/update";
    }

    @PostMapping("update")
    public String update(QnaDTO qnaDTO) throws Exception {
        qnaService.update(qnaDTO);
        return "redirect:./detail?boardNum=" + qnaDTO.getBoardNum();
    }

    @GetMapping("delete")
    public String delete(QnaDTO qnaDTO) throws Exception {
        qnaService.delete(qnaDTO);
        return "redirect:./list";
    }
}
