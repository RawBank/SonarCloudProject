/**
 * © Copyright 2021 Rawbank. All Rights Reserved.
 * 
 * Change Section:
 * Programmer			Date
 * Krishna				Aug/8/2022
 * CSC admin portal Project.
 * Initial Coding.
 * 
 * 
 * Created By:
 * @author krishna
 * @since Aug 8, 2022
 * 
 */
package com.rawbank.admin.oracle.entity;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.rawbank.admin.model.RawCardInfoModel;
import com.rawbank.admin.utility.RawUtility;

/**
 * @author krishna
 *
 */
@Entity
@Table(name = "CSC_ADMIN_CARD_LIMIT" /*, schema = "JACQUESK"*/)
public class RawCardLimitChange {
	
	private static final String PENDING = "Pending";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "CUSTOMER_NAME", nullable = true, updatable = true, insertable = true, length = 20)
	private String customerName;
	
	@Column(name = "CSC_NUMBER", nullable = true, updatable = true, insertable = true, length = 20)
	private String cscNumber;
	
	@Column(name = "CURRENT_CARD_STATUS", nullable = true, updatable = true, insertable = true, length = 20)
	private String currentCardStatus;
	
	@Column(name = "OLD_CARD_LIMIT", nullable = true, updatable = true, insertable = true, length = 20)
	private String oldCardLimit;
	
	@Column(name = "NEW_CARD_LIMIT", nullable = true, updatable = true, insertable = true, length = 20)
	private String newCardLimit;
	
	@Column(name = "USER_NAME", nullable = true, updatable = true, insertable = true, length = 20)
	private String userName;
	
	@Column(name = "VALIDATOR", nullable = true, updatable = true, insertable = true, length = 20)
	private String validator;
	
	@Column(name = "APPROVER_STATUS", nullable = true, updatable = true, insertable = true, length = 20)
	private String approverStatus;
	
	@Column(name = "DATE_CREATED")
	private Timestamp dateCreated;
	
	@Column(name = "FILE_NAME")
	private String fileName;
	
	@Column(name = "FILE_TYPE")
	private String fileType;
	
	@Column(name = "FILE_DATA")
	private byte[] fileData;
	
	@Column(name = "CHANGE_REASON", nullable = true, updatable = true, insertable = true, length = 50)
	private String changeReason;

	/**
	 * 
	 */
	public RawCardLimitChange() {
		super();
	}

	public RawCardLimitChange(RawCardInfoModel rawCardInfoModel) throws IOException {
		super();
		this.customerName = rawCardInfoModel.getCustomerName();
		this.cscNumber = rawCardInfoModel.getCustCardNumber();
		this.currentCardStatus = rawCardInfoModel.getCurrentCardStatus();
		this.oldCardLimit = rawCardInfoModel.getOldCardLimit();
		this.newCardLimit = rawCardInfoModel.getNewCardLimit();
		this.approverStatus = PENDING;
		this.dateCreated = new Timestamp(new Date().getTime());//Insertion Time.
		this.changeReason = rawCardInfoModel.getChangeReason();
		this.userName = RawUtility.getLoggedinUserName();
		if(rawCardInfoModel.getFile() != null) {
			this.fileName = rawCardInfoModel.getFile().getOriginalFilename();
			this.fileType = rawCardInfoModel.getFile().getContentType();
			this.fileData = rawCardInfoModel.getFile().getBytes();
		}
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
	 * @return the cscNumber
	 */
	public String getCscNumber() {
		return cscNumber;
	}

	/**
	 * @param cscNumber the cscNumber to set
	 */
	public void setCscNumber(String cscNumber) {
		this.cscNumber = cscNumber;
	}

	/**
	 * @return the currentCardStatus
	 */
	public String getCurrentCardStatus() {
		return currentCardStatus;
	}

	/**
	 * @param currentCardStatus the currentCardStatus to set
	 */
	public void setCurrentCardStatus(String currentCardStatus) {
		this.currentCardStatus = currentCardStatus;
	}

	/**
	 * @return the oldCardLimit
	 */
	public String getOldCardLimit() {
		return oldCardLimit;
	}

	/**
	 * @param oldCardLimit the oldCardLimit to set
	 */
	public void setOldCardLimit(String oldCardLimit) {
		this.oldCardLimit = oldCardLimit;
	}

	/**
	 * @return the newCardLimit
	 */
	public String getNewCardLimit() {
		return newCardLimit;
	}

	/**
	 * @param newCardLimit the newCardLimit to set
	 */
	public void setNewCardLimit(String newCardLimit) {
		this.newCardLimit = newCardLimit;
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
	 * @return the status
	 */
	public String getApproverStatus() {
		return approverStatus;
	}

	/**
	 * @param status the status to set
	 */
	public void setApproverStatus(String approverStatus) {
		this.approverStatus = approverStatus;
	}

	/**
	 * @return the dateCreated
	 */
	public Timestamp getDateCreated() {
		return dateCreated;
	}

	/**
	 * @param dateCreated the dateCreated to set
	 */
	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
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
	
	

}
