package com.rawbank.admin.model;

import java.io.Serializable;

public class RawReloadDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String clientNumber;

	private String clientName;

	private String cardNumber;

	private String cardStatus;

	private String cardExpiredDate;

	private String reloadComment;

	private Float amountDue;

	private Float paidAmount;

	private Float balance;

	private Float limitBalance;

	private Float pendingAmount;

	private String currency;
	
	private String custAccount;
	
	private String cscAccountNumber;
	
	public String getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardStatus() {
		return cardStatus;
	}

	public void setCardStatus(String cardStatus) {
		this.cardStatus = cardStatus;
	}

	public String getCardExpiredDate() {
		return cardExpiredDate;
	}

	public void setCardExpiredDate(String cardExpiredDate) {
		this.cardExpiredDate = cardExpiredDate;
	}

	public String getReloadComment() {
		return reloadComment;
	}

	public void setReloadComment(String reloadComment) {
		this.reloadComment = reloadComment;
	}

	public Float getAmountDue() {
		return amountDue;
	}

	public void setAmountDue(Float amountDue) {
		this.amountDue = amountDue;
	}

	public Float getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(Float paidAmount) {
		this.paidAmount = paidAmount;
	}

	public Float getBalance() {
		return balance;
	}

	public void setBalance(Float balance) {
		this.balance = balance;
	}




	public Float getLimitBalance() {
		return limitBalance;
	}

	public void setLimitBalance(Float limitBalance) {
		this.limitBalance = limitBalance;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Float getPendingAmount() {
		return pendingAmount;
	}

	public void setPendingAmount(Float pendingAmount) {
		this.pendingAmount = pendingAmount;
	}
	

	public String getCustAccount() {
		return custAccount;
	}

	public void setCustAccount(String custAccount) {
		this.custAccount = custAccount;
	}

	

	public String getCscAccountNumber() {
		return cscAccountNumber;
	}

	public void setCscAccountNumber(String cscAccountNumber) {
		this.cscAccountNumber = cscAccountNumber;
	}

	@Override
	public String toString() {
		return "RawReloadDetails [clientNumber=" + clientNumber + ", clientName=" + clientName + ", cardNumber="
				+ cardNumber + ", cardStatus=" + cardStatus + ", cardExpiredDate=" + cardExpiredDate
				+ ", reloadComment=" + reloadComment + ", amountDue=" + amountDue + ", paidAmount=" + paidAmount
				+ ", balance=" + balance + ", limitBalance=" + limitBalance + ", pendingAmount=" + pendingAmount
				+ ", currency=" + currency + ", custAccount=" + custAccount + ", cscAccountNumber=" + cscAccountNumber
				+ "]";
	}

	


}
