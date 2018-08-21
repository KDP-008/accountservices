package com.modulr.account.services.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountServiceExcpetion extends Exception {

	Logger logger = LoggerFactory.getLogger(AccountServiceExcpetion.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AccountServiceExcpetion(String errorMessage) {
		super(errorMessage);
	}

	public AccountServiceExcpetion(String errorMessage, Throwable cause) {
		super(errorMessage, cause);
	}
	
	public void printErrorMessage(String errorMessage) {
		logger.error("Error Message:"+errorMessage);
	} 

}
