package com.rawbank.admin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "COMMAND")
public class RawA2APojoRequestModel {

	@JacksonXmlProperty(localName = "CHANNEL")
	private String channel;

	@JacksonXmlProperty(localName = "TOKEN")
	private String token;

	@JacksonXmlProperty(localName = "REFERENCEID")
	private String referenceId;

	@JacksonXmlProperty(localName = "SRC_AGE")
	private String srcAge;

	@JacksonXmlProperty(localName = "SRC_ACCOUNT")
	private String srcAccount;

	@JacksonXmlProperty(localName = "DST_AGE")
	private String dstAge;

	@JacksonXmlProperty(localName = "DST_ACCOUNT")
	private String dstAccount;

	@JacksonXmlProperty(localName = "DEV")
	private String dev;

	@JacksonXmlProperty(localName = "AMOUNT")
	private Integer amount;

	@JacksonXmlProperty(localName = "MOTIF")
	private String motif;

	@JacksonXmlProperty(localName = "REMITTANCE")
	private String remittance;

	@JsonIgnore
	private String msisdn;

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}


	
	
	public String getSrcAge() {
		return srcAge;
	}

	public void setSrcAge(String srcAge) {
		this.srcAge = srcAge;
	}

	public String getSrcAccount() {
		return srcAccount;
	}

	public void setSrcAccount(String srcAccount) {
		this.srcAccount = srcAccount;
	}

	public String getDstAge() {
		return dstAge;
	}

	public void setDstAge(String dstAge) {
		this.dstAge = dstAge;
	}

	public String getDstAccount() {
		return dstAccount;
	}

	public void setDstAccount(String dstAccount) {
		this.dstAccount = dstAccount;
	}

	public String getDev() {
		return dev;
	}

	public void setDev(String dev) {
		this.dev = dev;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getMotif() {
		return motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}

	public String getRemittance() {
		return remittance;
	}

	public void setRemittance(String remittance) {
		this.remittance = remittance;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}




	@Override
	public String toString() {
		return "RawA2APojoRequestModel [channel=" + channel + ", token=" + token + ", referenceId=" + referenceId
				+ ", srcAge=" + srcAge + ", srcAccount=" + srcAccount + ", dstAge=" + dstAge + ", dstAccount="
				+ dstAccount + ", dev=" + dev + ", amount=" + amount + ", motif=" + motif + ", remittance=" + remittance
				+ ", msisdn=" + msisdn + "]";
	}

	public RawA2APojoRequestModel() {
		super();
	}

}
