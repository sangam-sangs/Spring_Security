package com.spring.SpringSecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring.SpringSecurity.entity.Role;
import com.spring.SpringSecurity.repository.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	RoleRepository roleRepository;

	public ResponseEntity<String> saveRoles(Role role) {
		roleRepository.save(role);
		return ResponseEntity.ok("Saved Role");
		
	}

}
