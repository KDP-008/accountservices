package com.modulr.account.services.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;

public class ATMServiceImplTest { 
	
	@Test
	public void testIsValidAccount() {
		AccountServiceImpl as = new ATMServiceImpl();
		assertTrue(as.isValidAccount("01001"));
	}

	@Test
	public void testCheckBalance() {
		ATMServiceImpl atm = new ATMServiceImpl();
		assertEquals(BigDecimal.valueOf(0.0),atm.checkBalance("01003"));
	}

	@Test
	public void testWithdrawAmount() {
		ATMServiceImpl atm = new ATMServiceImpl();
		assertTrue(atm.withdrawAmount("01001", BigDecimal.valueOf(300)));
	}

}
