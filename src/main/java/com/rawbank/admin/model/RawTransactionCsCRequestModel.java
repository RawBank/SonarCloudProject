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
* @since 24 août 2022
* 
 */

package com.rawbank.admin.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rawbank.admin.config.RawAppConfig;

/**
 * @author jacquesk
 *
 */
public class RawTransactionCsCRequestModel implements Serializable {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String amount;
	
    private String cardNumber;
    
    private Integer currency;
    
    private  String expiryDate;
    
    private String  institutionNumber;
    
	private String retrievalReference;
	
	private String procCode;
	
	private String transactionType;
	

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Integer getCurrency() {
		return currency;
	}

	public void setCurrency(Integer currency) {
		this.currency = currency;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyMM")
	@JsonProperty(value = "expiryDate")
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getInstitutionNumber() {
		return institutionNumber;
	}

	public void setInstitutionNumber(String institutionNumber) {
		this.institutionNumber = institutionNumber;
	}

	public String getRetrievalReference() {
		return retrievalReference;
	}

	public void setRetrievalReference(String retrievalReference) {
		this.retrievalReference = retrievalReference;
	}

	public String getProcCode() {
		return procCode;
	}

	public void setProcCode(String procCode) {
		this.procCode = procCode;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}



	public RawTransactionCsCRequestModel() {
		super();

	}

	public RawTransactionCsCRequestModel(String amount, String cardNumber, Integer currency, String expiryDate,
			 String retrievalReference, String procCode, String transactionType) {
		super();
		this.amount = amount;
		this.cardNumber = cardNumber;
		this.currency = currency;
		this.expiryDate = expiryDate;
		this.institutionNumber = RawAppConfig.getCscinstitutionNumber();
		this.retrievalReference = retrievalReference;
		this.procCode = procCode;
		this.transactionType = transactionType;
	}

	@Override
	public String toString() {
		return "RawTransactionCsCRequestModel [amount=" + amount + ", cardNumber=" + cardNumber + ", currency="
				+ currency + ", expiryDate=" + expiryDate + ", institutionNumber=" + institutionNumber
				+ ", retrievalReference=" + retrievalReference + ", procCode=" + procCode + ", transactionType="
				+ transactionType + "]";
	}
	
}