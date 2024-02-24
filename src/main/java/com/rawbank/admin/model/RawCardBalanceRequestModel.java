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

import java.io.Serializable;

import com.rawbank.admin.config.RawAppConfig;

/**
 * @author jacquesk
 *
 */
public class RawCardBalanceRequestModel implements Serializable {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

private String institutionNumber;

	private String  cardNumber;
	
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
	@Override
	public String toString() {
		return "RawCardBalanceRequestModel [institutionNumber=" + institutionNumber + ", cardNumber=" + cardNumber
				+ "]";
	}
	public RawCardBalanceRequestModel() {
		super();
	}
	public RawCardBalanceRequestModel(String cardNumber) {
		super();
		this.institutionNumber = RawAppConfig.getCscinstitutionNumber();
		this.cardNumber = cardNumber;
	}
	
	
	
}
