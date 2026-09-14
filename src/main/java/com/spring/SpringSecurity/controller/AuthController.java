package com.spring.SpringSecurity.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import com.spring.SpringSecurity.models.LoginRequest;
import com.spring.SpringSecurity.models.LoginResponse;
import com.spring.SpringSecurity.service.JwtService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired 
	JwtService jwtService;
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login (@RequestBody LoginRequest loginRequest)
	{
		Authentication authenticationRequest = UsernamePasswordAuthenticationToken
				.unauthenticated(loginRequest.getUserName(), loginRequest.getPassword()); 
		Authentication authentication=authenticationManager.authenticate(authenticationRequest);
		
		String accessToken= jwtService.generateToken(authentication);
		LoginResponse loginResponse = new LoginResponse();
		loginResponse.setAccessToken(accessToken);
		return ResponseEntity.ok(loginResponse);
		
		
		
		
	}

}
