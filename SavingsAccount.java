package lunvik8;

/**
 * SavingsAccount class that defines a SavingsAccount as an extension of
 * Account. Part of D0018D, assignment 2.
 * 
 * @author Viktor Lundberg, lunvik-8
 * @version 2.0 (2021-mm-dd)
 */

public class SavingsAccount extends Account
{
	// Instansvariabel
	private double rate;
	private int availableWithdrawals;

	// konstruktor
	public SavingsAccount()
	{
		super("Sparkonto");
		rate = 1;
		availableWithdrawals = 1;
	}

	/**
	 * Withdrawal from account
	 * 
	 * @param amount to withdrawal as double
	 */
	public void withdrawal(double amount)
	{
		balance -= amount;
		availableWithdrawals--;
	}

	/**
	 * Calculate interest (SEK)
	 * 
	 * @return the interest as double
	 */
	public double calculateInterest(int accountId)
	{
		double interest = getBalance(accountId) * rate / 100;
		return interest;
	}

	/**
	 * @return the availableWithdrawals
	 */
	public int getAvailableWithdrawals()
	{
		return availableWithdrawals;
	}

}
