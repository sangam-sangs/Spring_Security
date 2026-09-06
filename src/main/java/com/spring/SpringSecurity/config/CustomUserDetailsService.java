package com.spring.SpringSecurity.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.SpringSecurity.entity.User;
import com.spring.SpringSecurity.repository.UserRepository;

@Service 
public class CustomUserDetailsService implements UserDetailsService {
	
	private UserRepository userRepository;
	
	

	public CustomUserDetailsService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

          User user = userRepository.findByUserName(username);
         System.out.println(user.getPassword());
          return new  CustomUserDetails(user);
		
	}

}
