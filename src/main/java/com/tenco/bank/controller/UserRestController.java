package com.tenco.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.tenco.bank.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class UserRestController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/emailCheck")
	public int emailCheck() {
		//TODO: process POST request
		
		return entity;
	}
	
}
