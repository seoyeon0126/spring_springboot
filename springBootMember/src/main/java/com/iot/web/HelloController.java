package com.iot.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@RequestMapping("/h")
	public String hello() {
		return "안녕하쇼!!!!!";
	}
	
	@GetMapping("hi")
	public String hello(String name) {
		return "Hello "+name;
	}
}
