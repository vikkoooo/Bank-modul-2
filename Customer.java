package lunvik8;

import java.util.ArrayList;
import java.util.List;

/**
 * Customer class that defines a Customer object. Part of D0018D, assignment 1.
 * 
 * @author Viktor Lundberg, lunvik-8
 * @version 2.0 (2021-mm-dd)
 */

public class Customer
{

	/**
	 * List of accounts that holds Customer objects own personal accounts. Accounts
	 * are created through createSavingsAccount method in BankLogic.java
	 */
	private List<Account> accountsList = new ArrayList<>();

	/**
	 * Instance variables
	 */
	private String name;
	private String surname;
	private String pNo;

	/**
	 * Constructor
	 * 
	 * @param name    (First name)
	 * @param surname (Last name)
	 * @param pNo     (Personal number)
	 */
	public Customer(String name, String surname, String pNo)
	{
		this.name = name;
		this.surname = surname;
		this.pNo = pNo;
	}

	/**
	 * Change name (First name)
	 * 
	 * @param name (First name)
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Change surname (Last name)
	 * 
	 * @param surname (Last name)
	 */
	public void setSurname(String surname)
	{
		this.surname = surname;
	}

	/**
	 * Get name (First name)
	 * 
	 * @return name (First name)
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Get surname (Last name)
	 * 
	 * @return surname (Last name)
	 */
	public String getSurname()
	{
		return surname;
	}

	/**
	 * Get pNo (Personal number)
	 * 
	 * @return pNo (Personal number)
	 */
	public String getpNo()
	{
		return pNo;
	}
	
	/**
	 * Method is used to get the list of Accounts
	 * 
	 * @return the ArrayList with Accounts
	 */
	public List<Account> getAccountsList()
	{
		return accountsList;
	}

	/**
	 * A toString representation of the customer
	 * 
	 * @return pNo + name + surname
	 */
	public String toStringCustomer()
	{
		return (pNo + " " + name + " " + surname);
	}



}
