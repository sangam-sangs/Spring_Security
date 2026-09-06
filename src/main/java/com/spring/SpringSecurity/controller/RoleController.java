package com.spring.SpringSecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.SpringSecurity.entity.Role;
import com.spring.SpringSecurity.service.RoleService;

@RestController
@RequestMapping("/roles")
public class RoleController {
	
	@Autowired
	RoleService roleService;

	@PostMapping("/save")
	public ResponseEntity<String> saveRoles(@RequestBody Role role)
	{
		return roleService.saveRoles(role);
	}
	
}
