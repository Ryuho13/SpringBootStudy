package com.winter.app.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

// ... other imports

@Controller
@RequestMapping("/users/**")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Value("${category.user}")
	private String category;

	@ModelAttribute("category")
	private String getCategory() {
		return this.category;
	}
	
	@GetMapping("register")
	public void register(@ModelAttribute("userDTO") UserDTO userDTO)throws Exception{}	
	
	
	@PostMapping("register")
	public String register(@Validated(RegisterGroup.class) UserDTO userDTO,BindingResult bindingResult ,MultipartFile attach)throws Exception{
		if(userService.getError(userDTO, bindingResult)) {
			return "users/register";
		}
		
		int result = userService.register(userDTO, attach);
		
		return "redirect:/";
	}
	
	@GetMapping("mypage")
	public String mypage(@AuthenticationPrincipal UserDTO userDTO, Model model) throws Exception {
	    UserDTO fullUserDTO = userService.detail(userDTO);
	    model.addAttribute("dto", fullUserDTO);
	    return "users/mypage";
	}
	
	@GetMapping("login")
	public void login()throws Exception{}	
	
	@GetMapping("update")
	public void update(HttpSession session,Model model)throws Exception{
		
		model.addAttribute("userDTO", session.getAttribute("user"));
	}
//...
	
	@PostMapping("update")
	public String update(@Validated(UpdateGroup.class) UserDTO userDTO, BindingResult bindingResult,HttpSession session)throws Exception{
		if(bindingResult.hasErrors()) {
			return "users/update";
		}
		
		UserDTO loginDTO = (UserDTO)session.getAttribute("user");
		userDTO.setUsername(loginDTO.getUsername());
		int result = userService.update(userDTO);
		
		if(result>0) {
			loginDTO=userService.detail(loginDTO);
			session.setAttribute("user", loginDTO);
		}
		
		return "redirect:/";
	}
	
	@GetMapping("change")
	public void change(UserDTO userDTO)throws Exception{
		
	}
	
	@PostMapping("change")
	public String change(@Validated(PasswordGroup.class) UserDTO userDTO,BindingResult bindingResult  ,  String exist)throws Exception{
		
		if(userService.getError(userDTO, bindingResult)) {
			return "users/change";
		}
		
		return "redirect:mypage";
	}
	@GetMapping("/test")
	public String test(UserDAO userDAO) throws Exception {
	    UserDTO dto = new UserDTO();
	    dto.setUsername("testuser");
	    System.out.println(userDAO.detail(dto)); // null 또는 DTO출력
	    return "index";
	}


	
}
