package com.tenco.bank.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SignUpFormDto {
	
	private String username;
	private String password; 
	private String fullname; 
	private MultipartFile customFile; // name 속성과 일치 시켜야 함 
	private String originFileName; 
	private String uploadFileName; 
	private String eMail;
	
}
