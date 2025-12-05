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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

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
	public String register(Model model)throws Exception{
		model.addAttribute("userDTO", new UserDTO());
		return "users/register";
	}
	
	
	@PostMapping("register")
	public String register(@Validated(RegisterGroup.class) UserDTO userDTO,BindingResult bindingResult ,MultipartFile attach)throws Exception{
		if(userService.getError(userDTO, bindingResult)) {
			return "users/register";
		}
		
		int result = userService.register(userDTO, attach);
		
		return "redirect:/";
	}
	@GetMapping("mypage")
	public void detail()throws Exception{
		
	}
	@GetMapping("login")
	public String login(Model model)throws Exception{
		model.addAttribute("userDTO", new UserDTO());
		return "users/login";
	}	
	
	@PostMapping("login")
	public String login(UserDTO userDTO, HttpSession session, Model model)throws Exception{
		
		UserDTO loginDTO = userService.detail(userDTO);
		
		if(loginDTO == null) {
			model.addAttribute("errorMessage", "로그인 실패");
			return "users/login";
		}
		
		session.setAttribute("user", loginDTO);
		
		return "redirect:/";
	}
	
	@GetMapping("update")
	public void update(HttpSession session,Model model)throws Exception{
		UserDTO userDTO = (UserDTO)session.getAttribute("user");
		model.addAttribute("userDTO", userDTO);
	}
	
	@PostMapping("update")
	public String update(@Validated(UpdateGroup.class) UserDTO userDTO, BindingResult bindingResult,HttpSession session)throws Exception{
		if(bindingResult.hasErrors()) {
			return "users/update";
		}
		
		UserDTO loginDTO = (UserDTO)session.getAttribute("user");
		userDTO.setUsername(loginDTO.getUsername());
		int result = userService.update(userDTO);
		
		if(result>0) {
			loginDTO = userService.mypage(loginDTO);
			session.setAttribute("user", loginDTO);
		}
		
		return "redirect:./mypage";
	}
	
	@GetMapping("logout")
	public String logout(HttpSession session)throws Exception{
		session.invalidate();
		return "redirect:/";
	}
	
	
	@GetMapping("change")
	public String change(Model model)throws Exception{
		model.addAttribute("userDTO", new UserDTO());
		return "users/change";
	}
	
	@PostMapping("change")
	public String change(@Validated(PasswordGroup.class) UserDTO userDTO,BindingResult bindingResult, HttpSession session, RedirectAttributes redirectAttributes)throws Exception{
		
		if(bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			return "redirect:./change";
		}
		
		// 현재 로그인된 사용자 정보
		UserDTO loginUser = (UserDTO) session.getAttribute("user");
		
		// 현재 비밀번호가 맞는지 확인
		if (!userService.checkPassword(loginUser.getUsername(), userDTO.getOldPassword())) {
			redirectAttributes.addFlashAttribute("errorMessage", "기존 비밀번호가 일치하지 않습니다.");
			return "redirect:./change";
		}
		
		// 새 비밀번호와 확인 비밀번호가 일치하는지 확인
		if (!userDTO.getPassword().equals(userDTO.getPasswordCheck())) {
			redirectAttributes.addFlashAttribute("errorMessage", "새 비밀번호가 일치하지 않습니다.");
			return "redirect:./change";
		}
		
		// 비밀번호 변경
		loginUser.setPassword(userDTO.getPassword());
		int result = userService.changePassword(loginUser);
		
		if(result > 0) {
			redirectAttributes.addFlashAttribute("successMessage", "비밀번호가 성공적으로 변경되었습니다.");
			session.invalidate(); // 재로그인 유도
			return "redirect:/users/login";
		} else {
			redirectAttributes.addFlashAttribute("errorMessage", "비밀번호 변경에 실패했습니다.");
			return "redirect:./change";
		}

	}
}