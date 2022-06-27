package com.openclassrooms.PayMyBuddy.controller;

import org.springframework.security.core.Authentication;

import com.openclassrooms.PayMyBuddy.model.MyUserDetails;

public class CurrentUser {
	
	public static MyUserDetails getCurrentUser(Authentication authentication) {
		return (MyUserDetails) authentication.getPrincipal();
	}

}
