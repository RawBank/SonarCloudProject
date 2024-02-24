package com.rawbank.admin.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.rawbank.admin.config.RawAppConfig;

@JsonPropertyOrder({ "institutionNumber","currency", "cardNumber"})
public class RawAmountDueRequestModel implements Serializable {
 
	/**
	 *  "currency":"840",
	 */
	private static final long serialVersionUID = 1L;

	private  String   institutionNumber;
	 
	private String currency;
	
	private String  cardNumber;

	public String getInstitutionNumber() {
		return institutionNumber;
	}

	public void setInstitutionNumber(String institutionNumber) {
		this.institutionNumber = institutionNumber;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public RawAmountDueRequestModel() {
		super();
	}

	public RawAmountDueRequestModel( String cardNumber,String currency ) {
		super();
		this.institutionNumber = RawAppConfig.getCscinstitutionNumber();
		this.cardNumber = cardNumber;
		this.currency = currency;
	}

	@Override
	public String toString() {
		return "RawAmountDueModel [institutionNumber=" + institutionNumber + ", cardNumber=" + cardNumber + "]";
	}  
	
	
	
}
