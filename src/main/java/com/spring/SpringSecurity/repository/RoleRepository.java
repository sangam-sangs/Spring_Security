package com.spring.SpringSecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.SpringSecurity.entity.Role;

@Repository
public interface RoleRepository  extends JpaRepository<Role, Long>{
	
	public Role findByName(String name); 

}
