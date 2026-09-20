package com.spring.SpringSecurity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.SpringSecurity.entity.User;
import com.spring.SpringSecurity.models.UserRegisterRequest;
import com.spring.SpringSecurity.models.UserRegisterResponse;
import com.spring.SpringSecurity.models.UserResponse;
import com.spring.SpringSecurity.service.AuthService;
import com.spring.SpringSecurity.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	AuthService authService;
	
	@GetMapping("/get/all/user")
	public ResponseEntity<List<UserResponse>> getAllUsers()
	{
		return userService.getAllUsers();
	}
	
	@GetMapping("/get/user")
	public ResponseEntity<UserResponse> getUser(@RequestParam String username) {
		
		return userService.getUser(username);
		
	}
	
	@PostMapping("/register")
	public ResponseEntity<UserRegisterResponse> registerUser(@RequestBody UserRegisterRequest userRegisterRequest) {
		
		return authService.registerUser(userRegisterRequest);
	}

}
