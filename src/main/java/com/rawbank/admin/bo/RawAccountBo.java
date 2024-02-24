/**
 * © Copyright 2021 Rawbank. All Rights Reserved.
 * 
 * Change Section:
 * Programmer			Date
 * Krishna				7/20/2022
 * Initial Coding.
 * 
 * 
 * Created By:
 * @author krishna
 * @since Jul 20, 2022
 * 
 */
package com.rawbank.admin.bo;

/**
 * @author krishna
 *
 */
public class RawAccountBo {
	
	private String accountNumber;
	private String limit;
	private String billingAccountBalance;
	private String provisionAccountBalance;
	private String accountLimit;
	private String accountStatus;
	private String customerNumber;
	
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getLimit() {
		return limit;
	}
	public void setLimit(String limit) {
		this.limit = limit;
	}
	public String getBillingAccountBalance() {
		return billingAccountBalance;
	}
	public void setBillingAccountBalance(String billingAccountBalance) {
		this.billingAccountBalance = billingAccountBalance;
	}
	public String getProvisionAccountBalance() {
		return provisionAccountBalance;
	}
	public void setProvisionAccountBalance(String provisionAccountBalance) {
		this.provisionAccountBalance = provisionAccountBalance;
	}
	public String getAccountLimit() {
		return accountLimit;
	}
	public void setAccountLimit(String accountLimit) {
		this.accountLimit = accountLimit;
	}
	public String getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}
	public String getCustomerNumber() {
		return customerNumber;
	}
	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}
	@Override
	public String toString() {
		return "RawAccountBo [accountNumber=" + accountNumber + ", limit=" + limit + ", billingAccountBalance="
				+ billingAccountBalance + ", provisionAccountBalance=" + provisionAccountBalance + ", accountLimit="
				+ accountLimit + ", accountStatus=" + accountStatus + ", customerNumber=" + customerNumber + "]";
	}
	
	

}