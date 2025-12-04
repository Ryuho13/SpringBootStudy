package com.winter.app.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/users")
@Slf4j
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/register")
    public String register(Model model)throws Exception {
        model.addAttribute("usersDTO", new UsersDTO());
        return "users/register";
    }


    @PostMapping("/register")
    public String register(
            @Validated(RegisterGroup.class) @ModelAttribute("usersDTO") UsersDTO usersDTO,
            BindingResult bindingResult,
            @RequestParam("profile") MultipartFile profile) throws Exception {

        if(usersService.getError(usersDTO, bindingResult)) {
            return "users/register";
        }
        usersService.register(usersDTO, profile); // Un-commented this line
        return "redirect:/";
    }

    
    @GetMapping("/mypage")
    public String mypage(HttpSession session, Model model)throws Exception{
    	UsersDTO sessionUser = (UsersDTO) session.getAttribute("user");
    	
    	if(sessionUser == null) {
    		return "redirect:/users/login";
    	}
    	
        log.info("Session User in Mypage: {}", sessionUser);
    	model.addAttribute("dto", sessionUser);
    	
    	return "users/mypage";
    }

    @GetMapping("login")
    public void login ()throws Exception{}

    @PostMapping("login")
    public String login (UsersDTO usersDTO, HttpSession session, Model model)throws Exception{
    	UsersDTO resultDTO = usersService.login(usersDTO);
    	
    	if(resultDTO != null) {
    		session.setAttribute("user", resultDTO);
    		return "redirect:/";
    	} else {
    		model.addAttribute("errorMessage", "로그인 실패. 아이디와 비밀번호를 확인하세요.");
    		return "users/login";
    	}
    }
    
    @GetMapping("logout")
    public String logout(HttpSession session) throws Exception {
    	session.invalidate();
    	return "redirect:/";
    } 
    
    @GetMapping("update")
    public String update(@ModelAttribute("usersDTO") UsersDTO usersDTO, HttpSession session)throws Exception{
        UsersDTO sessionUser = (UsersDTO) session.getAttribute("user");
        if(sessionUser != null) {
            usersDTO.setUsername(sessionUser.getUsername());
            usersDTO.setName(sessionUser.getName());
            usersDTO.setEmail(sessionUser.getEmail());
            usersDTO.setPhone(sessionUser.getPhone());
            usersDTO.setBirth(sessionUser.getBirth());
            usersDTO.setFileDTOs(sessionUser.getFileDTOs());

        } else {
            return "redirect:/users/login";
        }
        return "users/update";
    }

    @PostMapping("update")
    public String update(@Validated(UpdateGroup.class) @ModelAttribute("usersDTO") UsersDTO usersDTO,
                         BindingResult bindingResult,
                         @RequestParam("profile") MultipartFile profile,
                         HttpSession session, Model model) throws Exception {
    	if (bindingResult.hasErrors()) {
            model.addAttribute("usersDTO", usersDTO);
			return "users/update";
		}
        
        usersService.update(usersDTO, profile);
        UsersDTO updatedUser = usersService.mypage(usersDTO);
        session.setAttribute("user", updatedUser);

		return "redirect:/users/mypage";
    }
    
    @GetMapping("passwordChange")
    public String passwordChange(@ModelAttribute("usersDTO") UsersDTO usersDTO, HttpSession session)throws Exception {
        UsersDTO sessionUser = (UsersDTO) session.getAttribute("user");
        if(sessionUser == null) {
            return "redirect:/users/login";
        }
        usersDTO.setUsername(sessionUser.getUsername());
        return "users/passwordChange";
    }

    @PostMapping("passwordChange")
    public String passwordChange(@Validated(PasswordGroup.class) @ModelAttribute("usersDTO") UsersDTO usersDTO,
                                 BindingResult bindingResult,
                                 HttpSession session, Model model)throws Exception{

        if(usersService.getError(usersDTO, bindingResult)) {
            return "users/passwordChange";
        }
        
        int res = usersService.updatePassword(usersDTO, bindingResult);

        if (res > 0) {
            return "redirect:/users/mypage";
        } else {
            model.addAttribute("errorMessage", "비밀번호 변경에 실패했습니다.");
            return "users/passwordChange";
        }
    }
}