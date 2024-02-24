/**
 * Copyright 2022 Rawbank. All Rights Reserved.
 * 
 * Change Section: 
 * Programmer 			Date
 * Krishna				6/29/2022
 * CSC admin portal Project.
 * Initial Coding.
 * 
 * 
 * Created By:
 * 
 * @author krishna
 * @since Jun 29, 2022
 * 
 */
package com.rawbank.admin.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "userName", "id", "firstName", "lastName", "email", "tokenjwt", "status",
		"numberOfAccesswithbadpwd", "roles" })
@JsonIgnoreProperties(value = { "password" })
public class RawLoginResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userName;
	private Integer id;
	private String firstName;
	private String lastName;
	private String email;
	private String tokenjwt;

	private String password;

	private Boolean status;

	private Integer numberOfAccesswithbadpwd;

	private List<RawRoles> roles;
	
	public RawLoginResponse() {
		super();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	@JsonProperty("userName")
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	@JsonProperty("firstName")
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	@JsonProperty("lastName")
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	@JsonProperty("email")
	public void setEmail(String email) {
		this.email = email;
	}

	public String getTokenjwt() {
		return tokenjwt;
	}

	@JsonProperty("tokenjwt")
	public void setTokenjwt(String tokenjwt) {
		this.tokenjwt = tokenjwt;
	}

	public Boolean getStatus() {
		return status;
	}

	@JsonProperty("status")
	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Integer getNumberOfAccesswithbadpwd() {
		return numberOfAccesswithbadpwd;
	}

	@JsonProperty("numberOfAccesswithbadpwd")
	public void setNumberOfAccesswithbadpwd(Integer numberOfAccesswithbadpwd) {
		this.numberOfAccesswithbadpwd = numberOfAccesswithbadpwd;
	}

	public List<RawRoles> getRoles() {
		return roles;
	}

	@JsonProperty("roles")
	public void setRoles(List<RawRoles> roles) {
		this.roles = roles;
	}


	@Override
	public int hashCode() {
		return Objects.hash(email, firstName, id, lastName, numberOfAccesswithbadpwd, password, roles, status, tokenjwt,
				userName);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RawLoginResponse [userName=");
		builder.append(userName);
		builder.append(", id=");
		builder.append(id);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", email=");
		builder.append(email);
		builder.append(", tokenjwt=");
		builder.append(tokenjwt);
		builder.append(", password=");
		builder.append(password);
		builder.append(", status=");
		builder.append(status);
		builder.append(", numberOfAccesswithbadpwd=");
		builder.append(numberOfAccesswithbadpwd);
		builder.append(", roles=");
		builder.append(roles);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RawLoginResponse other = (RawLoginResponse) obj;
		return Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(id, other.id) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(numberOfAccesswithbadpwd, other.numberOfAccesswithbadpwd)
				&& Objects.equals(password, other.password) && Objects.equals(roles, other.roles)
				&& Objects.equals(status, other.status) && Objects.equals(tokenjwt, other.tokenjwt)
				&& Objects.equals(userName, other.userName);
	}

}