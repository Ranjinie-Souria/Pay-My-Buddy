package com.openclassrooms.PayMyBuddy.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.openclassrooms.PayMyBuddy.model.User;
import com.openclassrooms.PayMyBuddy.repository.ConnectionRepository;
import com.openclassrooms.PayMyBuddy.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
	
	@InjectMocks
	private UserService uService;
	
	@Mock
    private UserRepository uRepo;
	
	@Mock
	private ConnectionRepository connectionRepository;

	/*
	 * @Test public void getUserTest(){ uService.saveUser(new User("admin", "admin",
	 * "admin", "0")); String aEmail = "a@a.com"; User userA = new User("a", aEmail,
	 * "a", "0"); uService.saveUser(userA);
	 * Assertions.assertEquals(uService.getUserByEmail(aEmail).get(),userA); }
	 */
    
	
	@Test
	void getConnectionsForEmail_shouldReturnEmails() {
		//GIVEN
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
		
		when(uRepo.findByEmail("tutu@gmail.com")).thenReturn(boxUser);
		
		//WHEN
		List<User> result = uService.getConnectionsForEmail("tutu@gmail.com");
		
		//THEN
		assertEquals(3, result.size());
	}
    

}
