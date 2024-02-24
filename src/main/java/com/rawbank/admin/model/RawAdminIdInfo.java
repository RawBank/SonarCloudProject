/**
 * © Copyright 2021 Rawbank. All Rights Reserved.
 * 
 * Change Section:
 * Programmer			Date
 * Krishna 				Sep 14, 2022
 * CSC admin Project Initial coding.
 * 
 * 
 * Created By:
 * @author krishna
 * @since Sep 14, 2022
 * 
 */
package com.rawbank.admin.model;

import java.io.Serializable;

/**
 * @author krishna
 *
 */
public class RawAdminIdInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	private String institutionNumber;
	private String clientNumber;
	
	
	/**
	 * @param institutionNumber
	 * @param clientNumber
	 */
	public RawAdminIdInfo(String institutionNumber, String clientNumber) {
		super();
		this.institutionNumber = institutionNumber;
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
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RawAdminIdInfo [institutionNumber=");
		builder.append(institutionNumber);
		builder.append(", clientNumber=");
		builder.append(clientNumber);
		builder.append("]");
		return builder.toString();
	}
	
	
}
