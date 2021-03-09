package lunvik8;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Account class that defines the properties of an Account. Part of D0018D,
 * assignment 2.
 * 
 * @author Viktor Lundberg, lunvik-8
 * @version 2.0 (2021-mm-dd)
 */

public abstract class Account
{
	/**
	 * Class variable. We want an unique accountId for the whole bank, not just for
	 * the customer.
	 */
	private static int nextAccountId = 1000;
	
	
	// transaktioner
	protected List<String> transactionsList = new ArrayList<>();

	/**
	 * Instance variables
	 */
	private int accountId;
	private double balance;
	private String type;

	/**
	 * Constructor
	 */
	public Account(String type)
	{
		nextAccountId++; // Increment class variable nextAccountId
		accountId = nextAccountId; // Then set our new account to that Id
		balance = 0;
		this.type = type;
	}



	/**
	 * Abstract method withdrawal to be defined in CreditAccount and SavingsAccount
	 * 
	 * @param amount to withdrawal as double
	 * @return true if successful, false if failed
	 */
	abstract boolean withdrawal(double amount);
	
	
	/**
	 * Calculates the interest of the account
	 * 
	 * @return the interest
	 */
	abstract double calculateInterest();
	
	
	/**
	 * Deposit into account
	 * 
	 * @param amount to deposit as double
	 */
	public void deposit(double amount)
	{
		balance += amount;
		
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter prefFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formatedTime = currentTime.format(prefFormat);
		transactionsList.add(formatedTime + " " + amount + " kr " + "Saldo: " + getBalance() + " kr");
		
		
		
	}
	
	
	// BEHÖVER JAG DENNA ELLER GÅR DET LÖSA PÅ NÅTT ANNAT SÄTT
	/**
	 * @param balance the balance to set
	 */
	public void setBalance(double balance)
	{
		this.balance = balance;
	}

	/**
	 * getAccountId
	 * 
	 * @return the accountId
	 */
	public int getAccountId()
	{
		return accountId;
	}

	/**
	 * Check balance
	 * 
	 * @return the balance
	 */
	public double getBalance()
	{
		return balance;
	}

	/**
	 * Getter for type of account
	 * 
	 * @return the account type as String. "Sparkonto" or "Kreditkonto"
	 */
	public String getType()
	{
		return type;
	}

	/**
	 * Abstract toString representation of the account including rate as %.
	 * 
	 * @return accountId + balance + type + rate
	 */
	abstract String toStringWithRate();

	/**
	 * A toString representation of the account without rate.
	 * 
	 * @return accountId + balance + type
	 */
	public String toStringWithoutRate()
	{
		return (accountId + " " + balance + " kr " + getType() + " ");
	}



}
