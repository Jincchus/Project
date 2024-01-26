package com.tenco.bank.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.tenco.bank.handler.exception.CustomPageException;


/**
 * View 렌더링을 위해 ModelView 객체를 반환 하도록 설정되어 있음
 * 예외처리 Page를 Return 할 때 사용
 */
@ControllerAdvice
public class MyPageExceptionHandler {
	
	// CustomPageException 발생 시 동작 시킬 함수
	@ExceptionHandler(CustomPageException.class)
	public ModelAndView handlerRuntimeException(CustomPageException e) {
		
		System.out.println("여기 에러 확인 ~~~~");
		
		ModelAndView modelAndView = new ModelAndView("errorPage");
		modelAndView.addObject("statusCode", HttpStatus.NOT_FOUND.value());
		modelAndView.addObject("message", e.getMessage());
		
		return modelAndView; // 페이지 반환 + 데이터 내려줌
	}
}
