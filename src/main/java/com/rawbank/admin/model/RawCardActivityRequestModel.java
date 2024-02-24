package com.rawbank.admin.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "cardNumber", "dateFrom", "dateTo", "institutionNumber" })
public class RawCardActivityRequestModel {

	private String cardNumber;

	private String dateFrom;

	private String dateTo;

	private String institutionNumber;

	public String getCardNumber() {
		return cardNumber;
	}

	@JsonProperty(value = "cardNumber")
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getDateFrom() {
		return dateFrom;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
	@JsonProperty(value = "dateFrom")
	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public String getDateTo() {
		return dateTo;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
	@JsonProperty(value = "dateTo")
	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}

	public String getInstitutionNumber() {
		return institutionNumber;
	}

	@JsonProperty(value = "institutionNumber")
	public void setInstitutionNumber(String institutionNumber) {
		this.institutionNumber = institutionNumber;
	}

	public RawCardActivityRequestModel(String cardNumber, String dateFrom, String dateTo, String institutionNumber) {
		super();
		this.cardNumber = cardNumber;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.institutionNumber = institutionNumber;
	}

	public RawCardActivityRequestModel() {
		super();
	}

	@Override
	public String toString() {
		return "RawCardActivityRequestModel [cardNumber=" + cardNumber + ", dateFrom=" + dateFrom + ", dateTo=" + dateTo
				+ ", institutionNumber=" + institutionNumber + "]";
	}

}
