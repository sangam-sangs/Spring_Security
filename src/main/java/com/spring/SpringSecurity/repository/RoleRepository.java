package com.spring.SpringSecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.SpringSecurity.entity.Role;

public interface RoleRepository  extends JpaRepository<Role, Long>{
	
	public Role findByName(String name); 

}
