package com.openclassrooms.PayMyBuddy.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	
	public User(String username, String email, String password, String balance) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.balance = balance;
	}
	
	public User() {
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="iduser")
	private int idUser;
	
	@Column(name="username")
	private String username;
	
	@Column(name="email", unique = true, nullable = false)
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Column(name="balance")
	private String balance;
	
	@ElementCollection(targetClass=User.class)
	@ManyToMany(
			fetch = FetchType.LAZY,
			cascade = { 
					CascadeType.PERSIST, 
					CascadeType.MERGE 
					}	
			)
	@JoinTable(
			name = "connection",
			joinColumns = @JoinColumn(name = "user_iduser"), 
			inverseJoinColumns = @JoinColumn(name = "user_iduser1")
			)
	private List<User> connections = new ArrayList<>();

	public User(String username, String email, String password, String balance, List<User> connections) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.balance = balance;
		this.connections = connections;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public List<User> getConnections() {
		return connections;
	}

	public void setConnections(List<User> connections) {
		this.connections = connections;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}	

	
}
