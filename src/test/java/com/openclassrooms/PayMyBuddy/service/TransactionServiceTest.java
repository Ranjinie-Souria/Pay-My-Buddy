package com.openclassrooms.PayMyBuddy.service;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.openclassrooms.PayMyBuddy.model.Transaction;
import com.openclassrooms.PayMyBuddy.model.User;
import com.openclassrooms.PayMyBuddy.repository.TransactionRepository;
import com.openclassrooms.PayMyBuddy.repository.UserRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class TransactionServiceTest {
	
	@Autowired
	private TransactionService tService;
	
	@Autowired
	private UserService uService;
	
	@MockBean
    private UserRepository uRepo;
	
	@MockBean
    private TransactionRepository tRepo;

	/*
	 * @BeforeAll public static void beforeAllTests() throws Exception{
	 * uService.saveUser(new User("admin", "admin", "admin", "0")); String aEmail =
	 * "a@a.com"; String bEmail = "b@b.com"; User userA = new User("a", aEmail, "a",
	 * "0"); User userB = new User("b", bEmail, "b", "0"); uService.saveUser(userA);
	 * uService.saveUser(userB); userA = uService.getUserByEmail(aEmail).get();
	 * userB = uService.getUserByEmail(aEmail).get();
	 * 
	 * tService.makeATransaction(userA.getEmail(), userB.getEmail(), "10",
	 * "First Transaction", userA.getIdUser());
	 * tService.makeATransaction(userB.getEmail(), userA.getEmail(), "15",
	 * "Second Transaction", userB.getIdUser()); }
	 */
    
	@Test
	void getTransactionsForEmail() {
		User userA = uService.getUserByEmail("a@a.com").get();
    	List<Transaction> t = tService.getTransactionsForEmail(userA.getEmail());
    	Assertions.assertEquals(t.size(),2);
	}

}
