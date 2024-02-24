/**
* Copyright 2022 Rawbank. All Rights Reserved.
* 
 * Change Section:
* Programmer               Date
*
* 
 * 
 * Created By:
* @author jacquesk
* @since 23 août 2022
* 
 */

package com.rawbank.admin.model;

import java.io.Serializable;

/**
 * @author jacquesk
 *
 */
public class RawCardBalanceResponseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String accountNumber;

	private String availableAmount;

	private String accountStatus;

	private String currencyCode;

	private String currentAmount;

	private String limitAmount;

	private String pendingAuthsAmount;

	private String billingLevel;

	private String clientLevel;

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAvailableAmount() {
		return availableAmount;
	}

	public void setAvailableAmount(String availableAmount) {
		this.availableAmount = availableAmount;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getCurrentAmount() {
		return currentAmount;
	}

	public void setCurrentAmount(String currentAmount) {
		this.currentAmount = currentAmount;
	}

	public String getLimitAmount() {
		return limitAmount;
	}

	public void setLimitAmount(String limitAmount) {
		this.limitAmount = limitAmount;
	}

	public String getPendingAuthsAmount() {
		return pendingAuthsAmount;
	}

	public void setPendingAuthsAmount(String pendingAuthsAmount) {
		this.pendingAuthsAmount = pendingAuthsAmount;
	}

	public String getBillingLevel() {
		return billingLevel;
	}

	public void setBillingLevel(String billingLevel) {
		this.billingLevel = billingLevel;
	}

	public String getClientLevel() {
		return clientLevel;
	}

	public void setClientLevel(String clientLevel) {
		this.clientLevel = clientLevel;
	}

	@Override
	public String toString() {
		return "RawCardBalanceResponseModel [accountNumber=" + accountNumber + ", availableAmount=" + availableAmount
				+ ", accountStatus=" + accountStatus + ", currencyCode=" + currencyCode + ", currentAmount="
				+ currentAmount + ", limitAmount=" + limitAmount + ", pendingAuthsAmount=" + pendingAuthsAmount
				+ ", billingLevel=" + billingLevel + ", clientLevel=" + clientLevel + "]";
	}

	public RawCardBalanceResponseModel() {
		super();
	}

}

/*
 * [ { "accountNumber": "00019179001", "availableAmount": "-58",
 * "accountStatus": "001", "currencyCode": "840", "currentAmount": null,
 * "limitAmount": "0", "pendingAuthsAmount": "0", "billingLevel": "001",
 * "clientLevel": "001" } ]
 */