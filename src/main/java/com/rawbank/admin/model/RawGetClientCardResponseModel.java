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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author jacquesk
 *
 */
 // no need of request javabean as it is the get request
	public class RawGetClientCardResponseModel {

	 private String cardNumber;
	 
	 private String effectiveDate;
	 
	 private String expiryDate;
	 
	 private String cardStatus;
	 
	 private   String  cardLevel;
	 
	private String cardType;
	
	private String groupNumber;
	
	private Integer serviceContractId;
	
	private Integer serviceId;
	
	private String embossLine1;
	
	private  String cardAlias;

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
	@JsonProperty(value = "effectiveDate")
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
	@JsonProperty(value = "expiryDate")
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getCardStatus() {
		return cardStatus;
	}

	public void setCardStatus(String cardStatus) {
		this.cardStatus = cardStatus;
	}

	public String getCardLevel() {
		return cardLevel;
	}

	public void setCardLevel(String cardLevel) {
		this.cardLevel = cardLevel;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getGroupNumber() {
		return groupNumber;
	}

	public void setGroupNumber(String groupNumber) {
		this.groupNumber = groupNumber;
	}

	public Integer getServiceContractId() {
		return serviceContractId;
	}

	public void setServiceContractId(Integer serviceContractId) {
		this.serviceContractId = serviceContractId;
	}

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public String getEmbossLine1() {
		return embossLine1;
	}

	public void setEmbossLine1(String embossLine1) {
		this.embossLine1 = embossLine1;
	}

	public String getCardAlias() {
		return cardAlias;
	}

	public void setCardAlias(String cardAlias) {
		this.cardAlias = cardAlias;
	}



	public RawGetClientCardResponseModel() {
		super();
	}

	@Override
	public String toString() {
		return "RawGetClientCardResponseModel [cardNumber=" + cardNumber + ", effectiveDate=" + effectiveDate
				+ ", expiryDate=" + expiryDate + ", cardStatus=" + cardStatus + ", cardLevel=" + cardLevel
				+ ", cardType=" + cardType + ", groupNumber=" + groupNumber + ", serviceContractId=" + serviceContractId
				+ ", serviceId=" + serviceId + ", embossLine1=" + embossLine1 + ", cardAlias=" + cardAlias + "]";
	}
	
	
	
	
}
