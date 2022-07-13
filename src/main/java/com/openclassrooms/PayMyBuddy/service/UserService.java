package com.openclassrooms.PayMyBuddy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.openclassrooms.PayMyBuddy.model.User;
import com.openclassrooms.PayMyBuddy.repository.ConnectionRepository;
import com.openclassrooms.PayMyBuddy.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ConnectionRepository connectionRepository;
	
	public Iterable<User> getUsers() {
		return userRepository.findAll();
	}
	
	public Optional<User> getUserById(Integer id) {
		return userRepository.findById(id);
	}
	
	public Optional<User> getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public User saveUser(User user) {
		return userRepository.save(user);		
	}
	
	public void deleteUserById(Integer id) {
		userRepository.deleteById(id);
	}
	
	public List<User> getConnectionsForEmail(String email){
		
		List<User> connectionEmails = new ArrayList<User>();
		Optional<User> user = userRepository.findByEmail(email);
		if (user.isPresent()) {
			for (User element : user.get().getConnections()) {
				connectionEmails.add(element);
			}
			return connectionEmails;
		} else {
			return null;
		}
	}
	
	public int addConnectionForEmail(String email, String friendEmail) {
		if(!email.equals(friendEmail)) {
			User newFriend = new User();
			try {
				newFriend = userRepository.findByEmail(friendEmail).get();
				User currentUser = userRepository.findByEmail(email).get();
				List<User> thisConnections = this.getConnectionsForEmail(email);
				if(!thisConnections.contains(newFriend)) {
					connectionRepository.addConnectionForId(currentUser.getIdUser(), newFriend.getIdUser());
				}
				else {
					return 3;
				}
				return 1;
			}
			catch(Exception NoSuchElementException){
				return 2;
			}
			
		}
		else{
			System.out.println("Email address is the same as the authentificated user.");
			return 0;
		}
		
	}
	
	public void deleteConnectionForEmail(String email, String friendEmail) {
		if(email != friendEmail) {
			User newFriend = userRepository.findByEmail(friendEmail).get();
			userRepository.findByEmail(email).get().getConnections().remove(newFriend);	
		}
	}
	
	@Transactional
	public void setSendAmount(int idUser, String moneySent) {
		Optional<User> userRepo = userRepository.findById(idUser);
		User user = userRepo.get();
		double sent = Double.parseDouble(moneySent);
		double balance = Double.parseDouble(user.getBalance());
		double newBalance = balance - sent;
		String strBalance = String.valueOf(newBalance);
		user.setBalance(strBalance);
		userRepository.save(user);		
	}
	
	@Transactional
	public void setGetAmount(int idUser, String moneySent) {
		Optional<User> userRepo = userRepository.findById(idUser);
		User user = userRepo.get();
		double sent = Double.parseDouble(moneySent);
		double balance = Double.parseDouble(user.getBalance());
		double newBalance = balance + sent;
		String strBalance = String.valueOf(newBalance);
		user.setBalance(strBalance);
		userRepository.save(user);
	}

	
	
	

}