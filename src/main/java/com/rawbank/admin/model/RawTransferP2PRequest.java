package com.rawbank.admin.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;


@JacksonXmlRootElement(localName = "COMMAND")
public class RawTransferP2PRequest {

	@JacksonXmlProperty(localName = "CHANNEL")
	private String channel;

	@JacksonXmlProperty(localName = "TOKEN")
	private String token;

	@JacksonXmlProperty(localName = "REFERENCEID")
	private String referenceId;

	@JacksonXmlProperty(localName = "SRC_AGE")
	private String srcAgence;

	@JacksonXmlProperty(localName = "SRC_ACCOUNT")
	private String srcAccount;

	@JacksonXmlProperty(localName = "DST_AGE")
	private String dstAgence;

	@JacksonXmlProperty(localName = "DST_ACCOUNT")
	private String dstAccount;

	@JacksonXmlProperty(localName = "DEV")
	private String devise;

	@JacksonXmlProperty(localName = "AMOUNT")
	private Float amount;

	@JacksonXmlProperty(localName = "MOTIF")
	private String motif;

	@JacksonXmlProperty(localName = "REMITTANCE")
	private String remittance;

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

	public String getSrcAgence() {
		return srcAgence;
	}

	public void setSrcAgence(String srcAgence) {
		this.srcAgence = srcAgence;
	}

	public String getSrcAccount() {
		return srcAccount;
	}

	public void setSrcAccount(String srcAccount) {
		this.srcAccount = srcAccount;
	}

	public String getDstAgence() {
		return dstAgence;
	}

	public void setDstAgence(String dstAgence) {
		this.dstAgence = dstAgence;
	}

	public String getDstAccount() {
		return dstAccount;
	}

	public void setDstAccount(String dstAccount) {
		this.dstAccount = dstAccount;
	}

	public String getDevise() {
		return devise;
	}

	public void setDevise(String devise) {
		this.devise = devise;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
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

	@Override
	public String toString() {
		return "RawTransferP2PRequest [channel=" + channel + ", token=" + token + ", referenceId=" + referenceId
				+ ", srcAgence=" + srcAgence + ", srcAccount=" + srcAccount + ", dstAgence=" + dstAgence
				+ ", dstAccount=" + dstAccount + ", devise=" + devise + ", amount=" + amount + ", motif=" + motif
				+ ", remittance=" + remittance + "]";
	}
   
	
	//<COMMAND>
	//<CHANNEL>LOGIREF</CHANNEL>
	//<TOKEN>LOGIREF0</TOKEN>
	//<REFERENCEID>726579215812592418CM00</REFERENCEID>
	//<SRC_AGE>05101</SRC_AGE>
	//<SRC_ACCOUNT>10071092401</SRC_ACCOUNT>
	//<DST_AGE>05101</DST_AGE>
	//<DST_ACCOUNT>00172657902</DST_ACCOUNT>
	//<DEV>USD</DEV>
	//<AMOUNT>2.0000</AMOUNT>
	//<MOTIF>XXXXXX</MOTIF>
	//<REMITTANCE>XXXXXXX</REMITTANCE>
	//</COMMAND>

}


