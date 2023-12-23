package com.example.application.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
class RestControllerApi {
	
	@GetMapping("/api")
	@ResponseBody()
	public String printHello() {
		return "Hello World!";
	}
}
