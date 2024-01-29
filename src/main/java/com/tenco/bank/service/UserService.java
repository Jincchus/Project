package com.tenco.bank.service;

import org.springframework.http.HttpStatus;
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
	
	// @Autowired
	private UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	/**
	 * 회원 가입 로직 처리
	 * @param SignUpFormDto
	 * @return void
	 */
	@Transactional // 트랜잭션 처리 습관
	public void createdUser(SignUpFormDto dto) {
		
		User user = User.builder()
				.username(dto.getUsername())
				.password(dto.getPassword())
				.fullname(dto.getFullname())
				.build();
		
		int result = userRepository.insert(user);
		if(result != 1) {
			throw new CustomRestfulException("회원 가입 실패",
											HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * 로그인 처리
	 * @param SignInFormDto
	 * @return User
	 */
	public User readUser(SignInFormDto dto) {
				
		User user = User.builder()
				.username(dto.getUsername())
				.password(dto.getPassword())
				.build();
		
		User userEntity = userRepository.findByUsernameAndPassword(user);

		if(userEntity == null) {
			throw new UnAuthorizedException("인증된 사용자가 아닙니다.",
										HttpStatus.UNAUTHORIZED);
		}
		return userEntity;
	}
	
}
