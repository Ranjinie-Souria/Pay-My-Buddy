package com.openclassrooms.PayMyBuddy.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.openclassrooms.PayMyBuddy.model.MyUserDetails;
import com.openclassrooms.PayMyBuddy.repository.UserRepository;
import com.openclassrooms.PayMyBuddy.service.MyUserDetailsService;
import com.openclassrooms.PayMyBuddy.service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
class ConnectionControllerTest {

	@InjectMocks
	private UserService uService;
	
	@Mock
    private UserRepository uRepo;
	
	@InjectMocks
	private MyUserDetailsService userDetail;
	
	@Autowired
    private MockMvc mockMvc;
	
	private MyUserDetails userDetails = new MyUserDetails("admin", "admin@admin.com");


	@Test 
	void showConnections_shouldshowConnections() throws Exception{
		mockMvc.perform(get("/connection").with(user(userDetails))).andExpect(status().isOk());
	}

}
