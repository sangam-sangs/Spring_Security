package com.spring.SpringSecurity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.SpringSecurity.entity.User;
import com.spring.SpringSecurity.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}


	public User getUser(String username) {
		 return userRepository.findByUserName(username);
	}


	public User registerUser(User user) {
		userRepository.save(user);
		return user;
		
	}

}
