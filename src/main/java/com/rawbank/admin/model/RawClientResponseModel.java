/**
 * © Copyright 2021 Rawbank. All Rights Reserved.
 * 
 * Change Section:
 * Programmer			Date
 * Krishna 				Sep 13, 2022
 * CSC admin Project Initial coding.
 * 
 * 
 * Created By:
 * @author krishna
 * @since Sep 13, 2022
 * 
 */
package com.rawbank.admin.model;

import java.io.Serializable;

/**
 * @author krishna
 *
 */
public class RawClientResponseModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String clientNumber;
	private String birthDate;
	private String clientCountry;
	private String fathersName;
	private String firstName;
	private String idNumber;
	private String lastName;
	private String mobile1;
	private String mobile2;
	private String nationality;
	private String shortName;
	private String telPrivate;
	private String telWork;
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
	 * @return the birthDate
	 */
	public String getBirthDate() {
		return birthDate;
	}
	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	/**
	 * @return the clientCountry
	 */
	public String getClientCountry() {
		return clientCountry;
	}
	/**
	 * @param clientCountry the clientCountry to set
	 */
	public void setClientCountry(String clientCountry) {
		this.clientCountry = clientCountry;
	}
	/**
	 * @return the fathersName
	 */
	public String getFathersName() {
		return fathersName;
	}
	/**
	 * @param fathersName the fathersName to set
	 */
	public void setFathersName(String fathersName) {
		this.fathersName = fathersName;
	}
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
	 * @return the idNumber
	 */
	public String getIdNumber() {
		return idNumber;
	}
	/**
	 * @param idNumber the idNumber to set
	 */
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the mobile1
	 */
	public String getMobile1() {
		return mobile1;
	}
	/**
	 * @param mobile1 the mobile1 to set
	 */
	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}
	/**
	 * @return the mobile2
	 */
	public String getMobile2() {
		return mobile2;
	}
	/**
	 * @param mobile2 the mobile2 to set
	 */
	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}
	/**
	 * @return the nationality
	 */
	public String getNationality() {
		return nationality;
	}
	/**
	 * @param nationality the nationality to set
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	/**
	 * @return the shortName
	 */
	public String getShortName() {
		return shortName;
	}
	/**
	 * @param shortName the shortName to set
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	/**
	 * @return the telPrivate
	 */
	public String getTelPrivate() {
		return telPrivate;
	}
	/**
	 * @param telPrivate the telPrivate to set
	 */
	public void setTelPrivate(String telPrivate) {
		this.telPrivate = telPrivate;
	}
	/**
	 * @return the telWork
	 */
	public String getTelWork() {
		return telWork;
	}
	/**
	 * @param telWork the telWork to set
	 */
	public void setTelWork(String telWork) {
		this.telWork = telWork;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RawGetClientResponseModel [clientNumber=");
		builder.append(clientNumber);
		builder.append(", birthDate=");
		builder.append(birthDate);
		builder.append(", clientCountry=");
		builder.append(clientCountry);
		builder.append(", fathersName=");
		builder.append(fathersName);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", idNumber=");
		builder.append(idNumber);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", mobile1=");
		builder.append(mobile1);
		builder.append(", mobile2=");
		builder.append(mobile2);
		builder.append(", nationality=");
		builder.append(nationality);
		builder.append(", shortName=");
		builder.append(shortName);
		builder.append(", telPrivate=");
		builder.append(telPrivate);
		builder.append(", telWork=");
		builder.append(telWork);
		builder.append("]");
		return builder.toString();
	}

}
