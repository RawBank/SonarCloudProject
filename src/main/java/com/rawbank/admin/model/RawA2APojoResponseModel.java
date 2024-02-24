package com.rawbank.admin.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;


//@Data @NoArgsConstructor @AllArgsConstructor
@JacksonXmlRootElement (localName ="COMMAND" )
public class RawA2APojoResponseModel {


	@JacksonXmlProperty(localName = "REFERENCEID")
	private String referenceid;
	
	@JacksonXmlProperty(localName = "AGE")
	private String age;
	
	@JacksonXmlProperty(localName = "ACCOUNT")
	private String account;
	
	@JacksonXmlProperty(localName = "DEV")
	private String devise;
	
	
	@JacksonXmlProperty(localName = "AMOUNT")
	private Float amount;
	
	@JacksonXmlProperty(localName = "STATUS")
	private String status;
	
	@JacksonXmlProperty(localName = "MSGDESC")
	private String msgdesc;
	
	@JacksonXmlProperty(localName = "BNKREFNO")
	private String bnkrefno;
	
	
	private String receiptfilename;


	public String getReferenceid() {
		return referenceid;
	}


	public void setReferenceid(String referenceid) {
		this.referenceid = referenceid;
	}


	public String getAge() {
		return age;
	}


	public void setAge(String age) {
		this.age = age;
	}


	public String getAccount() {
		return account;
	}


	public void setAccount(String account) {
		this.account = account;
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


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getMsgdesc() {
		return msgdesc;
	}


	public void setMsgdesc(String msgdesc) {
		this.msgdesc = msgdesc;
	}


	public String getBnkrefno() {
		return bnkrefno;
	}


	public void setBnkrefno(String bnkrefno) {
		this.bnkrefno = bnkrefno;
	}


	public String getReceiptfilename() {
		return receiptfilename;
	}


	public void setReceiptfilename(String receiptfilename) {
		this.receiptfilename = receiptfilename;
	}





	@Override
	public String toString() {
		return "RawA2APojoResponseModel [referenceid=" + referenceid + ", age=" + age + ", account=" + account + ", devise="
				+ devise + ", amount=" + amount + ", status=" + status + ", msgdesc=" + msgdesc + ", bnkrefno="
				+ bnkrefno + ", receiptfilename=" + receiptfilename + "]";
	}


	public RawA2APojoResponseModel() {
		super();
	}


	
}



