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

/**
 * @author jacquesk
 *
 */
public class RawTransactionCsCResponseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String responseCode;

	private String responseDescription;

	private String authCode;

	private String balanceAmount;

	private Integer currencyCode;

	private String expiryDate;

	private String retrievalReference;

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseDescription() {
		return responseDescription;
	}

	public void setResponseDescription(String responseDescription) {
		this.responseDescription = responseDescription;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(String balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public Integer getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(Integer currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyMM")
	@JsonProperty(value = "expiryDate")
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getRetrievalReference() {
		return retrievalReference;
	}

	public void setRetrievalReference(String retrievalReference) {
		this.retrievalReference = retrievalReference;
	}

	public RawTransactionCsCResponseModel() {
		super();

	}

	public RawTransactionCsCResponseModel(String responseCode, String responseDescription, String authCode,
			String balanceAmount, Integer currencyCode, String expiryDate, String retrievalReference) {
		super();
		this.responseCode = responseCode;
		this.responseDescription = responseDescription;
		this.authCode = authCode;
		this.balanceAmount = balanceAmount;
		this.currencyCode = currencyCode;
		this.expiryDate = expiryDate;
		this.retrievalReference = retrievalReference;
	}

	@Override
	public String toString() {
		return "RawTransactionCsCResponseModel [responseCode=" + responseCode + ", responseDescription="
				+ responseDescription + ", authCode=" + authCode + ", balanceAmount=" + balanceAmount
				+ ", currencyCode=" + currencyCode + ", expiryDate=" + expiryDate + ", retrievalReference="
				+ retrievalReference + "]";
	}

}

