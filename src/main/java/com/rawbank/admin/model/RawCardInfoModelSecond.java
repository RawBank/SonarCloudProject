/**
 * © Copyright 2021 Rawbank. All Rights Reserved.
 * 
 * Change Section:
 * Programmer			Date
 * Krishna				7/19/2022
 * Initial Coding.
 * 
 * 
 * Created By:
 * @author krishna
 * @since Jul 19, 2022
 * 
 */
package com.rawbank.admin.model;


import org.springframework.web.multipart.MultipartFile;

/**
 * @author krishna
 *
 */
public class RawCardInfoModelSecond extends RawCustomerInfoModel{
	
	private String customerName;
	private String custCardNumber;
	private String cardStatus;
	private String changeReason;
	private MultipartFile file;
	private String oldCardLimit;
	private String newCardLimit;
	private String debitA;
	private String creditA;
	private String debitB;
	private String creditB;
	private Float montantTxnA;
	private Float montantTxnB;
	private String reqCancelId;
	private String cardOldStatus;
	private String cancelComment;
	private String codeAgenceCreditA;
	private String codeAgenceDebitA;
	private String codeAgenceCreditB;
	private String codeAgenceDebitB;
	
	public RawCardInfoModelSecond() {
		
	}

	public RawCardInfoModelSecond(String customerName, String custCardNumber, String cardStatus, String changeReason,
			MultipartFile file, String oldCardLimit, String newCardLimit, String debitA, String creditA, String debitB,
			String creditB, Float montantTxnA, Float montantTxnB, String reqCancelId, String cardOldStatus,
			String cancelComment, String codeAgenceCreditA, String codeAgenceDebitA, String codeAgenceCreditB,
			String codeAgenceDebitB) {
		super();
		this.customerName = customerName;
		this.custCardNumber = custCardNumber;
		this.cardStatus = cardStatus;
		this.changeReason = changeReason;
		this.file = file;
		this.oldCardLimit = oldCardLimit;
		this.newCardLimit = newCardLimit;
		this.debitA = debitA;
		this.creditA = creditA;
		this.debitB = debitB;
		this.creditB = creditB;
		this.montantTxnA = montantTxnA;
		this.montantTxnB = montantTxnB;
		this.reqCancelId = reqCancelId;
		this.cardOldStatus = cardOldStatus;
		this.cancelComment = cancelComment;
		this.codeAgenceCreditA = codeAgenceCreditA;
		this.codeAgenceDebitA = codeAgenceDebitA;
		this.codeAgenceCreditB = codeAgenceCreditB;
		this.codeAgenceDebitB = codeAgenceDebitB;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustCardNumber() {
		return custCardNumber;
	}

	public void setCustCardNumber(String custCardNumber) {
		this.custCardNumber = custCardNumber;
	}

	public String getCardStatus() {
		return cardStatus;
	}

	public void setCardStatus(String cardStatus) {
		this.cardStatus = cardStatus;
	}

	public String getChangeReason() {
		return changeReason;
	}

	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
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

	public String getDebitA() {
		return debitA;
	}

	public void setDebitA(String debitA) {
		this.debitA = debitA;
	}

	public String getCreditA() {
		return creditA;
	}

	public void setCreditA(String creditA) {
		this.creditA = creditA;
	}

	public String getDebitB() {
		return debitB;
	}

	public void setDebitB(String debitB) {
		this.debitB = debitB;
	}

	public String getCreditB() {
		return creditB;
	}

	public void setCreditB(String creditB) {
		this.creditB = creditB;
	}

	public Float getMontantTxnA() {
		return montantTxnA;
	}

	public void setMontantTxnA(Float montantTxnA) {
		this.montantTxnA = montantTxnA;
	}

	public Float getMontantTxnB() {
		return montantTxnB;
	}

	public void setMontantTxnB(Float montantTxnB) {
		this.montantTxnB = montantTxnB;
	}

	public String getReqCancelId() {
		return reqCancelId;
	}

	public void setReqCancelId(String reqCancelId) {
		this.reqCancelId = reqCancelId;
	}

	public String getCardOldStatus() {
		return cardOldStatus;
	}

	public void setCardOldStatus(String cardOldStatus) {
		this.cardOldStatus = cardOldStatus;
	}

	public String getCancelComment() {
		return cancelComment;
	}

	public void setCancelComment(String cancelComment) {
		this.cancelComment = cancelComment;
	}

	public String getCodeAgenceCreditA() {
		return codeAgenceCreditA;
	}

	public void setCodeAgenceCreditA(String codeAgenceCreditA) {
		this.codeAgenceCreditA = codeAgenceCreditA;
	}

	public String getCodeAgenceDebitA() {
		return codeAgenceDebitA;
	}

	public void setCodeAgenceDebitA(String codeAgenceDebitA) {
		this.codeAgenceDebitA = codeAgenceDebitA;
	}

	public String getCodeAgenceCreditB() {
		return codeAgenceCreditB;
	}

	public void setCodeAgenceCreditB(String codeAgenceCreditB) {
		this.codeAgenceCreditB = codeAgenceCreditB;
	}

	public String getCodeAgenceDebitB() {
		return codeAgenceDebitB;
	}

	public void setCodeAgenceDebitB(String codeAgenceDebitB) {
		this.codeAgenceDebitB = codeAgenceDebitB;
	}

	@Override
	public String toString() {
		return "RawCardInfoModelSecond [customerName=" + customerName + ", custCardNumber=" + custCardNumber
				+ ", cardStatus=" + cardStatus + ", changeReason=" + changeReason + ", file=" + file + ", oldCardLimit="
				+ oldCardLimit + ", newCardLimit=" + newCardLimit + ", debitA=" + debitA + ", creditA=" + creditA
				+ ", debitB=" + debitB + ", creditB=" + creditB + ", montantTxnA=" + montantTxnA + ", montantTxnB="
				+ montantTxnB + ", reqCancelId=" + reqCancelId + ", cardOldStatus=" + cardOldStatus + ", cancelComment="
				+ cancelComment + ", codeAgenceCreditA=" + codeAgenceCreditA + ", codeAgenceDebitA=" + codeAgenceDebitA
				+ ", codeAgenceCreditB=" + codeAgenceCreditB + ", codeAgenceDebitB=" + codeAgenceDebitB + "]";
	}

	
	
	
}
