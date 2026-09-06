package com.spring.SpringSecurity.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.SpringSecurity.entity.User;

public interface UserRepository  extends JpaRepository<User, Long>{

	@EntityGraph(attributePaths = "roles")
	User findByUserName(String username);

}
