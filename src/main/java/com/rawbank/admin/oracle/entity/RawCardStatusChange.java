/**
 * modified / created by @author jacquesk last on 2022-07-26 12:21:19.067
 */
package com.rawbank.admin.oracle.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CSC_ADMIN_CARD_STATUS" /*, schema = "JACQUESK"*/)
public class RawCardStatusChange {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, updatable = true, insertable = true)
	private Long id;

	@Column(name = "CARD_OLD_STATUS", nullable = true, updatable = true, insertable = true, length = 20)
	private String cardoldStatus;


	@Column(name = "CARD_NEW_STATUS", nullable = true, updatable = true, insertable = true, length = 20)
	private String cardnewStatus;

	@Column(name = "CARD_STATUS_CHANGE_DATE")
	private LocalDateTime cardStatusChangeDate;

	@Column(name = "USER_NAME", nullable = true, updatable = true, insertable = true, length = 20)
	private String userName;

	@Column(name = "VALIDATOR", nullable = true, updatable = true, insertable = true, length = 20)
	private String validator;

	@Column(name = "CHANGEREASON", nullable = true, updatable = true, insertable = true, length = 50)
	private String changeReason;

	@Column(name = "FILENAME", nullable = true, updatable = true, insertable = true, length = 50)
	private String fileName;
	
	
	@Column(name = "CARD_NUMBER", nullable = true, updatable = true, insertable = true, length = 25)
	private String cardNumber;

	@Column(name = "CARD_CURRENT_STATUS_DATE")  
	private LocalDateTime cardStatusCurrenttDate;
	
	@Column(name = "CARD_CURRENT_STATUS", nullable = true, updatable = true, insertable = true, length = 20)
	private String cardCurrenttStatus;
	


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCardoldStatus() {
		return cardoldStatus;
	}

	public void setCardoldStatus(String cardoldStatus) {
		this.cardoldStatus = cardoldStatus;
	}


	public String getCardnewStatus() {
		return cardnewStatus;
	}

	public void setCardnewStatus(String cardnewStatus) {
		this.cardnewStatus = cardnewStatus;
	}

	public LocalDateTime getCardStatusChangeDate() {
		return cardStatusChangeDate;
	}

	public void setCardStatusChangeDate(LocalDateTime cardStatusChangeDate) {
		this.cardStatusChangeDate = cardStatusChangeDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getValidator() {
		return validator;
	}

	public void setValidator(String validator) {
		this.validator = validator;
	}

	

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getChangeReason() {
		return changeReason;
	}

	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public RawCardStatusChange() {
		super();

	}

	

	public LocalDateTime getCardStatusCurrenttDate() {
		return cardStatusCurrenttDate;
	}

	public void setCardStatusCurrenttDate(LocalDateTime cardStatusCurrenttDate) {
		this.cardStatusCurrenttDate = cardStatusCurrenttDate;
	}

	public String getCardCurrenttStatus() {
		return cardCurrenttStatus;
	}

	public void setCardCurrenttStatus(String cardCurrenttStatus) {
		this.cardCurrenttStatus = cardCurrenttStatus;
	}



	@Override
	public String toString() {
		return "RawCardStatusChange [id=" + id + ", cardoldStatus=" + cardoldStatus + ", cardnewStatus=" + cardnewStatus
				+ ", cardStatusChangeDate=" + cardStatusChangeDate + ", userName=" + userName + ", validator="
				+ validator + ", changeReason=" + changeReason + ", fileName=" + fileName + ", cardNumber=" + cardNumber
				+ ", cardStatusCurrenttDate=" + cardStatusCurrenttDate + ", cardCurrenttStatus=" + cardCurrenttStatus
				+ "]";
	}


	
}
