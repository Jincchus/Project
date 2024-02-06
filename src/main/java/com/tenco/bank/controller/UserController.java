package com.tenco.bank.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.tenco.bank.dto.KakaoAccount;
import com.tenco.bank.dto.KakaoProfile;
import com.tenco.bank.dto.OAuthToken;
import com.tenco.bank.dto.SignInFormDto;
import com.tenco.bank.dto.SignUpFormDto;
import com.tenco.bank.handler.exception.CustomRestfulException;
import com.tenco.bank.repository.entity.User;
import com.tenco.bank.service.UserService;
import com.tenco.bank.utils.Define;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;


@Slf4j
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
	
	// http://localhost:80/user/kakao-callback?code="xxxxx"
	@GetMapping("/kakao-callback")
	public String kakaoCallback(@RequestParam String code) {
		
		// POST 방식 - Header구성, body구성
		RestTemplate rt1 = new RestTemplate();
		// 헤더 구성
		HttpHeaders headers1 = new HttpHeaders();
		headers1.add("Content-type", "application/x-www-form-urlencoded; charset=utf-8");
		// 바디 구성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "0842db6850200ca9991957bd6d138279");
		params.add("redirect_uri", "http://localhost/user/kakao-callback");
		params.add("code", code);
		
		// 헤더, 바디 결합
		HttpEntity<MultiValueMap<String, String>> reqMsg 
			= new HttpEntity<>(params, headers1);
		
		ResponseEntity<OAuthToken> response
			= rt1.exchange("https://kauth.kakao.com/oauth/token",
						HttpMethod.POST,
						reqMsg,
						OAuthToken.class);
		
		// 다시 요청 - 인증 토큰 -- 사용자 정보 요청
		
		RestTemplate rt2 = new RestTemplate();
		// 헤더
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer "+ response.getBody().getAccessToken());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		// 바디 x
		// 결합 --> 요청
		HttpEntity<MultiValueMap<String, String>> kakaoInfo
			= new HttpEntity<>(headers2);
		
		ResponseEntity<KakaoProfile> response2
			= rt2.exchange("https://kapi.kakao.com/v2/user/me",
					HttpMethod.POST,
					kakaoInfo,
					KakaoProfile.class);

		KakaoProfile kakaoProfile = response2.getBody();
		
		// 최초 사용자 판단 여부 -- username 존재 여부
		SignUpFormDto dto = SignUpFormDto.builder()
									.username("OAuth_" + kakaoProfile.getProperties().getNickname())
									.fullname("Kakao")
									.password("asd1234")
									.eMail("none") // 이메일 로직 다시생각(unique)
									.build();
		
		User oldUser = userService.readUserByUsername(dto.getUsername());
		// -> 현재 User는 null 상태
		
		if(oldUser == null) {
			userService.createdUser(dto);
			///////////////////////////////
			oldUser = new User(); // 객체 생성하지 않으면 아래 oldUser가 null!!
			oldUser.setUsername(dto.getUsername());
			oldUser.setFullname(dto.getFullname());
			oldUser.setEMail(dto.getEMail());
		}
		oldUser.setPassword(null);
		
		// 로그인 처리
		httpSession.setAttribute(Define.PRINCIPAL, oldUser);
		
		return "redirect:/account/list";
	}
	
	
	
}
