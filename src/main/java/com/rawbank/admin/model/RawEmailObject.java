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
* @since 24 nov. 2022
* 
 */

package com.rawbank.admin.model;

;

/**
 * @author jacquesk
 *
 */
public class RawEmailObject {

	private String setTo;
	private String setCC;
	private String userName;

	private String rr;

	private String comment;

	public String getSetTo() {
		return setTo;
	}

	public void setSetTo(String setTo) {
		this.setTo = setTo;
	}

	public String getSetCC() {
		return setCC;
	}

	public void setSetCC(String setCC) {
		this.setCC = setCC;
	}

	public String getRr() {
		return rr;
	}

	public void setRr(String rr) {
		this.rr = rr;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "RawEmailObject [setTo=" + setTo + ", setCC=" + setCC + ", userName=" + userName + ", rr=" + rr
				+ ", comment=" + comment + "]";
	}

	

	
	
}
