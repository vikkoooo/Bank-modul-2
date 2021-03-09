package lunvik8;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * SavingsAccount class that defines a SavingsAccount as an extension of
 * Account. Part of D0018D, assignment 2.
 * 
 * @author Viktor Lundberg, lunvik-8
 * @version 2.0 (2021-mm-dd)
 */

public class SavingsAccount extends Account
{
	/**
	 * Instance variables
	 */
	private double rate;
	private int availableFreeWithdrawals;
	private double debtRate;

	/**
	 * Constructor
	 */
	public SavingsAccount()
	{
		super("Sparkonto");
		rate = 1;
		availableFreeWithdrawals = 1;
		debtRate = 2;
	}

	/**
	 * Withdrawal from account
	 * 
	 * @param amount to withdrawal as double
	 * @return true if successful, false if failed
	 */
	public boolean withdrawal(double amount)
	{
		double withdrawalInterest = amount * debtRate / 100;
		double currentBalance = getBalance();
				
		if (currentBalance >= amount && amount >= 0 && availableFreeWithdrawals > 0)
		{
			setBalance (currentBalance -= amount);
			availableFreeWithdrawals--;
			
			LocalDateTime currentTime = LocalDateTime.now();
			DateTimeFormatter prefFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String formatedTime = currentTime.format(prefFormat);
			transactionsList.add(formatedTime + " -" + amount + " kr " + "Saldo: " + getBalance() + " kr");
			
			
			
			
			return true;
		} 
		else if (currentBalance - withdrawalInterest >= amount && amount >= 0 && availableFreeWithdrawals <= 0)
		{
			setBalance (currentBalance -= (amount + withdrawalInterest));
			
			
			LocalDateTime currentTime = LocalDateTime.now();
			DateTimeFormatter prefFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String formatedTime = currentTime.format(prefFormat);
			transactionsList.add(formatedTime + " -" + amount + " kr " + "Saldo: " + getBalance() + " kr");
			
			
			
			
			
			
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Calculate interest (SEK)
	 * 
	 * @return the interest as double
	 */
	public double calculateInterest()
	{
		double interest = getBalance() * rate / 100;
		return interest;
	}

	
	/**
	 * A toString representation of the account including rate as %.
	 * 
	 * @return accountId + balance + type + rate
	 */
	public String toStringWithRate()
	{
		return (getAccountId() + " " + getBalance() + " kr " + getType() + " " + rate + " %");
	}

}
