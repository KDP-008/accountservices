package com.modulr.account.services.intrf;

import java.math.BigDecimal;

public interface AccountService {
	/**
	 * Method to be implemented for checking the balance
	 * @param accountNumber
	 * @return
	 */
	public BigDecimal checkBalance(String accountNumber);
	
	/**
	 * Method to be implemented for amount withdrawal
	 * @param accountNumber
	 * @param amount
	 * @return
	 */
	public boolean withdrawAmount(String accountNumber, BigDecimal amount);
}
