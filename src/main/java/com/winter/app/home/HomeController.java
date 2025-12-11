package com.winter.app.home;

import java.security.Principal;
import java.util.Enumeration;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.winter.app.users.UserDTO;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {
	// Spring boot 3.2 이상 버전 부터가능
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@GetMapping("/logout") // /logout 요청 시 홈페이지로 리다이렉트
	public String handleLogoutRedirect() {
		return "redirect:/";
	}
	
	public String index5(@AuthenticationPrincipal UserDTO dto)throws Exception{
		System.out.println(dto.getUsername());
		
		return "index";
	}
	
	// 보류 에러 발생
	public String index4(Principal principal) throws Exception{
		UserDTO userDTO = (UserDTO)principal;
		System.out.println(userDTO.getUsername());
		
		return "index";
	}
	
	
	public String index3 (Authentication authentication)throws Exception{
		UserDTO userDTO = (UserDTO)authentication.getPrincipal();
		System.out.println(userDTO.getUsername());
		System.out.println(authentication.getName());
		
		return "index";
	}
	
	
	public String index2()throws Exception{
		Object obj = SecurityContextHolder.getContext().getAuthentication();
		
		Authentication authentication = (Authentication)obj;
		
		UserDTO userDTO = (UserDTO)authentication.getPrincipal();
		
		System.out.println(userDTO.getUsername());
		System.out.println(authentication.getName());
		
		return "index";
	}
	
	
	public String index(HttpSession session)throws Exception{
//		Enumeration<String> en = session.getAttributeNames();
//
//		while (en.hasMoreElements()) {
//			String k = en.nextElement();
//			System.out.println(k);
//		}
		//System.out.println(obj.getClass());
		SecurityContextImpl obj = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
		Authentication authentication = obj.getAuthentication();
		log.info("{}",authentication);
		UserDTO userDTO = (UserDTO) authentication.getPrincipal();
		System.out.println(userDTO.getUsername());
		System.out.println(authentication.getName());
		
		return "index";
	}
}
