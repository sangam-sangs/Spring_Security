package com.spring.SpringSecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.SpringSecurity.entity.GoogleLoggedInUser;
import java.util.Optional;


@Repository
public interface GoogleUserRepository extends JpaRepository<GoogleLoggedInUser, Long> {
	
	Optional<GoogleLoggedInUser>  findByProviderAndProviderSubject(String provider, String providerSubject);

}
