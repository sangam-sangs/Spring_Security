package com.spring.SpringSecurity.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.SpringSecurity.entity.User;

@Repository
public interface UserRepository  extends JpaRepository<User, Long>{

	@EntityGraph(attributePaths = "roles")
	User findByUserName(String username);

}
