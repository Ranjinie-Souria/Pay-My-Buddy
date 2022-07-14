package com.openclassrooms.PayMyBuddy.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.openclassrooms.PayMyBuddy.repository.UserRepository;
import com.openclassrooms.PayMyBuddy.service.MyUserDetailsService;
import com.openclassrooms.PayMyBuddy.service.UserService;


@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
	
	@InjectMocks
	private UserService uService;
	
	@Mock
    private UserRepository uRepo;
	
	@InjectMocks
	private MyUserDetailsService userDetail;
	
	@Autowired
    private MockMvc mockMvc;

	@Test 
	void home_shouldRedirectToHome() throws Exception{
		mockMvc.perform(get("/")).andExpect(status().isOk());
	}
		
	@Test 
	void login_shouldRedirectToLogin() throws Exception{
		mockMvc.perform(get("/login")).andExpect(status().isOk());
	}
	
	@Test 
	void registration_shouldRedirectToRegistration() throws Exception{
		mockMvc.perform(get("/registration")).andExpect(status().isOk());
	}
	
	@Test 
	void registration_shouldSaveUser() throws Exception{
		mockMvc.perform(post("/registration")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	            .param("username","toto").param("email","toto@email.com").param("password","a"))
				.andExpect(redirectedUrl("/login?success"));
	}
	
	@Test 
	void registration_shouldSendDuplicateEmailError() throws Exception{
		mockMvc.perform(post("/registration")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	            .param("username","a").param("email","a@email.com").param("password","a"))
				.andExpect(redirectedUrl("/registration?DuplicateEmail"));
	}

	@Test 
	void registration_shouldSendEmailError() throws Exception{
		mockMvc.perform(post("/registration")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	            .param("username","a").param("email","a").param("password","a"))
				.andExpect(redirectedUrl("/registration?IncorrectEmail"));
	}
	
	@Test
	@Profile("test")
	void login_shouldLogUser() throws Exception{
		mockMvc.perform(post("/login")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	            .param("username","admin@admin.com").param("password","admin").param("ROLES", "USER"))
				.andExpect(redirectedUrl("/"));
	}
	
}
