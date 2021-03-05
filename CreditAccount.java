package lunvik8;

/**
 * CreditAccount class that defines a CreditAccount as an extension of Account.
 * Part of D0018D, assignment 2.
 * 
 * @author Viktor Lundberg, lunvik-8
 * @version 2.0 (2021-mm-dd)
 */

public class CreditAccount extends Account
{
	
	// instansvariabler
	int creditLimit;
	double rate;
	double debtRate;
	
	
	// konstruktor
	public CreditAccount ()
	{
			super ("Kreditkonto");
			creditLimit = 5000;
			rate = 0.5;
			debtRate = 7;
	}
	
	// ta ut
	public void withdrawal(double amount)
	{
		balance -= amount;
	}
	

}
