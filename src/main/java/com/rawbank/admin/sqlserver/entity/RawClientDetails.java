/**
 * © Copyright 2021 Rawbank. All Rights Reserved.
 * 
 * Change Section:
 * Programmer			Date
 * Krishna				7/14/2022
 * Initial Coding.
 * 
 * 
 * Created By:
 * @author krishna
 * @since Jul 14, 2022
 * 
 */
package com.rawbank.admin.sqlserver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author krishna
 *
 */
@Entity
@Table(name = "TBL_CLIENT_DETAILS" /*, schema = "JACQUESK" */)
public class RawClientDetails {
	
	@Column(name = "INSTITUTION_NUMBER")
    private String institutionNumber;
	
	@Id
	@Column(name = "CLIENT_NUMBER")
    private String clientNumber;
	
  /* 	@ManyToOne
    @MapsId
    @JoinColumn(name = "CLIENT_NUMBER")
    private RawClientCards rawClientCards;*/
	
	@Column(name = "FIRST_NAME")
	private String firstName;
	
	@Column(name = "MOBILE")
	private String mobileNumber;
   
	@Column(name = "BIRTH_DATE")
	private String birthDate;
	
	
	
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
	 * @return the institutionNumber
	 */
	public String getInstitutionNumber() {
		return institutionNumber;
	}

	/**
	 * @param institutionNumber the institutionNumber to set
	 */
	public void setInstitutionNumber(String institutionNumber) {
		this.institutionNumber = institutionNumber;
	}

	/**
	 * @return the rawClientCards
	 */
	/*public RawClientCards getRawClientCards() {
		return rawClientCards;
	}*/

	/**
	 * @param rawClientCards the rawClientCards to set
	 */
	/*public void setRawClientCards(RawClientCards rawClientCards) {
		this.rawClientCards = rawClientCards;
	}*/

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the mobileNumber
	 */
	public String getMobileNumber() {
		return mobileNumber;
	}

	/**
	 * @param mobileNumber the mobileNumber to set
	 */
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	
	
	
}
