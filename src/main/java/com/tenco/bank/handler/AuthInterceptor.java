package com.tenco.bank.handler;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.tenco.bank.handler.exception.UnAuthorizedException;
import com.tenco.bank.repository.entity.User;
import com.tenco.bank.utils.Define;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

// 1. HandlerInterceptor 구현하기
// AuthInterceptor --> new -->
// 하나만 IoC 대상으로 만들때는 @Component
// 메서드를 Bean 객체로 만들때는 @Configuration
@Component //IoC 대상
public class AuthInterceptor implements HandlerInterceptor{

	// preHandler -> request객체, response객체가 만들어지고
	// Controller 들어오기 전에 동작
	// true 반환시 Controller로 들어감
	// false 반환시 Controller로 안들어감
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("인터셉터 동작 시작");
		// 인증 검사
		HttpSession session = request.getSession();
		User principal = (User) session.getAttribute(Define.PRINCIPAL);
		if(principal == null) {
			// response.sendRedirect("/user/sign-in");
			// 예외 처리
			throw new UnAuthorizedException(Define.ENTER_YOUR_LOGIN, HttpStatus.UNAUTHORIZED);
		}
		
		return true;
	}
	
	// postHandle
	// 뷰가 렌더링 되기 전에 호출되는 메서드
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	
	// afterCompletion
	// 요청 처리가 완료 된 후 or 뷰 렌더링이 완료된 후 호출되는 메서드
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
}
