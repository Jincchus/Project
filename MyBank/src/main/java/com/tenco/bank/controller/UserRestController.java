package com.tenco.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenco.bank.service.UserService;


@Controller
public class UserRestController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/email-check")
	@ResponseBody
	public int emailCheck(String eMail){
		System.out.println("userRestController->emailCheck() start");
		int result = this.userService.findByEmailCheck(eMail);
		return result;
	}
	
}
