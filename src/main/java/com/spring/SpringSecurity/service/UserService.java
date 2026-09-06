package com.spring.SpringSecurity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring.SpringSecurity.entity.User;
import com.spring.SpringSecurity.models.UserResponse;
import com.spring.SpringSecurity.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	

	public ResponseEntity<List<UserResponse>> getAllUsers() {
		
		List<User> userList=  userRepository.findAll();
		List<UserResponse> responseList= userList.stream().map(user->{
					UserResponse userResponse= new UserResponse();
					userResponse.setUserName(user.getUserName());
					userResponse.setEmail(user.getEmail());
					userResponse.setRoles(user.getRoles().stream().map(role->role.getName()).toList());
					return userResponse;
				}).toList();
		return ResponseEntity.ok(responseList);
	}


	public ResponseEntity<UserResponse> getUser(String username) {
		User user= userRepository.findByUserName(username);
		UserResponse userResponse= new UserResponse();
		userResponse.setUserName(user.getUserName());
		userResponse.setEmail(user.getEmail());
		userResponse.setRoles(user.getRoles().stream().map(role->role.getName()).toList());
		return ResponseEntity.ok(userResponse);
	}


	public User registerUser(User user) {
		userRepository.save(user);
		return user;
		
	}

}
