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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author krishna
 *
 */
@JsonPropertyOrder({ "id", "data" })
public class RawClientRequestModel implements Serializable{
	
	private static final long serialVersionUID = 1L;

	
	private RawAdminIdInfo rawAdminIdInfo;
	
	
	private RawClientData rawClientData;

	public RawClientRequestModel() {
		super();
	}

	/**
	 * @param rawAdminIdInfo
	 * @param rawClientData
	 */
	public RawClientRequestModel(RawAdminIdInfo rawAdminIdInfo, RawClientData rawClientData) {
		super();
		this.rawAdminIdInfo = rawAdminIdInfo;
		this.rawClientData = rawClientData;
	}

	/**
	 * @return the rawAdminIdInfo
	 */
	public RawAdminIdInfo getRawAdminIdInfo() {
		return rawAdminIdInfo;
	}

	/**
	 * @param rawAdminIdInfo the rawAdminIdInfo to set
	 */
	@JsonProperty(value = "id" , required = true)
	public void setRawAdminIdInfo(RawAdminIdInfo rawAdminIdInfo) {
		this.rawAdminIdInfo = rawAdminIdInfo;
	}

	/**
	 * @return the rawClientData
	 */
	public RawClientData getRawClientData() {
		return rawClientData;
	}

	/**
	 * @param rawClientData the rawClientData to set
	 */
	@JsonProperty(value = "data" , required = true)
	public void setRawClientData(RawClientData rawClientData) {
		this.rawClientData = rawClientData;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RawClientRequestModel [rawAdminIdInfo=");
		builder.append(rawAdminIdInfo);
		builder.append(", rawClientData=");
		builder.append(rawClientData);
		builder.append("]");
		return builder.toString();
	}
	
}
