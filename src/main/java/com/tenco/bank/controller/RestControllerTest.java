package com.tenco.bank.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.tenco.bank.dto.Todo;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController // data 내려줌
public class RestControllerTest {

	// 클라이언트에서 접근하는 주소 설계
	@GetMapping("/my-test1")
	public ResponseEntity<?> getMethodName() {
		
		// 여기서 다른 서버로 자원을 요청한 다음
		// 다시 클라이언트에게 자원을 내려주자
		
		// URI 객체 만들기
		URI uri =  UriComponentsBuilder
				.fromUriString("https://jsonplaceholder.typicode.com")
				.path("/posts")
				.encode()
				.build()
				.toUri();
		
		// 객체 생성
		RestTemplate restTemplate = new RestTemplate();
		
		// HTTP 통신 -> HTTP 메세지 헤더, 바디를 구성해서 보내야함
		
		// exchange 사용 방법 
		// 1. HttpHeaders 객체를 만들고 Header 메세지 구성 
		// 2. body 데이터를 key=value 구조로 만들기 
		// 3. HttpEntity 객체를 생성해서 Header 와 결합 후 요청 
		
		// 헤더 구성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/json; charset=UTF-8");
		
		// 바디 구성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("title", "블로그 포스트 1");
		params.add("body", "후미진 어느 언덕에서 도시락 소풍");
		params.add("userId", "1");
		
		// 헤더와 바디 결합
		HttpEntity<MultiValueMap<String, String>> requestMessage
			= new HttpEntity<>(params, headers);
		
		// HTTP 요청 처리
		ResponseEntity<String> response
			= restTemplate
				.exchange(uri, 
						HttpMethod.POST, 
						requestMessage, 
						String.class);
		
		// http://localhost:80/my-test1
		System.out.println("headers " + response.getHeaders());
		
		return ResponseEntity.status(HttpStatus.OK).body(response.getBody());
	}
	
	// http://localhost:80/todos/id값
	@GetMapping("/todos/{id}")
	public ResponseEntity<?> test2(@PathVariable Integer id) {
		
		// URI uri = new URI("https://jsonplaceholder.typicode.com/"+id);
		URI uri = UriComponentsBuilder
				.fromUriString("https://jsonplaceholder.typicode.com")
				.path("/todos")
				.path("/"+id)
				.encode()
				.build()
				.toUri();
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<Todo> response 
			= restTemplate.getForEntity(uri, Todo.class); // GET 방식 요청 응답은??
		System.out.println("Headers : " + response.getHeaders());
		System.out.println("Body : " + response.getBody());
		System.out.println("Title : " + response.getBody().getTitle());
		System.out.println("Id : " + response.getBody().getId());
		System.out.println("UserId : " + response.getBody().getUserId());
		return ResponseEntity.status(HttpStatus.OK).body(response.getBody());
	}
	

	
	
}
