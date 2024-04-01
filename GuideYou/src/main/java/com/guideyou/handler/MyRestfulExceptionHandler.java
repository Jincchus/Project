package com.guideyou.handler;

import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.guideyou.handler.exception.CustomRestfulException;


@Order(1)
@RestControllerAdvice
public class MyRestfulExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public void exception(Exception e) {
		System.out.println("--------------");
		System.out.println(e.getClass().getName());
		System.out.println(e.getMessage());
		System.out.println("--------------");
	}
	
	@ExceptionHandler(CustomRestfulException.class)
	public String basicException(CustomRestfulException e) {
		StringBuffer sb = new StringBuffer();
		sb.append("<script>");
		sb.append("alert('" + e.getMessage() + "');");
		sb.append("window.history.back();");
		sb.append("</script>");
		return sb.toString();
	}
	
	
	
}
