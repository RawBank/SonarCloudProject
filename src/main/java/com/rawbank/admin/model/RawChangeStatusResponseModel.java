package com.rawbank.admin.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(value = { "message" })
public class RawChangeStatusResponseModel {

	private String message;
	

	private String error;
	
	private String errorDescription;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public RawChangeStatusResponseModel() {
		super();
	}

	public String getError() {
		return error;
	}

	@JsonProperty (value = "error", required = false)
	public void setError(String error) {
		this.error = error;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	@JsonProperty (value = "error_description", required = false)
	public void setErrorDescription(String erroDescription) {
		this.errorDescription = erroDescription;
	}

	public RawChangeStatusResponseModel(String message, String error, String erroDescription) {
		super();
		this.message = message;
		this.error = error;
		this.errorDescription = erroDescription;
	}

	
	@Override
	public String toString() {
		return "RawChangeStatusResponseModel [message=" + message + ", error=" + error + ", errorDescription="
				+ errorDescription + "]";
	}

	public RawChangeStatusResponseModel(String message) {
		super();
		this.message = message;
	}
	
	
	
	
}
