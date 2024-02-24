/**
 * © Copyright 2021 Rawbank. All Rights Reserved.
 * 
 * Change Section:
 * Programmer			Date
 * Krishna 				Aug 15, 2022
 * CSC admin portal Project Initial Coding.
 * 
 * 
 * Created By:
 * @author krishna
 * @since Aug 15, 2022
 * 
 */
package com.rawbank.admin.model;

import java.time.LocalDateTime;
import java.util.Arrays;

import com.rawbank.admin.oracle.entity.RawApprovalsOps;
import com.rawbank.admin.oracle.entity.RawCardLimitChange;

/**
 * @author krishna
 *
 */
public class RawPendingCardsModel {

	private static final String CHANGELIMIT = "changeLimit";
	private static final String RELOAD = "RELOAD";

	private Long id;


	private String cardOldStatus;
	private String cardNewStatus;
	private String cardStatusChangeDate;
	private String cardStatusCurrenttDate;

	private String oldCardLimit;
	private String newCardLimit;

	private String userName;
	private String validator;
	private String changeReason;
	private String fileName;
	private String cardNumber;

	private String approverStatus;
	private String cardStatus;
	private String action;
	private String customerName;
	private String fileType;
	private byte[] fileData;

	
	private String retrievalReference;
	private String amount;
	private String currency;
	private String aaSrcAccount;

	private String institutionNumber;
	private String proccode;
	private String transactionType;
	private String expiryDate;
	private LocalDateTime dateheuretrans;
	private String commentApprov;
	private String aaDestAccount;

	public RawPendingCardsModel() {
		super();
	}


	public RawPendingCardsModel(RawCardLimitChange rawCardLimitChange) {
		this.userName = rawCardLimitChange.getUserName();
		this.validator = rawCardLimitChange.getValidator();
		this.changeReason = rawCardLimitChange.getChangeReason();
		this.fileName = rawCardLimitChange.getFileName();
		this.cardNumber = rawCardLimitChange.getCscNumber();
		this.approverStatus = rawCardLimitChange.getApproverStatus();
		this.cardStatus = rawCardLimitChange.getCurrentCardStatus();
		this.action = CHANGELIMIT;
		this.oldCardLimit = rawCardLimitChange.getOldCardLimit();
		this.newCardLimit = rawCardLimitChange.getNewCardLimit();
		this.customerName = rawCardLimitChange.getCustomerName();

		this.id = rawCardLimitChange.getId();
	}



	public RawPendingCardsModel(RawApprovalsOps approvalsOps) {
		this.id = approvalsOps.getOpsid();
		this.retrievalReference = approvalsOps.getRetrievalReference();
		this.cardNumber = approvalsOps.getCardNumber();
		this.userName = approvalsOps.getInitiator();
		this.validator = approvalsOps.getApprouver();
		this.changeReason = approvalsOps.getComments();
		this.fileName = approvalsOps.getFileName();
		this.amount = approvalsOps.getTransamount();
		this.currency = approvalsOps.getCurrency();
		this.aaSrcAccount = approvalsOps.getAaSrcAccount();
		this.proccode = approvalsOps.getProccode();
		this.institutionNumber = approvalsOps.getInstitutionNumber();
		this.transactionType = approvalsOps.getTransactionType();
		this.customerName = approvalsOps.getCustomerName();
		this.approverStatus = approvalsOps.getApprouverStatus();
		this.expiryDate = approvalsOps.getExpiryDate();
		this.aaSrcAccount = approvalsOps.getAaSrcAccount();
		this.dateheuretrans = approvalsOps.getDateheuretrans();
		this.commentApprov = approvalsOps.getCommentsApprov();
		this.aaDestAccount = approvalsOps.getAaDestAccount();
		this.action = RELOAD;

	}

	/**
	 * @return the cardOldStatus
	 */
	public String getCardOldStatus() {
		return cardOldStatus;
	}

	/**
	 * @param cardOldStatus the cardOldStatus to set
	 */
	public void setCardOldStatus(String cardOldStatus) {
		this.cardOldStatus = cardOldStatus;
	}

	/**
	 * @return the cardNewStatus
	 */
	public String getCardNewStatus() {
		return cardNewStatus;
	}

	/**
	 * @param cardNewStatus the cardNewStatus to set
	 */
	public void setCardNewStatus(String cardNewStatus) {
		this.cardNewStatus = cardNewStatus;
	}

	/**
	 * @return the cardStatusChangeDate
	 */
	public String getCardStatusChangeDate() {
		return cardStatusChangeDate;
	}

