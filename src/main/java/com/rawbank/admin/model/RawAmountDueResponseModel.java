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


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "amountDue", "currency", "paymentDueDate", "accountNumber" })
public class RawAmountDueResponseModel {


		private String amountDue;

		private String currency;

		private String paymentDueDate;

		private String accountNumber;

		public String getAmountDue() {
			return amountDue;
		}
		@JsonProperty("amountDue")
		public void setAmountDue(String amountDue) {
			this.amountDue = amountDue;
		}

		public String getCurrency() {
			return currency;
		}

		@JsonProperty("currency")
		public void setCurrency(String currency) {
			this.currency = currency;
		}

		public String getPaymentDueDate() {
			return paymentDueDate;
		}

		
		@JsonProperty("paymentDueDate")
		public void setPaymentDueDate(String paymentDueDate) {
			this.paymentDueDate = paymentDueDate;
		}

		public String getAccountNumber() {
			return accountNumber;
		}

		
		@JsonProperty("accountNumber")
		public void setAccountNumber(String accountNumber) {
			this.accountNumber = accountNumber;
		}

		

		@Override
		public String toString() {
			return "RawAmountDueResponseModel [amountDue=" + amountDue + ", currency=" + currency + ", paymentDueDate="
					+ paymentDueDate + ", accountNumber=" + accountNumber + "]";
		}
		public RawAmountDueResponseModel(String amountDue, String currency, String paymentDueDate, String accountNumber) {
			super();
			this.amountDue = amountDue;
			this.currency = currency;
			this.paymentDueDate = paymentDueDate;
			this.accountNumber = accountNumber;
		}

		public RawAmountDueResponseModel() {
			super();

		}

	

}
