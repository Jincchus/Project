package com.tenco.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenco.bank.dto.SignInFormDto;
import com.tenco.bank.dto.SignUpFormDto;
import com.tenco.bank.handler.exception.CustomRestfulException;
import com.tenco.bank.handler.exception.UnAuthorizedException;
import com.tenco.bank.repository.entity.User;
import com.tenco.bank.repository.interfaces.UserRepository;

@Service //IoC 대상
public class UserService {

	// 생성자 의존 주입(DI)
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * 회원 가입 로직 처리
	 * @param SignUpFormDto
	 * @return void
	 */
	@Transactional // 트랜잭션 처리 습관
	public void createdUser(SignUpFormDto dto) {
		
		// 추가 개념 : 암호화 처리
		
		System.out.println("userService createdUser Start");
		
		User user = User.builder()
				.username(dto.getUsername())
				.password(passwordEncoder.encode(dto.getPassword()))
				.fullname(dto.getFullname())
				.originFileName(dto.getOriginFileName())
				.uploadFileName(dto.getUploadFileName())
				.eMail(dto.getEMail())
				.build();

		int result = 0;
		try {
			result = userRepository.insert(user);
		} catch (Exception e) {
			if(result != 1) {
				throw new CustomRestfulException("회원 가입 실패",
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		System.out.println("userService createdUser End");
	}
	
	/**
	 * 로그인 처리
	 * @param SignInFormDto
	 * @return User
	 */
	public User readUser(SignInFormDto dto) {
				
		// 사용자의 username 받아서 정보를 추출
		User userEntity = userRepository.findByUsername(dto.getUsername());
		if(userEntity == null) {
			throw new CustomRestfulException("존재하지 않는 계정 입니다.",
										HttpStatus.BAD_REQUEST);
		}
		
		boolean isPwdMatched = 
				passwordEncoder.matches(dto.getPassword(),
										userEntity.getPassword());
		if(isPwdMatched == false) {
			throw new CustomRestfulException("비밀번호가 잘못되었습니다.",
										HttpStatus.BAD_REQUEST);
		}
		return userEntity;
	}
	
	// 사용자 이름만으로 정보 조회
	public User readUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	// 이메일 중복 체크
	public int findByEmailCheck(String eMail) {

		System.out.println("userService->findByEmailCheck() start");
		int result = this.userRepository.findByEmailCheck(eMail);
		return result;
	}
	
}
