package com.spring.SpringSecurity.config;


import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class MySecurityConfig{
	
	@Value("${jwt.secret}")
	private String jwtSecretKey;
	
	@Value("${jwt.issuer}")
	private String jwtIssuer;
	
	
	@Bean
	 public PasswordEncoder passwordEncoder()
	 {
		 return new  BCryptPasswordEncoder();
	 }
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider(
			CustomUserDetailsService userDetailsService,
			PasswordEncoder passwordEncoder)
	{
		DaoAuthenticationProvider provider= new DaoAuthenticationProvider(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder);
		return provider;
	}
	
	@Bean
	 public SecurityFilterChain securityFilterChain(DaoAuthenticationProvider provider,
			 HttpSecurity http, JwtAuthenticationConverter jwtAuthenticationConverter) throws Exception
	{
		http
		.csrf(csrf->csrf.disable())
		.authenticationProvider(provider)
		//.formLogin(Customizer.withDefaults())
		//.httpBasic(Customizer.withDefaults())
		.authorizeHttpRequests(auth->
		auth.requestMatchers("/user/register","/roles/save", "/auth/login").permitAll()
		.requestMatchers("/h2/**").permitAll()
		.anyRequest().authenticated())
		.sessionManagement(session->
		session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.oauth2ResourceServer(oauth2->
		oauth2.jwt(jwt->
		jwt.jwtAuthenticationConverter(jwtAuthenticationConverter)));
		return http.build();
		
	}
	
	
	@Bean
	public SecretKey jwtSecretkey()
	{
		byte [] decodeKey=java.util.Base64.getDecoder().decode(jwtSecretKey);
		return new SecretKeySpec(decodeKey, "HmacSHA256");
				
	}
	
	@Bean
	public AuthenticationManager authenticationManager(DaoAuthenticationProvider daoAuthenticationProvider) {
		return new ProviderManager(daoAuthenticationProvider);
		
	}
	
	// It is used so that Authentication will not use its' default authentication mechanism
	@Bean
	public JwtAuthenticationConverter jwtAuthenticationConverter() {
	
		JwtGrantedAuthoritiesConverter authoritiesConverter= new JwtGrantedAuthoritiesConverter();
		authoritiesConverter.setAuthoritiesClaimName("authorities");
		//below will avoid to add extra prefix and will use my role 
		authoritiesConverter.setAuthorityPrefix("");
		JwtAuthenticationConverter authenticationConverter= new JwtAuthenticationConverter();
		authenticationConverter.setJwtGrantedAuthoritiesConverter(authoritiesConverter);
		return authenticationConverter;
	}
	
	//Used to Sign the token
	@Bean
	public JwtEncoder  jwtEncoder(SecretKey secretKey) {
	return NimbusJwtEncoder
			.withSecretKey(secretKey)
			.algorithm(MacAlgorithm.HS256)
			.build();
		
	}
	
	//To verify the token
	@Bean
	public JwtDecoder jwtDecoder(SecretKey secretKey) {
		 NimbusJwtDecoder deocder=NimbusJwtDecoder
				.withSecretKey(secretKey)
				.macAlgorithm(MacAlgorithm.HS256)
				.build();
		 
		 deocder.setJwtValidator(JwtValidators.createDefaultWithIssuer(jwtIssuer));
		 return deocder;
	}
	
	

}
