package com.rawbank.admin.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "scope", "access_token", "token_type", "expires_in", "refresh_token" })
@JsonIgnoreProperties(value = { "expires_at" })
public class RawTokenResponseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String[] scope;

	private String accessToken;

	private String tokenType;

	private String expiresIn;

	private String refreshToken;

	private LocalDateTime expiresAt;

	public String[] getScope() {
		return scope;
	}

	@JsonProperty(value = "scope")
	public void setScope(String[] scope) {
		this.scope = scope;
	}


	public String getAccessToken() {
		return accessToken;
	}
	@JsonProperty(value = "access_token")
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	
	public String getTokenType() {
		return tokenType;
	}
  
	@JsonProperty(value = "token_type")
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	
	public String getExpiresIn() {
		return expiresIn;
	}

	@JsonProperty(value = "expires_in")
	public void setExpiresIn(String expiresIn) {
		this.expiresIn = expiresIn;
	}


	
	public String getRefreshToken() {
		return refreshToken;
	}

	@JsonProperty(value = "refresh_token")
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	
	public LocalDateTime getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(LocalDateTime expiresAt) {
		this.expiresAt = expiresAt;
	}

	public RawTokenResponseModel() {
		super();
	}

	@Override
	public String toString() {
		return "RawTokenResponseModel [scope=" + Arrays.toString(scope) + ", accessToken=" + accessToken
				+ ", tokenType=" + tokenType + ", expiresIn=" + expiresIn + ", refreshToken=" + refreshToken
				+ ", expiresAt=" + expiresAt + "]";
	}

	

}
