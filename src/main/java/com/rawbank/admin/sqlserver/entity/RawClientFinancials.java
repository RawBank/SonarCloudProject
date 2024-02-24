/**
 * © Copyright 2021 Rawbank. All Rights Reserved.
 * 
 * Change Section:
 * Programmer			Date
 * Krishna				7/14/2022
 * Initial Coding.
 * 
 * 
 * Created By:
 * @author krishna
 * @since Jul 14, 2022
 * 
 */
package com.rawbank.admin.sqlserver.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author krishna
 *
 */
@Entity
@Table(name = "TBL_CLIENT_FINANCIALS" /*, schema = "JACQUESK" */)
public class RawClientFinancials {
	
	@Column(name = "INSTITUTION_NUMBER")
    private String institutionNumber;
    
	@Id
	@Column(name = "CLIENT_NUMBER")
    private String clientNumber;
	
	@Column(name = "BANK_ACCOUNT_NO")
	private String bankAccountNumber;
	
	@Column(name = "LIMIT")
	private String limit;
	
	@Column(name = "CURRENT_BALANCE")
	private String currentBalance;
	
	@Column(name = "TOTAL_DUES")
	private String totalDues;
	
	@Column(name = "TEL_PRIVATE")
	private String TelPrivate;
	
	public RawClientFinancials() {
		super();
	}
	
	public RawClientFinancials(String institutionNumber, String clientNumber, String bankAccountNumber) {
		super();
		this.institutionNumber = institutionNumber;
		this.clientNumber = clientNumber;
		this.bankAccountNumber = bankAccountNumber;
	}

	/**
	 * @return the clientNumber
	 */
	public String getClientNumber() {
		return clientNumber;
	}

	/**
	 * @param clientNumber the clientNumber to set
	 */
	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
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

	
	public String getInstitutionNumber() {
		return institutionNumber;
	}

	public void setInstitutionNumber(String institutionNumber) {
		this.institutionNumber = institutionNumber;
	}

	
	/**
	 * @return the limit
	 */
	public String getLimit() {
		return limit;
	}

	/**
	 * @param limit the limit to set
	 */
	public void setLimit(String limit) {
		this.limit = limit;
	}

	/**
	 * @return the currentBalance
	 */
	public String getCurrentBalance() {
		return currentBalance;
	}

	/**
	 * @param currentBalance the currentBalance to set
	 */
	public void setCurrentBalance(String currentBalance) {
		this.currentBalance = currentBalance;
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

	
	
	public String getTelPrivate() {
		return TelPrivate;
	}

	public void setTelPrivate(String telPrivate) {
		TelPrivate = telPrivate;
	}

	@Override
	public String toString() {
		return "RawClientFinancials [institutionNumber=" + institutionNumber + ", clientNumber=" + clientNumber
				+ ", bankAccountNumber=" + bankAccountNumber + ", limit=" + limit + ", currentBalance=" + currentBalance
				+ ", totalDues=" + totalDues + ", TelPrivate=" + TelPrivate + "]";
	}

	
	

}
