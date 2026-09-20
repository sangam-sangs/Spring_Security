package com.spring.SpringSecurity.service;

import java.util.Optional;

import org.jspecify.annotations.Nullable;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import com.spring.SpringSecurity.entity.GoogleLoggedInUser;
import com.spring.SpringSecurity.repository.GoogleUserRepository;

@Service
public class GoogleUserService {
	
	private GoogleUserRepository googleUserRepository;
	
	
	public GoogleLoggedInUser registerOrUpdate(String provider, OidcUser oidcUser)
	{
		String providerSubject= oidcUser.getSubject();
		String name=oidcUser.getClaimAsString("name");
		String email=oidcUser.getClaimAsString("email");
		Optional<GoogleLoggedInUser> existingUser= googleUserRepository.findByProviderAndProviderSubject(provider, providerSubject);
		if(existingUser.isPresent())
		{
			 GoogleLoggedInUser user= existingUser.get();
			 user.setName(name);
			 user.setEmail(email);
			 return user;
		}
		else {
			GoogleLoggedInUser googleUser= new GoogleLoggedInUser(name, email, provider, providerSubject);
			 return googleUserRepository.save(googleUser);
		}
		
	}


	public Optional<GoogleLoggedInUser> findbyProviderAndSubject(String provider, @Nullable String subject) {
		// TODO Auto-generated method stub
		return googleUserRepository.findByProviderAndProviderSubject(provider, subject);
	}

}
