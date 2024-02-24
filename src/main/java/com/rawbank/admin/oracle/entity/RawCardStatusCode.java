package com.rawbank.admin.oracle.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CSC_ADMIN_CARD_STATUS_CODE" /*, schema = "JACQUESK"*/)
public class RawCardStatusCode implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NUMCODE", nullable = true, updatable = true, insertable = true, length = 5)
	private String numCode;

	@Column(name = "DESCRIPTION", nullable = true, updatable = true, insertable = true, length = 50)
	private String description;

	@Column(name = "STATUS", nullable = true, updatable = true, insertable = true, length = 2)
	private String status;
	// TIMESTAMP



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumCode() {
		return numCode;
	}

	public void setNumCode(String numCode) {
		this.numCode = numCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}




	public RawCardStatusCode(Long id, String numCode, String description, String status) {
		super();
		this.id = id;
		this.numCode = numCode;
		this.description = description;
		this.status = status;
		
	}
	

	@Override
	public String toString() {
		return "RawCardStatusCode [id=" + id + ", numCode=" + numCode + ", description=" + description + ", status="
				+ status + "]";
	}

	public RawCardStatusCode() {
		super();
	}

}
