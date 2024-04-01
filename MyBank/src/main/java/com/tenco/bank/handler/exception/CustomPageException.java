package com.tenco.bank.handler.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class CustomPageException extends RuntimeException{

	// 상태코드
	private HttpStatus httpStatus;
	
	public CustomPageException(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}
	
	// 사용 시점에 활용 가능
	// => new CustomPageException("나타낼 메세지", HttpStatus.ok);
}
