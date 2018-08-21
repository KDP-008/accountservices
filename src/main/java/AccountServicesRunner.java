import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.modulr.account.services.business.ATMServiceImpl;
import com.modulr.account.services.exception.AccountServiceExcpetion;

/**
 * Class to serve as an interface for ATM functions
 * To test the ATM functionality, please run this class
 * @author kdp
 *
 */
public class AccountServicesRunner {

	private static final Logger logger = LoggerFactory.getLogger(AccountServicesRunner.class);
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String ar[]) throws AccountServiceExcpetion {
		//Assumptions: User has a valid card and is about to perform action
		// Action can be : 
		//1. Check Balance
		//2. Withdraw custom amount in multiples of 5
		performOperation();
		
	}

	private static void performOperation() throws AccountServiceExcpetion {
		displayUserMessage();
		String acNo = null;
		try {
			ATMServiceImpl atm = new ATMServiceImpl();
			int userOption = Integer.parseInt(br.readLine());
			if (userOption == 1 || userOption == 2) {
				logger.info("Please input your account number.");
				acNo = br.readLine();
				if (userOption==1) {
					logger.info("Balance in your account: £"+atm.checkBalance(acNo));
					presentUserOption();
				} else if (userOption == 2) {
					logger.info("Please enter the amount to be withdrawn in a multiple of 5 ");
					int amount = Integer.parseInt(br.readLine());
					if (amount%5==0) {
						if (atm.withdrawAmount(acNo,BigDecimal.valueOf(amount)))
							logger.info("Please take your amount!");
						else
							logger.info("Could not process!");
							
					} else {
						logger.error("Please enter the amount to be withdrawn in a multiple of 5 ");
					}
					presentUserOption();
			}
			} else {
				throw new AccountServiceExcpetion("Please select a valid option.");
			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		
	}

	private static void displayUserMessage() {
		logger.info("Welcome User!");
		logger.info("Please select your option from the list below:");
		logger.info("1. Check Balance\n2. Withdraw Money");
	}
	
	private static void presentUserOption() throws IOException, AccountServiceExcpetion {
		logger.info("Do you want to continue? (Y / N)");
		String selectedOp = br.readLine();
		if (selectedOp!=null && selectedOp.equalsIgnoreCase("Y")) {
			performOperation();
		}
	}
}
