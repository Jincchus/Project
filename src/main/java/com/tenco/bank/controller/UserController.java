package com.tenco.bank.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

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
		
		System.out.println("dto : " + dto.toString());
		System.out.println(dto.getCustomFile().getOriginalFilename());
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
		if(dto.getEMail() == null || dto.getEMail().isEmpty()) {
			throw new CustomRestfulException("e-Mail을 입력 하세요",
												HttpStatus.BAD_REQUEST);
		}
		
		// 파일 업로드
		MultipartFile file = dto.getCustomFile();
		if(file.isEmpty() == false) {
			// 사용자가 이미지를 업로드 했다면 기능 구현
			// 파일 사이즈 체크
			if(file.getSize() > Define.MAX_FILE_SIZE) {
				throw new CustomRestfulException("파일 크기는 20MB 이상 클 수 없습니다", HttpStatus.BAD_REQUEST);
			}
			
			// 서버 컴퓨터에 파일 넣을 디렉토리가 있는지 검사
			String saveDirectory = Define.UPLOAD_FILE_DERECTORY;
			// 폴더가 없다면 오류 발생(파일 생성시)
			File dir = new File(saveDirectory);
			if(dir.exists() == false) {
				dir.mkdir(); // 폴더가 없으면 폴더 생성
			}
			
			// 파일 이름 (중복 처리 예방)
			UUID uuid = UUID.randomUUID();
			String fileName = uuid + "_" + file.getOriginalFilename();
			System.out.println("file Name : " + fileName);
			
			String uploadPath 
			= Define.UPLOAD_FILE_DERECTORY + File.separator + fileName;
			
			File destination = new File(uploadPath);
			
			try {
				file.transferTo(destination);
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// 객체 상태 변경
			dto.setOriginFileName(file.getOriginalFilename());
			dto.setUploadFileName(fileName);
			
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
	
	@GetMapping("/find-pw")
	public String findPw() {
		return "/user/findPw";
	}
	
	
	
}
