package com.openclassrooms.PayMyBuddy.controller;

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
import com.openclassrooms.PayMyBuddy.repository.BankAccountTransactionRepository;
import com.openclassrooms.PayMyBuddy.service.BankAccountTransactionService;


@SpringBootTest
@AutoConfigureMockMvc
class BankTransactionControllerTest {
	
	@InjectMocks
	private BankAccountTransactionService uService;
	
	@Mock
    private BankAccountTransactionRepository uRepo;
	
	@Autowired
    private MockMvc mockMvc;

	private MyUserDetails userDetails = new MyUserDetails("admin", "admin@admin.com");

	@Test 
	void showTransactions_shouldShowAllTransactions() throws Exception{
		mockMvc.perform(get("/bankTransaction").with(user(userDetails)))
				.andExpect(status().isOk());
	}

}
