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
	public String login(HttpSession session)throws Exception{
		Object obj = session.getAttribute("SPRING_SECURITY_CONTEXT");
		
		if(obj!=null) {
			return "redirect:/";
		}
		return "users/login";
	}	
	
	@GetMapping("update")
	public void update(@AuthenticationPrincipal UserDTO userDTO, Model model)throws Exception{
		UserDTO dto = userService.detail(userDTO);
		model.addAttribute("usersDTO", dto);
	}

	/*
	// 기존 코드
	@PostMapping("update")
	public String update(@Validated(UpdateGroup.class) @ModelAttribute("usersDTO") UserDTO userDTO, BindingResult bindingResult, MultipartFile profile)throws Exception{
		if(bindingResult.hasErrors()) {
			return "users/update";
		}
		
		int result = userService.update(userDTO, profile);
		
		return "redirect:./mypage";
	}
	*/
	
	// ============== 수정된 코드 (2차) ==============
	@PostMapping("update")
	public String update(@Validated(UpdateGroup.class) UserDTO userDTO, 
	BindingResult bindingResult, @AuthenticationPrincipal UserDTO principal, MultipartFile profile, Model model) throws Exception {
	    if (bindingResult.hasErrors()) {
	        // 유효성 검사 실패 시, JSP 렌더링에 필요한 DTO를 다시 조회하여 모델에 직접 추가.
	        UserDTO freshData = userService.detail(principal);
	        model.addAttribute("usersDTO", freshData);
	        return "users/update";
	    }

	    // 현재 로그인된 사용자의 username을 안전하게 설정
	    userDTO.setUsername(principal.getUsername());
	    int result = userService.update(userDTO, profile);

	    return "redirect:./mypage";
	}
	// =======================================
	
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
