package com.tenco.bank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
@RequestMapping("/layout")
public class MainContreller {
	@GetMapping({"/", "/main"})
	public String mainPage() {
		return "layout/main";
	}
	
}
