/**
 * © Copyright 2021 Rawbank. All Rights Reserved.
 * 
 * Change Section:
 * Programmer			Date
 * Krishna				6/29/2022
 * CSC admin portal Project.
 * Initial Coding.
 * 
 * 
 * 
 * @author krishna
 * @since Jun 29, 2022
 * 
 */
package com.rawbank.admin.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author krishna
 *
 */
public class RawRoles implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer roleId;
	private String roleName;
	private String description;
	private String application;

	public Integer getRoleId() {
		return roleId;
	}

	@JsonProperty("roleId")
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	@JsonProperty("roleName")
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return description;
	}

	@JsonProperty("description")
	public void setDescription(String description) {
		this.description = description;
	}

	public String getApplication() {
		return application;
	}

	@JsonProperty("application")
	public void setApplication(String application) {
		this.application = application;
	}

	public RawRoles() {
		super();
	}

	public RawRoles(Integer roleId, String roleName, String description, String application) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.description = description;
		this.application = application;
	}

	@Override
	public String toString() {
		return "roles [roleId=" + roleId + ", roleName=" + roleName + ", description=" + description
				+ ", application=" + application + "]";
	}
}
