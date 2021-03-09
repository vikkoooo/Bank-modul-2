package lunvik8;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * CreditAccount class that defines a CreditAccount as an extension of Account.
 * Part of D0018D, assignment 2.
 * 
 * @author Viktor Lundberg, lunvik-8
 * @version 2.0 (2021-mm-dd)
 */

public class CreditAccount extends Account
{
	/**
	 * Instance variables
	 */
	int creditLimit;
	double rate;
	double debtRate;

	/**
	 * Constructor
	 */
	public CreditAccount()
	{
		super("Kreditkonto");
		creditLimit = 5000;
		rate = 0.5;
		debtRate = 7;
	}

	/**
	 * Withdrawal from account
	 * 
	 * @param amount to withdrawal as double
	 * @return true if successful, false if failed
	 */
	// TODO: withdrawal måste skrivas om
	public boolean withdrawal(double amount)
	{
		double currentBalance = getBalance();
		if (currentBalance + creditLimit >= amount && amount >= 0)
		{
			setBalance(currentBalance -= amount);
			
			
			LocalDateTime currentTime = LocalDateTime.now();
			DateTimeFormatter prefFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String formatedTime = currentTime.format(prefFormat);
			transactionsList.add(formatedTime + " -" + amount + " kr " + "Saldo: " + getBalance() + " kr");
			
			return true;
		} else
		{
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
		double currentRate;
		if (getBalance() >= 0)
		{
			currentRate = rate;
		} else
		{
			currentRate = debtRate;
		}
		double interest = getBalance() * currentRate / 100;
		return interest;
	}

	/**
	 * A toString representation of the account including rate as %.
	 * 
	 * @return accountId + balance + type + rate
	 */
	public String toStringWithRate()
	{
		double currentRate;
		if (getBalance() >= 0)
		{
			currentRate = rate;
		} else
		{
			currentRate = debtRate;
		}
		return (getAccountId() + " " + getBalance() + " kr " + getType() + " " + currentRate + " %");
	}

}
