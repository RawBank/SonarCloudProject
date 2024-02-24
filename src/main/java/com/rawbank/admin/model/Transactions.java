package com.rawbank.admin.model;

import java.sql.Date;
import java.sql.Time;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Transactions {
  
	 // this column is added to manage the auto inctrement  in the datatable backend service
	private  Integer  incr;
	
	private String authCode;
	
	private String issuingCurrency;
	
	private Float accountAmount;
	
	private String merchantName;
	
	private String merchantCity;
	
	private String merchantCountry;
	
	private Float  settlementAmount; 
	
	private Integer settlementCurrency;
	
	private Float transactionAmount;
	
	private Integer  transactionCurrency;
	
	private Date transactionDate;
	
	private String transactionCategory;
	
	private String transactionType;
	
	private Time transactionTime;
	
	private String reversalFlag;
	
	private String drCrIndicator;
	
	private Date recordDate;
	
	private Date valueDate;
	
	private String accountNumber;

	
	
	public Integer getIncr() {
		return incr;
	}


	public void setIncr(Integer incr) {
		this.incr = incr;
	}


	public String getAuthCode() {
		return authCode;
	}


	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	
	public String getIssuingCurrency() {
		return issuingCurrency;
	}

	public void setIssuingCurrency(String issuingCurrency) {
		this.issuingCurrency = issuingCurrency;
	}

	public Float getAccountAmount() {
		return accountAmount;
	}

	public void setAccountAmount(Float accountAmount) {
		this.accountAmount = accountAmount;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getMerchantCity() {
		return merchantCity;
	}

	public void setMerchantCity(String merchantCity) {
		this.merchantCity = merchantCity;
	}

	public String getMerchantCountry() {
		return merchantCountry;
	}

	public void setMerchantCountry(String merchantCountry) {
		this.merchantCountry = merchantCountry;
	}

	public Float getSettlementAmount() {
		return settlementAmount;
	}

	public void setSettlementAmount(Float settlementAmount) {
		this.settlementAmount = settlementAmount;
	}

	public Integer getSettlementCurrency() {
		return settlementCurrency;
	}

	public void setSettlementCurrency(Integer settlementCurrency) {
		this.settlementCurrency = settlementCurrency;
	}

	public Float getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(Float transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public Integer getTransactionCurrency() {
		return transactionCurrency;
	}

	public void setTransactionCurrency(Integer transactionCurrency) {
		this.transactionCurrency = transactionCurrency;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getTransactionCategory() {
		return transactionCategory;
	}

	public void setTransactionCategory(String transactionCategory) {
		this.transactionCategory = transactionCategory;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Time getTransactionTime() {
		return transactionTime;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
	public void setTransactionTime(Time transactionTime) {
		this.transactionTime = transactionTime;
	}

	public String getReversalFlag() {
		return reversalFlag;
	}

	public void setReversalFlag(String reversalFlag) {
		this.reversalFlag = reversalFlag;
	}

	public String getDrCrIndicator() {
		return drCrIndicator;
	}

	public void setDrCrIndicator(String drCrIndicator) {
		this.drCrIndicator = drCrIndicator;
	}

	public Date getRecordDate() {
		return recordDate;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	public Date getValueDate() {
		return valueDate;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
	public void setValueDate(Date valueDate) {
		this.valueDate = valueDate;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}


	public Transactions() {
		super();
	}

	@Override
	public String toString() {
		return "Transactions [incr =" +incr + ",authCode=" + authCode + ", issuingCurrency=" + issuingCurrency + ", accountAmount="
				+ accountAmount + ", merchantName=" + merchantName + ", merchantCity=" + merchantCity
				+ ", merchantCountry=" + merchantCountry + ", settlementAmount=" + settlementAmount
				+ ", settlementCurrency=" + settlementCurrency + ", transactionAmount=" + transactionAmount
				+ ", transactionCurrency=" + transactionCurrency + ", transactionDate=" + transactionDate
				+ ", transactionCategory=" + transactionCategory + ", transactionType=" + transactionType
				+ ", transactionTime=" + transactionTime + ", reversalFlag=" + reversalFlag + ", drCrIndicator="
				+ drCrIndicator + ", recordDate=" + recordDate + ", valueDate=" + valueDate + ", accountNumber="
				+ accountNumber + "]";
	}
	
	
	
}
 
//"authCode": "938985",
//"issuingCurrency": "840",
//"accountAmount": "52.79",
//"merchantName": "ECITIZEN",
//"merchantCity": "NAIROBI",
//"merchantCountry": "404",
//"settlementAmount": "52.79",
//"settlementCurrency": "840",
//"transactionAmount": "52.79",
//"transactionCurrency": "840",
//"transactionDate": "20210329",
//"transactionCategory": "001",
//"transactionType": "005",
//"transactionTime": "19:33:00",
//"reversalFlag": "000",
//"drCrIndicator": "002",
//"recordDate": "20210401",
//"valueDate": "20210426",
//"accountNumber": "00014393001"


