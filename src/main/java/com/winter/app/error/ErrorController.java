package com.winter.app.error;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// 예외를 전역 처리하는 Controller
@ControllerAdvice
public class ErrorController {

	@ExceptionHandler(exception = NullPointerException.class)
	public String e1(Model model) {
		return "error/errorPage";
	}
	@ExceptionHandler(Exception.class)
	public String exc2(Model model) {
		
		return "error/errorPage";
	}
	@ExceptionHandler(Throwable.class)
	public String exc3(Model model) {
		
		return "error/errorPage";
	}
	
	
}
