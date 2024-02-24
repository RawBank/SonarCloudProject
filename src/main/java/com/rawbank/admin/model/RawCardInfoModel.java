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
public class RawCardInfoModel {
	
	private String customerName;
	private String custCardNumber;
	private String cardStatus;
	private String changeReason;
	private MultipartFile file;
	private String customerNumber;
	private String currentCardStatus;
	private String oldCardLimit;
	private String newCardLimit;
	private String expiryDate;

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
	 * @return the custCardNumber
	 */
	public String getCustCardNumber() {
		return custCardNumber;
	}
	/**
	 * @param custCardNumber the custCardNumber to set
	 */
	public void setCustCardNumber(String custCardNumber) {
		this.custCardNumber = custCardNumber;
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
	 * @return the file
	 */
	public MultipartFile getFile() {
		return file;
	}
	/**
	 * @param file the file to set
	 */
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	/**
	 * @return the customerNumber
	 */
	public String getCustomerNumber() {
		return customerNumber;
	}
	/**
	 * @param customerNumber the customerNumber to set
	 */
	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}
	
	

	public String getCurrentCardStatus() {
		return currentCardStatus;
	}
	public void setCurrentCardStatus(String currentCardStatus) {
		this.currentCardStatus = currentCardStatus;
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
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public RawCardInfoModel(String customerName, String custCardNumber, String cardStatus, String changeReason,
			MultipartFile file, String customerNumber, String currentCardStatus, String oldCardLimit,
			String newCardLimit, String expiryDate) {
		super();
		this.customerName = customerName;
		this.custCardNumber = custCardNumber;
		this.cardStatus = cardStatus;
		this.changeReason = changeReason;
		this.file = file;
		this.customerNumber = customerNumber;
		this.currentCardStatus = currentCardStatus;
		this.oldCardLimit = oldCardLimit;
		this.newCardLimit = newCardLimit;
		this.expiryDate = expiryDate;
	}
	@Override
	public String toString() {
		return "RawCardInfoModel [customerName=" + customerName + ", custCardNumber=" + custCardNumber + ", cardStatus="
				+ cardStatus + ", changeReason=" + changeReason + ", file=" + file + ", customerNumber="
				+ customerNumber + ", currentCardStatus=" + currentCardStatus + ", oldCardLimit=" + oldCardLimit
				+ ", newCardLimit=" + newCardLimit + ", expiryDate=" + expiryDate + "]";
	}

	
	
	
}
