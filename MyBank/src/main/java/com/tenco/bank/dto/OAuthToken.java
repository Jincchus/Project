package com.tenco.bank.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

// json 형식의 코딩 컨벤션이 스네이크 케이스 일 때 카멜 노테이션으로 변경
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
public class OAuthToken {

	private String accessToken;
	private String tokenType;
	private String refreshToken;
	private String expiresIn;
	private String scope;
	private String refreshTokenExpiresIn;
}
