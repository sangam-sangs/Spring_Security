package com.spring.SpringSecurity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.SpringSecurity.entity.User;
import com.spring.SpringSecurity.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/get/all")
	public List<User> getAllUsers()
	{
		return userService.getAllUsers();
	}
	
	@GetMapping("/get/{username}")
	public User getUser(@RequestParam String username) {
		
		return userService.getUser(username);
		
	}
	
	@PostMapping("/register")
	public User registerUser(@RequestBody User user) {
		
		return userService.registerUser(user);
	}

}
