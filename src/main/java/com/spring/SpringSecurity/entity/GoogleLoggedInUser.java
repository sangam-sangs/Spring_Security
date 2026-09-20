package com.spring.SpringSecurity.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Googgle_User")
public class GoogleLoggedInUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	private String email;
	
	// google 
	private String provider;
	
	/*
	 * Google will provide below information , sub will identifier for this user and we can store it for future use
	 * { "sub" : "1223404040", "name": , "email": ""
	 * 
	 * }
	 */
	
	private String providerSubject;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getProviderSubject() {
		return providerSubject;
	}
	public void setProviderSubject(String providerSubject) {
		this.providerSubject = providerSubject;
	}
	
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public GoogleLoggedInUser(String name, String email, String provider, String providerSubject) {
		super();
		this.name = name;
		this.email = email;
		this.provider = provider;
		this.providerSubject = providerSubject;
	}
	
	
	
	

}