	/**
	 * @param cardStatusChangeDate the cardStatusChangeDate to set
	 */
	public void setCardStatusChangeDate(String cardStatusChangeDate) {
		this.cardStatusChangeDate = cardStatusChangeDate;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the validator
	 */
	public String getValidator() {
		return validator;
	}

	/**
	 * @param validator the validator to set
	 */
	public void setValidator(String validator) {
		this.validator = validator;
	}

	/**
	 * @return the changeReason
	 */
	public String getChangeReason() {
		return changeReason;
	}

	/**
	 * @param changeReason the changeReason to set
	 */
	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the cardNumber
	 */
	public String getCardNumber() {
		return cardNumber;
	}

	/**
	 * @param cardNumber the cardNumber to set
	 */
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	/**
	 * @return the cardStatusCurrenttDate
	 */
	public String getCardStatusCurrenttDate() {
		return cardStatusCurrenttDate;
	}

	/**
	 * @param cardStatusCurrenttDate the cardStatusCurrenttDate to set
	 */
	public void setCardStatusCurrenttDate(String cardStatusCurrenttDate) {
		this.cardStatusCurrenttDate = cardStatusCurrenttDate;
	}

	/**
	 * @return the approverStatus
	 */
	public String getApproverStatus() {
		return approverStatus;
	}

	/**
	 * @param approverStatus the approverStatus to set
	 */
	public void setApproverStatus(String approverStatus) {
		this.approverStatus = approverStatus;
	}

	public String getOldCardLimit() {
		return oldCardLimit;
	}

	public void setOldCardLimit(String oldCardLimit) {
		this.oldCardLimit = oldCardLimit;
	}

	public String getNewCardLimit() {
		return newCardLimit;
	}

	public void setNewCardLimit(String newCardLimit) {
		this.newCardLimit = newCardLimit;
	}

	/**
	 * @return the cardStatus
	 */
	public String getCardStatus() {
		return cardStatus;
	}

	/**
	 * @param cardStatus the cardStatus to set
	 */
	public void setCardStatus(String cardStatus) {
		this.cardStatus = cardStatus;
	}

	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @return the fileType
	 */
	public String getFileType() {
		return fileType;
	}

	/**
	 * @param fileType the fileType to set
	 */
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	/**
	 * @return the fileData
	 */
	public byte[] getFileData() {
		return fileData;
	}

	/**
	 * @param fileData the fileData to set
	 */
	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	public String getRetrievalReference() {
		return retrievalReference;
	}

	public void setRetrievalReference(String retrievalReference) {
		this.retrievalReference = retrievalReference;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getAaSrcAccount() {
		return aaSrcAccount;
	}

	public void setAaSrcAccount(String aaSrcAccount) {
		this.aaSrcAccount = aaSrcAccount;
	}

	public String getInstitutionNumber() {
		return institutionNumber;
	}

	public void setInstitutionNumber(String institutionNumber) {
		this.institutionNumber = institutionNumber;
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

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getAaDestAccount() {
		return aaDestAccount;
	}

	public void setAaDestAccount(String aaDestAccount) {
		this.aaDestAccount = aaDestAccount;
	}

	public String getCommentApprov() {
		return commentApprov;
	}

	public void setCommentApprov(String commentApprov) {
		this.commentApprov = commentApprov;
	}

	public LocalDateTime getDateheuretrans() {
		return dateheuretrans;
	}

	public void setDateheuretrans(LocalDateTime dateheuretrans) {
		this.dateheuretrans = dateheuretrans;
	}

	@Override
	public String toString() {
		return "RawPendingCardsModel [id=" + id + ", cardOldStatus=" + cardOldStatus + ", cardNewStatus="
				+ cardNewStatus + ", cardStatusChangeDate=" + cardStatusChangeDate + ", cardStatusCurrenttDate="
				+ cardStatusCurrenttDate + ", oldCardLimit=" + oldCardLimit + ", newCardLimit=" + newCardLimit
				+ ", userName=" + userName + ", validator=" + validator + ", changeReason=" + changeReason
				+ ", fileName=" + fileName + ", cardNumber=" + cardNumber + ", approverStatus=" + approverStatus
				+ ", cardStatus=" + cardStatus + ", action=" + action + ", customerName=" + customerName + ", fileType="
				+ fileType + ", fileData=" + Arrays.toString(fileData) + ", retrievalReference=" + retrievalReference
				+ ", amount=" + amount + ", currency=" + currency + ", aaSrcAccount=" + aaSrcAccount
				+ ", institutionNumber=" + institutionNumber + ", proccode=" + proccode + ", transactionType="
				+ transactionType + ", expiryDate=" + expiryDate + ", dateheuretrans=" + dateheuretrans
				+ ", commentApprov=" + commentApprov + ", aaDestAccount=" + aaDestAccount + "]";
	}

}
