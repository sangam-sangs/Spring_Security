package com.spring.SpringSecurity.service;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
	
	@Value("${jwt.issuer}")
	private String jwtIssuer;
	
	@Value("${jwt.expiry}")
	private long jwtExpiry;
	
	@Autowired
	private JwtEncoder jwtEncoder;


	public String generateToken(Authentication authentication) {
		Instant now = Instant.now();
		List<String> authorities =authentication
				.getAuthorities()
				.stream()
				.map(GrantedAuthority :: getAuthority)
				.toList();
		
		JwtClaimsSet claims=JwtClaimsSet.builder()
				.issuer(jwtIssuer)
				.issuedAt(now)
				.expiresAt(now.plusSeconds(jwtExpiry))
				//subject is username 
				.subject(authentication.getName())
				//Authorities means roles
				.claim( "authorities",authorities)
				.build();
		
		Jwt jwt= jwtEncoder.encode(JwtEncoderParameters.from(claims));
		return jwt.getTokenValue();
		
}
	
}
