package com.tenco.bank.repository.entity;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class History {
	private Integer id; 
	private Long amount; 
	private Integer wAccountId; 
	private Integer dAccountId; 
	private Long wBalance; 
	private Long dBalance;
	private Timestamp createdAt;
	
}