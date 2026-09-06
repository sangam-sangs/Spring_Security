package com.spring.SpringSecurity.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.SpringSecurity.entity.Role;
import com.spring.SpringSecurity.entity.User;
import com.spring.SpringSecurity.models.UserRegisterRequest;
import com.spring.SpringSecurity.models.UserRegisterResponse;
import com.spring.SpringSecurity.repository.RoleRepository;
import com.spring.SpringSecurity.repository.UserRepository;

@Service
public class AuthService {
	
	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	private RoleRepository roleRepository;
	
	public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.roleRepository=roleRepository;
	}

	public ResponseEntity<UserRegisterResponse> registerUser(UserRegisterRequest userRegisterRequest) {
		
		User user= new User();
		user.setUserName(userRegisterRequest.getUserName());
		user.setEmail(userRegisterRequest.getEmail());
		user.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));
		Role role = roleRepository.findByName("USER_ROLE");
		user.getRoles().add(role);
		userRepository.save(user);
		UserRegisterResponse userRegisterResponse= new UserRegisterResponse();
		userRegisterResponse.setUserName(user.getUserName());
		userRegisterResponse.setMeesage("Registered Successfuly");
		return ResponseEntity.ok(userRegisterResponse);
		
	}
	
	

}
