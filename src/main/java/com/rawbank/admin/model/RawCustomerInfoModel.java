/**
 * Copyright 2022 Rawbank. All Rights Reserved.
 * 
 * Change Section:
 * Programmer			Date
 * krishna				6/20/2022
 * CSC admin portal Project.
 * Initial Coding.
 * 
 * 
 * Created By:
 * @author krishna
 * @since Jun 20, 2022
 * 
 */
package com.rawbank.admin.model;

/**
 * @author krishna
 *
 */
public class RawCustomerInfoModel {
	
	private String customerNumber;
	private String name;
	private String phoneNumber;
	private String cardLimit;
	private String cardCurrentBalance;
	private String billingAccountBalance;
	private String provisionAccountBalance;
	private String accountLimit;
	private String currentCardStatus;
	private String cscNumber;
	private String totalDues;
	private String mobileNumber;
	private String bankAccountNumber;
    private String cscClientNumber;
    private String currency;
    private String expiryDate;
     // 
    private String privateTel;
    private String birthDate;
    
	/**
	 * @return the customerNumber
	 */
	public String getCustomerNumber() {
		return customerNumber;
	}

	/**
	 * @param customerNumber the customerNumber to set
	 */
	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the cardLimit
	 */
	public String getCardLimit() {
		return cardLimit;
	}

	/**
	 * @param cardLimit the cardLimit to set
	 */
	public void setCardLimit(String cardLimit) {
		this.cardLimit = cardLimit;
	}

	

	/**
	 * @return the billingAccountBalance
	 */
	public String getBillingAccountBalance() {
		return billingAccountBalance;
	}

	/**
	 * @param billingAccountBalance the billingAccountBalance to set
	 */
	public void setBillingAccountBalance(String billingAccountBalance) {
		this.billingAccountBalance = billingAccountBalance;
	}

	/**
	 * @return the provisionAccountBalance
	 */
	public String getProvisionAccountBalance() {
		return provisionAccountBalance;
	}

	/**
	 * @param provisionAccountBalance the provisionAccountBalance to set
	 */
	public void setProvisionAccountBalance(String provisionAccountBalance) {
		this.provisionAccountBalance = provisionAccountBalance;
	}

	/**
	 * @return the accountLimit
	 */
	public String getAccountLimit() {
		return accountLimit;
	}

	/**
	 * @param accountLimit the accountLimit to set
	 */
	public void setAccountLimit(String accountLimit) {
		this.accountLimit = accountLimit;
	}

	/**
	 * @return the currentCardStatus
	 */
	public String getCurrentCardStatus() {
		return currentCardStatus;
	}

	/**
	 * @param currentCardStatus the currentCardStatus to set
	 */
	public void setCurrentCardStatus(String currentCardStatus) {
		this.currentCardStatus = currentCardStatus;
	}

	/**
	 * @return the cscNumber
	 */
	public String getCscNumber() {
		return cscNumber;
	}

	/**
	 * @param cscNumber the cscNumber to set
	 */
	public void setCscNumber(String cscNumber) {
		this.cscNumber = cscNumber;
	}

	/**
	 * @return the cardCurrentBalance
	 */
	public String getCardCurrentBalance() {
		return cardCurrentBalance;
	}

	/**
	 * @param cardCurrentBalance the cardCurrentBalance to set
	 */
	public void setCardCurrentBalance(String cardCurrentBalance) {
		this.cardCurrentBalance = cardCurrentBalance;
	}

	/**
	 * @return the totalDues
	 */
	public String getTotalDues() {
		return totalDues;
	}

	/**
	 * @param totalDues the totalDues to set
	 */
	public void setTotalDues(String totalDues) {
		this.totalDues = totalDues;
	}

	/**
	 * @return the mobileNumber
	 */
	public String getMobileNumber() {
		return mobileNumber;
	}

	/**
	 * @param mobileNumber the mobileNumber to set
	 */
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	/**
	 * @return the bankAccountNumber
	 */
	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	/**
	 * @param bankAccountNumber the bankAccountNumber to set
	 */
	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	
	/**
	 * @returnt the csc client number 
	 * note that  there is a difference between csc and Bank  client Number 
	 * This field is  used to call some  csc api  during the reload   of card 
	 *
	 */
	public String getCscClientNumber() {
		return cscClientNumber;
	}
	/**
	 * @param cscClientNumber the cscClientNumber to set
	 */
	public void setCscClientNumber(String cscClientNumber) {
		this.cscClientNumber = cscClientNumber;
	}

	
	
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	
	
	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public RawCustomerInfoModel() {
		super();
	}

	public String getPrivateTel() {
		return privateTel;
	}

	public void setPrivateTel(String privateTel) {
		this.privateTel = privateTel;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "RawCustomerInfoModel [customerNumber=" + customerNumber + ", name=" + name + ", phoneNumber="
				+ phoneNumber + ", cardLimit=" + cardLimit + ", cardCurrentBalance=" + cardCurrentBalance
				+ ", billingAccountBalance=" + billingAccountBalance + ", provisionAccountBalance="
				+ provisionAccountBalance + ", accountLimit=" + accountLimit + ", currentCardStatus="
				+ currentCardStatus + ", cscNumber=" + cscNumber + ", totalDues=" + totalDues + ", mobileNumber="
				+ mobileNumber + ", bankAccountNumber=" + bankAccountNumber + ", cscClientNumber=" + cscClientNumber
				+ ", currency=" + currency + ", expiryDate=" + expiryDate + ", privateTel=" + privateTel
				+ ", birthDate=" + birthDate + "]";
	}




	
}
