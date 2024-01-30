package com.tenco.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tenco.bank.dto.SignInFormDto;
import com.tenco.bank.dto.SignUpFormDto;
import com.tenco.bank.handler.exception.CustomRestfulException;
import com.tenco.bank.repository.entity.User;
import com.tenco.bank.service.UserService;
import com.tenco.bank.utils.Define;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired // DI 처리
	private UserService userService;
	
	@Autowired
	private HttpSession httpSession;
	
	/**
	 * 회원 가입 페이지 요청
	 * @return signUp.jsp 파일 기턴 
	 */
	@GetMapping("/sign-up")
	public String signUpPage() {
		// prefix: /WEB-INF/view/
		// suffix: .jsp
		return "user/signUp";
	}
	
	/**
	 * 회원 가입 요청
	 * @param dto
	 * @return 로그인 페이지(/sign-in)
	 */
	@PostMapping("/sign-up")
	public String signProc(SignUpFormDto dto) {
		// 유효성 검사
		if(dto.getUsername() == null || dto.getUsername().isEmpty()) {
			throw new CustomRestfulException("username을 입력 하세요", 
												HttpStatus.BAD_REQUEST);
		}
		if(dto.getPassword() == null || dto.getPassword().isEmpty()) {
			throw new CustomRestfulException("password를 입력 하세요", 
												HttpStatus.BAD_REQUEST);
		}
		if(dto.getFullname() == null || dto.getFullname().isEmpty()) {
			throw new CustomRestfulException("fullname을 입력 하세요", 
												HttpStatus.BAD_REQUEST);
		}
		
		userService.createdUser(dto);
		
		return "redirect:/user/sign-in";
	}
	
	/**
	 * 로그인 페이지 요청
	 * @return 
	 */
	@GetMapping("/sign-in")
	public String signInPage() {
		return "/user/signIn";
	}
	
	/**
	 * 로그인 요청 처리
	 * @param SignInFormDto
	 * @return account/list.jsp
	 */
	@PostMapping("/sign-in")
	public String signInProc(SignInFormDto dto) {
		
		if(dto.getUsername() == null || dto.getUsername().isEmpty()) {
			throw new CustomRestfulException("username을 입력하시오", 
												HttpStatus.BAD_REQUEST);
		}
		if(dto.getPassword() == null || dto.getPassword().isEmpty()) {
			throw new CustomRestfulException("password를 입력하시오", 
												HttpStatus.BAD_REQUEST);
		}
		
		User user = userService.readUser(dto);
		httpSession.setAttribute(Define.PRINCIPAL, user);

		return "redirect:/account/list";
	}
	
	// 로그아웃 기능 만들기
	@GetMapping("/logout")
	public String logout() {
		httpSession.invalidate();
		return "redirect:/user/sign-in";
	}
	
	
}
