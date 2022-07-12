package com.openclassrooms.PayMyBuddy.service;

public class CompareBalanceToTransaction {
	
	public static Boolean hasEnoughMoney(String amount, String balance) {
		double moneySent = Integer.parseInt(amount);
		double fee = (moneySent * 0.005);
		double moneyReceived = moneySent - fee;
		double currentBalance = Double.parseDouble(balance);
		if(currentBalance >= moneyReceived) {
			return true;
		}
		else {
			return false;
		}
	}

}
