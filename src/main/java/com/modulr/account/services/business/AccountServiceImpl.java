package com.modulr.account.services.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.modulr.account.services.intrf.AccountService;
import com.modulr.account.services.model.Account;

public abstract class AccountServiceImpl implements AccountService {
	
	Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
	
	public static List<Account> existingAccounts = new ArrayList<>();
	
	static {
		Account a1 = new Account();
		a1.setAccountNumber("01001");
		a1.setBalance(BigDecimal.valueOf(2738.59));
		
		Account a2 = new Account();
		a2.setAccountNumber("01002");
		a2.setBalance(BigDecimal.valueOf(23.00));
		
		Account a3 = new Account();
		a3.setAccountNumber("01003");
		a3.setBalance(BigDecimal.valueOf(0.00));
		
		existingAccounts.add(a1);
		existingAccounts.add(a2);
		existingAccounts.add(a3);
	}
	
	/**
	 * Method to check if the account exists
	 * @param accountNumber
	 * @return
	 */
	public boolean isValidAccount (String accountNumber) {
		logger.info("Validating account: "+accountNumber);
		boolean isAccountPresent = existingAccounts
									.stream()
									.filter(e -> e.getAccountNumber()!=null && accountNumber.equals(e.getAccountNumber()))
									.findAny()
									.isPresent();
		logger.info("Account Found: "+isAccountPresent);							
		return true;
	}

	/**
	 * Method to update the balance in the account
	 * @param accountNumber
	 * @param amount
	 */
	public static synchronized void updateAccountBalance(String accountNumber, BigDecimal amount) {
		existingAccounts.stream()
			.filter(a -> a.getAccountNumber().equals(accountNumber))
			.findAny().ifPresent(c-> c.setBalance(c.getBalance().subtract(amount)));
	}

}
