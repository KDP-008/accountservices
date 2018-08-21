package com.modulr.account.services.business;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.modulr.account.services.exception.AccountServiceExcpetion;

public class ATMServiceImpl extends AccountServiceImpl {
	
	Logger logger = LoggerFactory.getLogger(ATMServiceImpl.class);
	
	/**
	 * Method to check the balance in the requested account
	 * @param accountNumber
	 * @return boolean
	 */
	public synchronized BigDecimal checkBalance(String accountNumber) {
		logger.info("Request received for account:"+ accountNumber);
		BigDecimal balance = BigDecimal.ZERO;
		boolean isValidAccount = false;
		try {
			isValidAccount = isValidAccount(accountNumber);
			if (isValidAccount) {
				balance = ATMServiceImpl.existingAccounts
							.stream()
							.filter(a -> a.getAccountNumber().equals(accountNumber)).
							findFirst().get().getBalance();
			} else {
				throw new AccountServiceExcpetion("Invalid Account!");
			}
		} catch (AccountServiceExcpetion ase) {
			ase.printErrorMessage(ase.getMessage());
		}
		return balance;
	}

	/**
	 * Method to withdraw the amount from the given account
	 * @param accountnumber
	 * @param amount
	 * 
	 */
	public synchronized boolean withdrawAmount(String accountNumber, BigDecimal amount) {
		boolean success = false;
		try {
			BigDecimal balance = checkBalance(accountNumber);
			
			if (balance.compareTo(amount)>=0){
				logger.info("Amount withdrawn:"+amount);
				AccountServiceImpl.updateAccountBalance(accountNumber, amount);
				success = true;
			} else {
				success = false;
				throw new AccountServiceExcpetion("Not Enough Amount to withdraw!");
			}
		} catch (AccountServiceExcpetion ase) {
			success = false;
			ase.printErrorMessage(ase.getMessage());
		}
		return success;
	}

}
