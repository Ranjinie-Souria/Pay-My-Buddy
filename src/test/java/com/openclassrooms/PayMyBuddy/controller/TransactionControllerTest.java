package com.openclassrooms.PayMyBuddy.controller;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.openclassrooms.PayMyBuddy.model.MyUserDetails;
import com.openclassrooms.PayMyBuddy.model.User;
import com.openclassrooms.PayMyBuddy.repository.TransactionRepository;
import com.openclassrooms.PayMyBuddy.repository.UserRepository;
import com.openclassrooms.PayMyBuddy.service.TransactionService;
import com.openclassrooms.PayMyBuddy.service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
class TransactionControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	@InjectMocks
	private TransactionService tService;
	
	@InjectMocks
	private UserService uService;
	
	@Mock
    private TransactionRepository tRepo;
	
	@Mock
    private UserRepository uRepo;

	private MyUserDetails userDetails = new MyUserDetails("admin", "admin@admin.com");

	
	@Test
	void showConnections() throws Exception {
		mockMvc.perform(get("/connection").with(user(userDetails))).andExpect(status().isOk());
	}

	@Test
	void aaddConnection_shouldSendError() throws Exception{
		List<User> listUser = new ArrayList<>();
		User user1 = new User("toto","toto@email.com","toto","10");
		User user2 = new User("tota","tota@email.com","toto","10");
		User user3 = new User("tote","tote@email.com","toto","10");
		listUser.add(user1);
		listUser.add(user2);
		listUser.add(user3);
		User user = new User("tutu", "tutu@gmail.com","tutu","10000");
		user.setConnections(listUser);
		Optional<User> boxUser = Optional.of(user);
		when(uRepo.findByEmail("toto@gmail.com")).thenReturn(Optional.of(user1));
		when(uRepo.findByEmail("tutu@gmail.com")).thenReturn(boxUser);
		
		mockMvc.perform(post("/connection")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE).with(user(userDetails))
				.param("email","toto@email.com"))
				.andExpect(redirectedUrl("/connection?UserNotFound"));
	}
	
}
