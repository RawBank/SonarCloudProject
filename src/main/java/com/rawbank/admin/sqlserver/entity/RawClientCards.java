/**
 * © Copyright 2021 Rawbank. All Rights Reserved.
 * 
 * Change Section:
 * Programmer			Date
 * Krishna				7/13/2022
 * Initial Coding.
 * 
 * 
 * Created By:
 * @author krishna
 * @since Jul 13, 2022
 * 
 */
package com.rawbank.admin.sqlserver.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author krishna
 *
 */
@Entity
@Table(name = "TBL_CLIENT_CARDS" /*, schema = "JACQUESK" */)
public class RawClientCards {
	
	@Column(name = "INSTITUTION_NUMBER")
	private String id;

	@Column(name = "CLIENT_NUMBER")
	private String clientNumber;
	
	@Id
	@Column(name = "CARD_NUMBER")
	private String cardNumber;
	
	@Column(name = "EMBOSS_LINE_1")
	private String customerName;
	
	@Column(name = "CARD_CURR")
	private String cardCurrency;
	
	@Column(name = "CARD_STATUS")
	private String cardStatus;
	
	@Column(name ="EXPIRY_DATE")
	 private String expiryDate;
	
	@Column (name ="PARENT_CLIENT_NUMBER")
	private String parentClientNumber; 
	
	/*@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "CLIENT_NUMBER", referencedColumnName = "id")
    private RawClientDetails rawClientDetails;*/

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the clientNumber
	 */
	public String getClientNumber() {
		return clientNumber;
	}

	/**
	 * @param clientNumber the clientNumber to set
	 */
	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
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
	 * @return the cardCurrency
	 */
	public String getCardCurrency() {
		return cardCurrency;
	}

	/**
	 * @param cardCurrency the cardCurrency to set
	 */
	public void setCardCurrency(String cardCurrency) {
		this.cardCurrency = cardCurrency;
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
	 * @return the rawClientDetails
	 */
	/*public RawClientDetails getRawClientDetails() {
		return rawClientDetails;
	}*/

	/**
	 * @param rawClientDetails the rawClientDetails to set
	 */
	/*public void setRawClientDetails(RawClientDetails rawClientDetails) {
		this.rawClientDetails = rawClientDetails;
	}*/

	
	
	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	@Override
	public String toString() {
		return "RawClientCards [id=" + id + ", clientNumber=" + clientNumber + ", cardNumber=" + cardNumber
				+ ", customerName=" + customerName + ", cardCurrency=" + cardCurrency + ", cardStatus=" + cardStatus
				+ ", expiryDate=" + expiryDate + ", parentClientNumber=" + parentClientNumber + "]";
	}




	

}
