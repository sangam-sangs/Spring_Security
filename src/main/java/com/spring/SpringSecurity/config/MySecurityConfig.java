package com.spring.SpringSecurity.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class MySecurityConfig{
	
	@Bean
	 public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		http
		.csrf(csrf->csrf.disable())
		.authorizeHttpRequests(auth->
		auth.requestMatchers("/user/register").permitAll()
		.requestMatchers("/h2/**").permitAll()
		.anyRequest().authenticated())
		 .httpBasic(Customizer.withDefaults());
		
		return http.build();
		
	}
	

}
