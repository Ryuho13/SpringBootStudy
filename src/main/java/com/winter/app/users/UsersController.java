package com.winter.app.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/register")
    public String register() {
        return "users/register";
    }

    @PostMapping("/register")
    public String register(UsersDTO usersDTO, @RequestParam("profile") MultipartFile profile) throws Exception {
        usersService.register(usersDTO, profile);
        return "redirect:/";
    }
    @GetMapping("/mypage")
    public String mypage(@RequestParam String username, Model model)throws Exception{
    	UsersDTO usersDTO = usersService.mypage(username);
    	model.addAttribute("dto", usersDTO);
    	return "users/mypage";
    }
}
