package com.spring.security.SpringSecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.security.SpringSecurity.service.SpringService;

@RestController
public class SpringController {
	
	@Autowired
	SpringService springService;
	
	@GetMapping("/hello")
	public String sayHello()
	{
		return springService.sayHello();
	}

}
