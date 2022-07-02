package com.openclassrooms.PayMyBuddy.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class MyUserDetails implements UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int idUser;

	private String username;
	
	private String password;
	
	private String email;
	
	private String balance;
	
	private List<User> connections;
	
	public MyUserDetails(User user) {
		this.idUser = user.getIdUser();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.email = user.getEmail();
		this.balance = user.getBalance();
		this.connections = user.getConnections();
	}
	
	public MyUserDetails(String username, String email) {
		this.username = username;
		this.email = email;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {

		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
