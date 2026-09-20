package com.spring.SpringSecurity.service;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

@Service
public class CustomOidcUserService implements OAuth2UserService<OidcUserRequest, OidcUser> {
	
	private final OidcUserService oidcUserService= new OidcUserService();
	
	@Autowired
	private GoogleUserService googleUserService;

	@Override
	public @Nullable OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
		// TODO Auto-generated method stub
		OidcUser oidcUser = oidcUserService.loadUser(userRequest);
		String provider= userRequest.getClientRegistration().getRegistrationId();
		googleUserService.registerOrUpdate(provider, oidcUser);
		return oidcUser;
	}

}
