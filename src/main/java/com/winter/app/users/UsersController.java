package com.winter.app.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j; // Import Slf4j

@Controller
@RequestMapping("/users")
@Slf4j // Add Slf4j annotation
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/register")
    public String register(Model model)throws Exception {
        model.addAttribute("usersDTO", new UsersDTO()); // usersDTO 로 변경
        return "users/register";
    }


    @PostMapping("/register")
    public String register(
            @Valid @ModelAttribute("usersDTO") UsersDTO usersDTO,
            BindingResult bindingResult,
            @RequestParam("profile") MultipartFile profile) throws Exception {

        if(bindingResult.hasErrors()) {
            return "users/register";
        }

        return "redirect:/";
    }

    
    @GetMapping("/mypage")
    public String mypage(HttpSession session, Model model)throws Exception{
    	// 세션에서 사용자 정보 꺼내기
    	UsersDTO sessionUser = (UsersDTO) session.getAttribute("user");
    	
    	// 로그인이 안되어 있으면 로그인 페이지로 리다이렉트
    	if(sessionUser == null) {
    		return "redirect:/users/login";
    	}
    	
    	// DB에서 최신 정보 다시 조회
    	UsersDTO usersDTO = usersService.mypage(sessionUser);
    	model.addAttribute("dto", usersDTO);
    	
    	return "users/mypage";
    }

    @GetMapping("login")
    public void login ()throws Exception{}

    @PostMapping("login")
    public String login (UsersDTO usersDTO, HttpSession session, Model model)throws Exception{
    	UsersDTO resultDTO = usersService.login(usersDTO);
    	
    	if(resultDTO != null) {
    		// 로그인 성공
    		session.setAttribute("user", resultDTO);
    		return "redirect:/";
    	} else {
    		// 로그인 실패
    		model.addAttribute("errorMessage", "로그인 실패. 아이디와 비밀번호를 확인하세요.");
    		return "users/login";
    	}
    }
    
    @GetMapping("logout")
    public String logout(HttpSession session) throws Exception {
    	session.invalidate();
    	return "redirect:/";
    } 
    
    
}
