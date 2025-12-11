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
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import reactor.core.publisher.Mono;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

// ... other imports

@Controller
@RequestMapping("/users/**")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Value("${category.user}")
	private String category;
	
	@Value("${spring.security.oauth2.client.registration.kakao.client-secret}") // application.properties 파일에 kakao.admin-key=YOUR_KAKAO_ADMIN_KEY 추가 필요
	private String adminKey;

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
	@GetMapping("delete")
	public String deleteGet() throws Exception {
		return "users/delete"; // 탈퇴 확인 페이지로 이동
	}
	
	@PostMapping("delete")
	public String deletePost(@AuthenticationPrincipal UserDTO userDTO, HttpSession session) throws Exception {
		// 1. 소셜 로그인 사용자인 경우 카카오 연결 끊기 (unlink) API 호출
		// userDTO.getAttributes()가 null이 아니면 소셜 로그인 사용자
		if (userDTO.getAttributes() != null) {
			WebClient webClient = WebClient.create();
			
			// 카카오 연결 끊기 API (v1/user/unlink)
			try {
				String kakaoUnlinkResponse = webClient.post()
						 .uri("https://kapi.kakao.com/v1/user/unlink")
						 .header("Authorization","KakaoAK " + adminKey)
						 .header("Content-Type", "Content-Type: application/x-www-form-urlencoded;charset=utf-8")
						 .body(BodyInserters.fromFormData("target_id_type", "user_id").with("target_id", userDTO.getUsername())) // user_id 사용
						 .retrieve()
						 .bodyToMono(String.class)
						 .block(); // 동기적으로 처리
				System.out.println("Kakao Unlink Response: " + kakaoUnlinkResponse);
			} catch (Exception e) {
				System.err.println("Kakao unlink API call failed: " + e.getMessage());
				// 실패해도 로컬 DB 삭제는 진행 (카카오 연결만 끊기지 않은 것)
			}
		}
		
		// 2. DB에서 사용자 정보 삭제
		int result = userService.deleteUser(userDTO);
		
		// 3. 세션 무효화
		session.invalidate();
		
		// 4. 메인 페이지로 리다이렉트
		return "redirect:/";
	}

	
}
