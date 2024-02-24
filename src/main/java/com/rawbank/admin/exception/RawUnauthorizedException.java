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

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class RawUnauthorizedException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public RawUnauthorizedException(String message) {
		super(message);
	}
	
	public RawUnauthorizedException(String message, Throwable cause) {
		super(message, cause);
	}

}