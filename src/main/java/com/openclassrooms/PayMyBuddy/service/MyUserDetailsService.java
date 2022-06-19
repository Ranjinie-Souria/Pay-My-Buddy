package com.openclassrooms.PayMyBuddy.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.openclassrooms.PayMyBuddy.model.MyUserDetails;
import com.openclassrooms.PayMyBuddy.model.User;

public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> user = userService.getUserByEmail(email);
		user.orElseThrow(() -> new UsernameNotFoundException("This email is not registered : "+email));
		return user.map(MyUserDetails::new).get();
	}

}
