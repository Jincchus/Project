package com.tenco.bank.repository.entity;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
	
	private Integer id;
	private String username;
	private String password;
	private String fullname;
	private Timestamp createdAt;
	
	private String originFileName;
	private String uploadFileName;
	
	private String eMail;
	
	// 사용자가 회원가입시, 이미지, 이미지 x
	public String setupUserImage() {
		return uploadFileName == null ? "/images/m-cut.png" : "/images/upload/" + uploadFileName;
	}
}
