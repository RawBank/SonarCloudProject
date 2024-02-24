package com.rawbank.admin.model;

public class RawPrepaidBulkApprovalParams {
	
	private String approuverStatusBlk;
	private String commentsLike;
	
	
	public RawPrepaidBulkApprovalParams() {
		super();
	}


	public RawPrepaidBulkApprovalParams(String approuverStatusBlk, String commentsLike) {
		super();
		this.approuverStatusBlk = approuverStatusBlk;
		this.commentsLike = commentsLike;
	}


	public String getApprouverStatusBlk() {
		return approuverStatusBlk;
	}


	public void setApprouverStatusBlk(String approuverStatusBlk) {
		this.approuverStatusBlk = approuverStatusBlk;
	}


	public String getCommentsLike() {
		return commentsLike;
	}


	public void setCommentsLike(String commentsLike) {
		this.commentsLike = commentsLike;
	}


	@Override
	public String toString() {
		return "RawPrepaidBulkApprovalParams [approuverStatusBlk=" + approuverStatusBlk + ", commentsLike="
				+ commentsLike + "]";
	}
	
	


	

}
