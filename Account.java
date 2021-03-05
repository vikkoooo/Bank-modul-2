package lunvik8;

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

	/**
	 * Instance variables
	 */
	private int accountId;
	protected double balance;
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

	
	//Deposit
	public void deposit(double amount)
	{
		balance += amount;
	}
	
	
	//Withdrawal ABSTRACT
	abstract void withdrawal(double amount);
	

	
	

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
	 * @param accountId for the account we want to check
	 * @return the balance
	 */
	public double getBalance(int accountId)
	{
		return balance;
	}


	
	/**
	 * A toString representation of the account without rate.
	 * 
	 * @return accountId + balance + type
	 */
	@Override
	public String toString()
	{
		return (accountId + " " + balance + " kr " + type + " ");
	}






}
