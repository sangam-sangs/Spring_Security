package com.spring.SpringSecurity.controller;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.SpringSecurity.entity.GoogleLoggedInUser;
import com.spring.SpringSecurity.service.GoogleUserService;

@RestController
@RequestMapping("/google")
public class GooglUserController {
	
	@Autowired
	private GoogleUserService googleUserService;
	
	
	@GetMapping("/home")
	public String home() {
		 return "Login Using http://localhost:8080/login/oauth2/code/google";
	}
	
	
	@GetMapping("/profile")
	public Map<String, Object> login(@AuthenticationPrincipal OidcUser oidcUser) {
		
		Optional<GoogleLoggedInUser> googleLoggedInUser= googleUserService.findbyProviderAndSubject("google", oidcUser.getSubject());
		
		Map<String,Object> userInfoMap= new HashMap<>();
		userInfoMap.put("internaluserId", googleLoggedInUser.get().getId());
		userInfoMap.put("provider", googleLoggedInUser.get().getProvider());
		userInfoMap.put("subject", oidcUser.getSubject());
		userInfoMap.put("name", oidcUser.getClaimAsString("name"));
		userInfoMap.put("email", oidcUser.getClaimAsString("email"));
		return userInfoMap;
		
	}
	
	

}
