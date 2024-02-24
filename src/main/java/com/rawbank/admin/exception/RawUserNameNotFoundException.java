/**
 * © Copyright 2021 Rawbank. All Rights Reserved.
 * 
 * Change Section:
 * Programmer			Date
 * Krishna				6/27/2022
 * CSC admin portal Project.
 * Initial Coding.
 * 
 * 
 * Created By:
 * 
 * @author krishna
 * @since Jun 27, 2022
 * 
 */
package com.rawbank.admin.exception;


import org.springframework.security.core.AuthenticationException;

public class RawUserNameNotFoundException extends AuthenticationException {
	
	private static final long serialVersionUID = 1L;

	public RawUserNameNotFoundException(String message) {
		super(message);
	}
	
	public RawUserNameNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
