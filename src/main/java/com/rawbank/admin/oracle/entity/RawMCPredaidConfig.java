package com.rawbank.admin.oracle.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CSC_ADMIN_MC_PREPAID_CONFIG")
public class RawMCPredaidConfig {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	
	@Column(name = "TRUSTED_CREDIT_ACCOUNT_USD")
	private String trustedCreditQccount;

	public RawMCPredaidConfig() {
		super();
	}

	public RawMCPredaidConfig(int id, String trustedCreditQccount) {
		super();
		this.id = id;
		this.trustedCreditQccount = trustedCreditQccount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTrustedCreditQccount() {
		return trustedCreditQccount;
	}

	public void setTrustedCreditQccount(String trustedCreditQccount) {
		this.trustedCreditQccount = trustedCreditQccount;
	}

	@Override
	public String toString() {
		return "RawMCPredaidConfig [id=" + id + ", trustedCreditQccount=" + trustedCreditQccount + "]";
	}
	
	
	

}
