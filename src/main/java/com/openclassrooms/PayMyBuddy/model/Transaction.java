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
@Table(name = "Transaction")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idTransaction")
	private int idTransaction;
	
	@Column(name="senderEmail")
	private String senderEmail;
	
	@Column(name="receiverEmail")
	private String receiverEmail;
	
	@Column(name="amount")
	private String amount;
	
	@Column(name="description")
	private String description;
	
	@ManyToOne
	@JoinColumn(name="iduser", nullable=false)
	private User user;

	@Column(name = "idTransaction")
	public int getIdTransaction() {
		return idTransaction;
	}
	
	@Column(name = "idTransaction")
	public void setIdTransaction(int idTransaction) {
		this.idTransaction = idTransaction;
	}

	@Column(name = "senderEmail")
	public String getSenderEmail() {
		return senderEmail;
	}

	@Column(name = "senderEmail")
	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}
	
	@Column(name = "receiverEmail")
	public String getReceiverEmail() {
		return receiverEmail;
	}

	@Column(name = "receiverEmail")
	public void setReceiverEmail(String receiverEmail) {
		this.receiverEmail = receiverEmail;
	}

	@Column(name = "amount")
	public String getAmount() {
		return amount;
	}

	@Column(name = "amount")
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	@Column(name = "description")
	public String getDescription() {
		return description;
	}
	
	@Column(name = "description")
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "iduser")
	public User getUser() {
		return user;
	}
	
	@Column(name = "iduser")
	public void setUser(User user) {
		this.user = user;
	}
	

}
