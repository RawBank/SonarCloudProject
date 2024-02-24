package com.rawbank.admin.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "clearedTransactions", "pendingTransactions" })
public class RawCardActivityResponseModel {

	private List<Transactions> clearedTransactions;

	private List<Transactions> pendingTransactions;

	public List<Transactions> getClearedTransactions() {
		return clearedTransactions;
	}

	@JsonProperty("clearedTransactions")
	public void setClearedTransactions(List<Transactions> clearedTransactions) {
		this.clearedTransactions = clearedTransactions;
	}

	public List<Transactions> getPendingTransactions() {
		return pendingTransactions;
	}

	@JsonProperty("pendingTransactions")
	public void setPendingTransactions(List<Transactions> pendingTransactions) {
		this.pendingTransactions = pendingTransactions;
	}

	@Override
	public String toString() {
		return "RawCardActivityResponseModel [clearedTransactions=" + clearedTransactions + ", pendingTransactions="
				+ pendingTransactions + "]";
	}

}
