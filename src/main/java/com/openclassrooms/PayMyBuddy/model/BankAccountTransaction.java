package com.openclassrooms.PayMyBuddy.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "bankaccounttransaction")
public class BankAccountTransaction {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idbank_account_transaction", updatable = false, nullable = false)
	private int idBankAccountTransaction;
	
	@Column(name="bank_account")
	private String bankAccount;
	
	@Column(name="amount")
	private String amount;
	
	@Column(name="description")
	private String description;
	
	private Boolean fromBankTransaction;

	@ManyToOne(
			targetEntity = User.class,
			cascade = { 
					CascadeType.PERSIST, 
					CascadeType.MERGE 
					}
			)
	@JoinColumn(name="iduser")
	private User idUser;

	public BankAccountTransaction(String bankAccount, String amount, String description, User idUser) {
		this.bankAccount = bankAccount;
		this.amount = amount;
		this.description = description;
		this.idUser = idUser;
		this.fromBankTransaction = false;
	}

	public BankAccountTransaction() {
	}

	public BankAccountTransaction(String iban, String amount, User idUser) {
		this.bankAccount = iban;
		this.amount = amount;
		this.description = "Transaction from bank account : "+iban;
		this.fromBankTransaction = true;
		this.idUser = idUser;
	}
	
	
}
