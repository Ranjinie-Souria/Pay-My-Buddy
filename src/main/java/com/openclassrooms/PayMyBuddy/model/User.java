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

import lombok.Data;

@Data
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
	
	@Column(name="email", unique = true)
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

}
