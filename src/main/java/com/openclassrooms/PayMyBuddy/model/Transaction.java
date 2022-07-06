package com.openclassrooms.PayMyBuddy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "transaction")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_transaction")
	private int idTransaction;
	
	@Column(name="sender_email")
	private String senderEmail;
	
	@Column(name="receive_email")
	private String receiverEmail;
	
	@Column(name="amount")
	private String amount;
	
	@Column(name="description")
	private String description;
	
	@ManyToOne
	@JoinColumn(name="iduser", nullable=false)
	private User user;

	public Transaction(String senderEmail, String receiverEmail, String amount, String description, User user) {
		this.senderEmail = senderEmail;
		this.receiverEmail = receiverEmail;
		this.amount = amount;
		this.description = description;
		this.user = user;
	}

	public Transaction () {
		
	}

	public String getSenderEmail() {
		return senderEmail;
	}

	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}

	public String getReceiverEmail() {
		return receiverEmail;
	}

	public void setReceiverEmail(String receiverEmail) {
		this.receiverEmail = receiverEmail;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getIdTransaction() {
		return idTransaction;
	}

}
