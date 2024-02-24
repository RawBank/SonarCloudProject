package com.rawbank.admin.model;

import java.io.Serializable;


public class RawTokenRequestModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String grantType;



	public String getGrantType() {
		return grantType;
	}



	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}



	public RawTokenRequestModel() {
		super();
	}

}
