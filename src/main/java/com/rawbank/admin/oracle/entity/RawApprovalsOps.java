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
* @since 4 nov. 2022
* 
 */

package com.rawbank.admin.oracle.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author jacquesk this class is helping to gather all pending records for
 *         approval purpose these can be rejected or approved
 */
@Entity
@Table(name = "CSC_ADMIN_APPROVAL_OPS" /*, schema = "JACQUESK"*/)
public class RawApprovalsOps {

	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OPSID")
	private Long opsid;

	@Column(name = "RETRIEVALREFRENCE")
	private String retrievalReference;

	@Column(name = "PROCCODE")
	private String proccode;

	@Column(name = "TRANSACTIONTYPE")
	private String transactionType;

	@Column(name = "ACQUIRERCOUNTRY")
	private String acquirerCountry;

	@Column(name = "INSTITUTIONNUMBER")
	private String institutionNumber;

	@Column(name = "CARDNUMBER")
	private String cardNumber;

	@Column(name = "AMOUNT")
	private String transamount;

	@Column(name = "CURRENCY")
	private String currency;

	@Column(name = "INITIATOR")
	private String initiator;

	@Column(name = "APPROUVER")
	private String approuver;

	@Column(name = "RESPONSECODE")
	private String responseCode;

	@Column(name = "RESPONSEMESSAGE")
	private String responseMessage;

	@Column(name = "A2ATRANSACTIONID")
	private String aaTransId;

	@Column(name = "OPSTYPE")
	private String opsType;

	@Column(name = "A2ASTATUS")
	private String aaStatus;

	@Column(name = "COMMENTS")
	private String comments;

	@Column(name = "A2AACOUNTSRC")
	private String aaSrcAccount;

	@Column(name = "A2AACOUNTDEST")
	private String aaDestAccount;

	@Column(name = "AUTHCODE")
	private String authCode;

	@Column(name = "FILENAME")
	private String fileName;

	@Column(name = "EXPIRYDATE")
	private String expiryDate;

	@Column(name = "APPROUVERSTATUS")
	private String approuverStatus;
	
	@Column(name ="CUSTOMERNAME")
	private String customerName;

	@Column(name ="COMMENTSAPPROV")
	 private String  commentsApprov;
	
	
	@Column(name = "DATEHEURETRANSINIT", nullable = false)
	//@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime dateheuretrans =LocalDateTime.now();
	
	
	
	@Column(name = "DATEHEURETRANSAPPROVAL", nullable = true)
	//@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime dateheuretransApproval ;
	
	
	//CSCACCOUNTNUMBER
	@Column(name = "CSCACCOUNTNUMBER", nullable = true)
	private String cscAccountNumber;

	public Long getOpsid() {
		return opsid;
	}

	public void setOpsid(Long opsid) {
		this.opsid = opsid;
	}

	public String getRetrievalReference() {
		return retrievalReference;
	}

	public void setRetrievalReference(String retrievalReference) {
		this.retrievalReference = retrievalReference;
	}

	public String getProccode() {
		return proccode;
	}

	public void setProccode(String proccode) {
		this.proccode = proccode;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getAcquirerCountry() {
		return acquirerCountry;
	}

	public void setAcquirerCountry(String acquirerCountry) {
		this.acquirerCountry = acquirerCountry;
	}

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

	public String getTransamount() {
		return transamount;
	}

	public void setTransamount(String transamount) {
		this.transamount = transamount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getInitiator() {
		return initiator;
	}

	public void setInitiator(String initiator) {
		this.initiator = initiator;
	}

	public String getApprouver() {
		return approuver;
	}

	public void setApprouver(String approuver) {
		this.approuver = approuver;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public String getAaTransId() {
		return aaTransId;
	}

	public void setAaTransId(String aaTransId) {
		this.aaTransId = aaTransId;
	}

	public String getOpsType() {
		return opsType;
	}

	public void setOpsType(String opsType) {
		this.opsType = opsType;
	}

	public String getAaStatus() {
		return aaStatus;
	}

	public void setAaStatus(String aaStatus) {
		this.aaStatus = aaStatus;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getAaSrcAccount() {
		return aaSrcAccount;
	}

	public void setAaSrcAccount(String aaSrcAccount) {
		this.aaSrcAccount = aaSrcAccount;
	}

	public String getAaDestAccount() {
		return aaDestAccount;
	}

	public void setAaDestAccount(String aaDestAccount) {
		this.aaDestAccount = aaDestAccount;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getApprouverStatus() {
		return approuverStatus;
	}

	public void setApprouverStatus(String approuverStatus) {
		this.approuverStatus = approuverStatus;
	}

	
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	

	public String getCommentsApprov() {
		return commentsApprov;
	}

	public void setCommentsApprov(String commentsApprov) {
		this.commentsApprov = commentsApprov;
	}

	
	
	public LocalDateTime getDateheuretrans() {
		return dateheuretrans;
	}

	public void setDateheuretrans(LocalDateTime dateheuretrans) {
		this.dateheuretrans = dateheuretrans;
	}

	public LocalDateTime getDateheuretransApproval() {
		return dateheuretransApproval;
	}

	public void setDateheuretransApproval(LocalDateTime dateheuretransApproval) {
		this.dateheuretransApproval = dateheuretransApproval;
	}
	
	

	public String getCscAccountNumber() {
		return cscAccountNumber;
	}

	public void setCscAccountNumber(String cscAccountNumber) {
		this.cscAccountNumber = cscAccountNumber;
	}

	@Override
	public String toString() {
		return "RawApprovalsOps [opsid=" + opsid + ", retrievalReference=" + retrievalReference + ", proccode="
				+ proccode + ", transactionType=" + transactionType + ", acquirerCountry=" + acquirerCountry
				+ ", institutionNumber=" + institutionNumber + ", cardNumber=" + cardNumber + ", transamount="
				+ transamount + ", currency=" + currency + ", initiator=" + initiator + ", approuver=" + approuver
				+ ", responseCode=" + responseCode + ", responseMessage=" + responseMessage + ", aaTransId=" + aaTransId
				+ ", opsType=" + opsType + ", aaStatus=" + aaStatus + ", comments=" + comments + ", aaSrcAccount="
				+ aaSrcAccount + ", aaDestAccount=" + aaDestAccount + ", authCode=" + authCode + ", fileName="
				+ fileName + ", expiryDate=" + expiryDate + ", approuverStatus=" + approuverStatus + ", customerName="
				+ customerName + ", commentsApprov=" + commentsApprov + ", dateheuretrans=" + dateheuretrans
				+ ", dateheuretransApproval=" + dateheuretransApproval + ", cscAccountNumber=" + cscAccountNumber + "]";
	}

	

	


}
