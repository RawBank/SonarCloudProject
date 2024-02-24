package com.rawbank.admin.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"cardNumber","status","institutionNumber"})
public class RawChangeStatusRequestModel {

	
	private String cardNumber;
	private String status;
	private String   institutionNumber;
	public String getCardNumber() {
		return cardNumber;
	}
	@JsonProperty(value = "cardNumber")
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getStatus() {
		return status;
	}
	@JsonProperty(value = "status")
	public void setStatus(String status) {
		this.status = status;
	}
	public String getInstitutionNumber() {
		return institutionNumber;
	}
	
	@JsonProperty(value = "institutionNumber")
	public void setInstitutionNumber(String institutionNumber) {
		this.institutionNumber = institutionNumber;
	}
	public RawChangeStatusRequestModel() {
		super();
	}
	public RawChangeStatusRequestModel(String cardNumber, String status, String institutionNumber) {
		super();
		this.cardNumber = cardNumber;
		this.status = status;
		this.institutionNumber = institutionNumber;
	}
	@Override
	public String toString() {
		return "RawChangeStatusRequestModel [cardNumber=" + cardNumber + ", status=" + status + ", institutionNumber="
				+ institutionNumber + "]";
	}
	
	
	
	
}

