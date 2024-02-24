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
* @since 16 sept. 2022
* 
 */

package com.rawbank.admin.model;

/**
 * @author jacquesk
 *
 */
public class RawClientDetailsModel {
  
	private String  clientNumber;
	
	private String birthDate;
	
	private  Integer clientCountry;
	
	private  String fathersName;
	
	private String firstName;
	
	private String idNumber;
	
	private String lastName;
	
	private String mobile1;
	
	private String mobile2;
	
	private Integer nationality;
	
	private String shortName ;
	
	private String telPrivate;
	
	private String telWork;

	public String getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public Integer getClientCountry() {
		return clientCountry;
	}

	public void setClientCountry(Integer clientCountry) {
		this.clientCountry = clientCountry;
	}

	public String getFathersName() {
		return fathersName;
	}

	public void setFathersName(String fathersName) {
		this.fathersName = fathersName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobile1() {
		return mobile1;
	}

	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}

	public String getMobile2() {
		return mobile2;
	}

	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}

	public Integer getNationality() {
		return nationality;
	}

	public void setNationality(Integer nationality) {
		this.nationality = nationality;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getTelPrivate() {
		return telPrivate;
	}

	public void setTelPrivate(String telPrivate) {
		this.telPrivate = telPrivate;
	}

	public String getTelWork() {
		return telWork;
	}

	public void setTelWork(String telWork) {
		this.telWork = telWork;
	}

	@Override
	public String toString() {
		return "RawClientDetailsModel [clientNumber=" + clientNumber + ", birthDate=" + birthDate + ", clientCountry="
				+ clientCountry + ", fathersName=" + fathersName + ", firstName=" + firstName + ", idNumber=" + idNumber
				+ ", lastName=" + lastName + ", mobile1=" + mobile1 + ", mobile2=" + mobile2 + ", nationality="
				+ nationality + ", shortName=" + shortName + ", telPrivate=" + telPrivate + ", telWork=" + telWork
				+ "]";
	}



	public RawClientDetailsModel() {
		super();
	}
	
	
	
}


