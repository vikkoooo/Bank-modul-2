package lunvik8;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * Basic GUI class that implements interface for bank. Part of D0018D,
 * assignment 3.
 * 
 * @author Viktor Lundberg, lunvik-8
 * @version 1.1 (2021-04-13)
 */

public class GUI extends JFrame
{
	/**
	 * Main method to start the GUI
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		// Driver code
		JFrame frame = new GUI();
	}

	// To reach an instance of BankLogic. Initialized in constructor.
	BankLogic bank;

	// Window settings
	private static final int HEIGHT = 400;
	private static final int WEST_WIDTH = 240;
	private static final int CENTER_WIDTH = 240;
	private static final int EAST_WIDTH = 240;

	// Panels
	JPanel northPanel, westPanel, centerPanel, eastPanel, southPanel;

	// Components in north panel
	JLabel currentPage;

	// Components in west panel
	JButton home, viewCustomers, createCustomer, deleteCustomer, changeCustomerName, createSavingsAccount,
			createCreditAccount, viewCustomerAccounts, findAccount, deposit, withdrawal, closeAccount, viewTransactions;

	// Components in center panel
	JTextField field1, field2, field3;
	TitledBorder field1border, field2border, field3border;
	JButton okCreateCustomer, okDeleteCustomer, okChangeCustomerName, okCreateSavingsAccount, okCreateCreditAccount,
			okViewCustomerAccounts, okFindAccount, okDeposit, okWithdrawal, okCloseAccount, okViewTransactions;

	// Components in east panel
	JList displayList;

	/**
	 * Constructor
	 */
	public GUI()
	{
		bank = new BankLogic(); // Initialize a new instance of BankLogic
		this.setLayout(new BorderLayout()); // Use BorderLayout

		// Build panels
		buildMenu();
		buildNorth();
		buildWest();
		buildCenter();
		buildEast();
		buildSouth();

		// Frame settings
		this.setTitle("Bank");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	/**
	 * Menu for file handling to be implemented in next version of program.
	 */
	private void buildMenu()
	{
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		// File menu
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		JMenu importMenu = new JMenu("Import ...");
		fileMenu.add(importMenu);
		JMenu exportMenu = new JMenu("Export ...");
		fileMenu.add(exportMenu);

		// Import sub menu
		JMenuItem customerImport = new JMenuItem("Customer with accounts");
		importMenu.add(customerImport);

		// Export sub menu
		JMenuItem customerExport = new JMenuItem("Customer with accounts");
		exportMenu.add(customerExport);
		JMenuItem transactionItem = new JMenuItem("Transactions for account");
		exportMenu.add(transactionItem);
	}

	/**
	 * North center panel, display current page
	 */
	private void buildNorth()
	{
		northPanel = new JPanel();
		this.add(northPanel, BorderLayout.NORTH);

		// Displays current page
		currentPage = new JLabel("Home"); // Start at page "Home".
		northPanel.add(currentPage);
		northPanel.setVisible(true);
	}

	/**
	 * West panel. Menu with buttons, all our functions.
	 */
	private void buildWest()
	{
		westPanel = new JPanel();
		westPanel.setLayout(new GridLayout(14, 1)); // GridLayout inside this panel to keep buttons aligned.
		westPanel.setPreferredSize(new Dimension(WEST_WIDTH, HEIGHT));
		this.add(westPanel, BorderLayout.WEST);

		// ActionListener for menu
		MenuListener menuListener = new MenuListener();

		// Buttons
		home = new JButton("Home");
		westPanel.add(home);
		home.addActionListener(menuListener);

		viewCustomers = new JButton("View all existing customers");
		westPanel.add(viewCustomers);
		viewCustomers.addActionListener(menuListener);

		createCustomer = new JButton("Create new customer");
		westPanel.add(createCustomer);
		createCustomer.addActionListener(menuListener);

		deleteCustomer = new JButton("Delete an existing customer");
		westPanel.add(deleteCustomer);
		deleteCustomer.addActionListener(menuListener);

		JLabel handleCust = new JLabel("                Handle customers");
		westPanel.add(handleCust);

		changeCustomerName = new JButton("Change customer name");
		westPanel.add(changeCustomerName);
		changeCustomerName.addActionListener(menuListener);

		createSavingsAccount = new JButton("Create savings account");
		westPanel.add(createSavingsAccount);
		createSavingsAccount.addActionListener(menuListener);

		createCreditAccount = new JButton("Create credit account");
		westPanel.add(createCreditAccount);
		createCreditAccount.addActionListener(menuListener);

		viewCustomerAccounts = new JButton("View customer accounts");
		westPanel.add(viewCustomerAccounts);
		viewCustomerAccounts.addActionListener(menuListener);

		findAccount = new JButton("Find account");
		westPanel.add(findAccount);
		findAccount.addActionListener(menuListener);

		deposit = new JButton("Deposit");
		westPanel.add(deposit);
		deposit.addActionListener(menuListener);

		withdrawal = new JButton("Withdrawal");
		westPanel.add(withdrawal);
		withdrawal.addActionListener(menuListener);

		closeAccount = new JButton("Close account");
		westPanel.add(closeAccount);
		closeAccount.addActionListener(menuListener);

		viewTransactions = new JButton("View transactions");
		westPanel.add(viewTransactions);
		viewTransactions.addActionListener(menuListener);

		westPanel.setVisible(true);
	}

	/**
	 * Center panel. Handles input and "OK" buttons.
	 */
	private void buildCenter()
	{
		centerPanel = new JPanel();
		centerPanel.setPreferredSize(new Dimension(CENTER_WIDTH, HEIGHT));
		this.add(centerPanel, BorderLayout.CENTER);

		// Fields, will be used to handle input for all functions and will be
		// manipulated each time.
		field1 = new JTextField(14);
		centerPanel.add(field1);
		field1.setVisible(false);

		field2 = new JTextField(14);
		centerPanel.add(field2);
		field2.setVisible(false);

		field3 = new JTextField(14);
		centerPanel.add(field3);
		field3.setVisible(false);

		// ActionListener for "OK" buttons
		OkListener okListener = new OkListener();

		// "OK" buttons need to be different depending on which function we are using
		// because it will call different functions within BankLogic class.
		okCreateCustomer = new JButton("OK");
		okCreateCustomer.addActionListener(okListener);
		centerPanel.add(okCreateCustomer);
		okCreateCustomer.setVisible(false);

		okDeleteCustomer = new JButton("OK");
		okDeleteCustomer.addActionListener(okListener);
		centerPanel.add(okDeleteCustomer);
		okDeleteCustomer.setVisible(false);

		okChangeCustomerName = new JButton("OK");
		okChangeCustomerName.addActionListener(okListener);
		centerPanel.add(okChangeCustomerName);
		okChangeCustomerName.setVisible(false);

		okCreateSavingsAccount = new JButton("OK");
		okCreateSavingsAccount.addActionListener(okListener);
		centerPanel.add(okCreateSavingsAccount);
		okCreateSavingsAccount.setVisible(false);

		okCreateCreditAccount = new JButton("OK");
		okCreateCreditAccount.addActionListener(okListener);
		centerPanel.add(okCreateCreditAccount);
		okCreateCreditAccount.setVisible(false);

		okViewCustomerAccounts = new JButton("OK");
		okViewCustomerAccounts.addActionListener(okListener);
		centerPanel.add(okViewCustomerAccounts);
		okViewCustomerAccounts.setVisible(false);

		okFindAccount = new JButton("OK");
		okFindAccount.addActionListener(okListener);
		centerPanel.add(okFindAccount);
		okFindAccount.setVisible(false);

		okDeposit = new JButton("OK");
		okDeposit.addActionListener(okListener);
		centerPanel.add(okDeposit);
		okDeposit.setVisible(false);

		okWithdrawal = new JButton("OK");
		okWithdrawal.addActionListener(okListener);
		centerPanel.add(okWithdrawal);
		okWithdrawal.setVisible(false);

		okCloseAccount = new JButton("OK");
		okCloseAccount.addActionListener(okListener);
		centerPanel.add(okCloseAccount);
		okCloseAccount.setVisible(false);

		okViewTransactions = new JButton("OK");
		okViewTransactions.addActionListener(okListener);
		centerPanel.add(okViewTransactions);
		okViewTransactions.setVisible(false);

		centerPanel.setVisible(true);
	}

	/**
	 * East panel. Shows relevant current information about customers, accounts and
	 * transactions. Information field changes depending on which state in the
	 * program user currently is.
	 */
	private void buildEast()
	{
		eastPanel = new JPanel();
		eastPanel.setPreferredSize(new Dimension(EAST_WIDTH, HEIGHT));
		this.add(eastPanel, BorderLayout.EAST);

		// We display information in JList. When we start the program default is a list
		// of the bank's current customers.
		displayList = new JList();
		displayList.setListData(bank.getAllCustomers().toArray());
		displayList.setBorder(BorderFactory.createTitledBorder("Customers"));

		// Scroll is needed because list can grow large.
		JScrollPane custScroll = new JScrollPane(displayList);
		custScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		custScroll.setVisible(true);
		custScroll.setPreferredSize(new Dimension(EAST_WIDTH, HEIGHT - 100));
		eastPanel.add(custScroll);

		eastPanel.setVisible(true);
	}

	/**
	 * South panel. Shows program name, version and author.
	 */
	private void buildSouth()
	{
		southPanel = new JPanel();
		this.add(southPanel, BorderLayout.SOUTH);

		JLabel version = new JLabel("Bank 1.0 by Viktor Lundberg");
		southPanel.add(version);
		southPanel.setVisible(true);
	}

	/**
	 * Since we are reusing the center panel for user input interactions we have to
	 * make sure we only display the components we need for the current page. So by
	 * default, we want to reset so everything is hidden each time we start to call
	 * a button function.
	 */
	private void hideFields()
	{
		field1.setVisible(false);
		field2.setVisible(false);
		field3.setVisible(false);
		okCreateCustomer.setVisible(false);
		okDeleteCustomer.setVisible(false);
		okChangeCustomerName.setVisible(false);
		okCreateSavingsAccount.setVisible(false);
		okCreateCreditAccount.setVisible(false);
		okViewCustomerAccounts.setVisible(false);
		okFindAccount.setVisible(false);
		okDeposit.setVisible(false);
		okWithdrawal.setVisible(false);
		okCloseAccount.setVisible(false);
		okViewTransactions.setVisible(false);
	}

	/**
	 * Nested ActionListener class. This class listens to the buttons in our menu,
	 * west panel. Each action will perform changes to the center panel, getting the
	 * center panel ready to handle next user input properly.
	 */
	public class MenuListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			// Get text from button clicked. Use this information to find out which button
			// user clicked on.
			String buttonText = e.getActionCommand();

			// Clicked "Home" or "View all existing customers" button
			if (buttonText.equals("Home") || buttonText.equals("View all existing customers"))
			{
				// Change current page information
				currentPage.setText(buttonText);
				// By default we want to hide all fields and bring out only what we need
				hideFields();

				// By default we want to show current customers in the bank.
				displayList.setListData(bank.getAllCustomers().toArray());
				displayList.setBorder(BorderFactory.createTitledBorder("Customers"));
			}
			// Clicked "Create new customer" button
			else if (buttonText.equals("Create new customer"))
			{
				currentPage.setText(buttonText);
				hideFields();

				// Manipulate fields so the user knows what to fill in
				field1.setBorder(BorderFactory.createTitledBorder("Name"));
				field1.setVisible(true);
				field2.setBorder(BorderFactory.createTitledBorder("Surname"));
				field2.setVisible(true);
				field3.setBorder(BorderFactory.createTitledBorder("Personal number"));
				field3.setVisible(true);

				// Bring out "OK" button linked to the specific function we are performing, in
				// this case the click of "OK" from user means we should call the createCustomer
				// method in BankLogic.
				okCreateCustomer.setVisible(true);
			}
			// Clicked "Delete an existing customer" button
			else if (buttonText.equals("Delete an existing customer"))
			{
				currentPage.setText(buttonText);
				hideFields();
				field1.setBorder(BorderFactory.createTitledBorder("Personal number"));
				field1.setVisible(true);
				okDeleteCustomer.setVisible(true);
			}
			// Clicked "Change customer name" button
			else if (buttonText.equals("Change customer name"))
			{
				currentPage.setText(buttonText);
				hideFields();
				field1.setBorder(BorderFactory.createTitledBorder("Name"));
				field1.setVisible(true);
				field2.setBorder(BorderFactory.createTitledBorder("Surname"));
				field2.setVisible(true);
				field3.setBorder(BorderFactory.createTitledBorder("Personal number"));
				field3.setVisible(true);
				okChangeCustomerName.setVisible(true);
			}
			// Clicked "Create savings account" button
			else if (buttonText.equals("Create savings account"))
			{
				currentPage.setText(buttonText);
				hideFields();
				field1.setBorder(BorderFactory.createTitledBorder("Personal number"));
				field1.setVisible(true);
				okCreateSavingsAccount.setVisible(true);
			}
			// Clicked "Create credit account" button
			else if (buttonText.equals("Create credit account"))
			{
				currentPage.setText(buttonText);
				hideFields();
				field1.setBorder(BorderFactory.createTitledBorder("Personal number"));
				field1.setVisible(true);
				okCreateCreditAccount.setVisible(true);
			}
			// Clicked "View customer accounts" button
			else if (buttonText.equals("View customer accounts"))
			{
				currentPage.setText(buttonText);
				hideFields();
				field1.setBorder(BorderFactory.createTitledBorder("Personal number"));
				field1.setVisible(true);
				okViewCustomerAccounts.setVisible(true);
			}
			// Clicked "Find account" button
			else if (buttonText.equals("Find account"))
			{
				currentPage.setText(buttonText);
				hideFields();
				field1.setBorder(BorderFactory.createTitledBorder("Personal number"));
				field1.setVisible(true);
				field2.setBorder(BorderFactory.createTitledBorder("Account number"));
				field2.setVisible(true);
				okFindAccount.setVisible(true);
			}
			// Clicked "Deposit" button
			else if (buttonText.equals("Deposit"))
			{
				currentPage.setText(buttonText);
				hideFields();
				field1.setBorder(BorderFactory.createTitledBorder("Personal number"));
				field1.setVisible(true);
				field2.setBorder(BorderFactory.createTitledBorder("Account number"));
				field2.setVisible(true);
				field3.setBorder(BorderFactory.createTitledBorder("Amount"));
				field3.setVisible(true);
				okDeposit.setVisible(true);
			}
			// Clicked "Withdrawal" button
			else if (buttonText.equals("Withdrawal"))
			{
				currentPage.setText(buttonText);
				hideFields();
				field1.setBorder(BorderFactory.createTitledBorder("Personal number"));
				field1.setVisible(true);
				field2.setBorder(BorderFactory.createTitledBorder("Account number"));
				field2.setVisible(true);
				field3.setBorder(BorderFactory.createTitledBorder("Amount"));
				field3.setVisible(true);
				okWithdrawal.setVisible(true);
			}
			// Clicked "Close account" button
			else if (buttonText.equals("Close account"))
			{
				currentPage.setText(buttonText);
				hideFields();
				field1.setBorder(BorderFactory.createTitledBorder("Personal number"));
				field1.setVisible(true);
				field2.setBorder(BorderFactory.createTitledBorder("Account number"));
				field2.setVisible(true);
				okCloseAccount.setVisible(true);
			}
			// Clicked "View transactions" button
			else if (buttonText.equals("View transactions"))
			{
				currentPage.setText(buttonText);
				hideFields();
				field1.setBorder(BorderFactory.createTitledBorder("Personal number"));
				field1.setVisible(true);
				field2.setBorder(BorderFactory.createTitledBorder("Account number"));
				field2.setVisible(true);
				okViewTransactions.setVisible(true);
			}
		}
	}

	/**
	 * Nested ActionListener class. This class listens to the "OK" buttons and
	 * accepts information from field1, field2 and field3 and calls the appropriate
	 * function in BankLogic class.
	 */
	public class OkListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			// Create new customer
			if (e.getSource().equals(okCreateCustomer))
			{
				// createCustomer returns boolean, true or false, if successful or not. Store in
				// variable to know if it was successful or not
				boolean action = bank.createCustomer(field1.getText(), field2.getText(), field3.getText());

				// Display changes
				displayList.setListData(bank.getAllCustomers().toArray());

				// Sets the text in field1, field2 and field3 to ""
				resetTextFields();

				// Popup message that displays if the action was a success or not.
				confirmBoolean(action);
			}
			// Delete customer
			else if (e.getSource().equals(okDeleteCustomer))
			{
				// deleteCustomer returns an ArrayList with information about the deleted
				// customer. Store this information.
				ArrayList<String> action = bank.deleteCustomer(field1.getText());

				// But if no customer was found action will contain null, if this is the case,
				// customer was not found
				if (action == null)
				{
					JOptionPane.showMessageDialog(rootPane, "Customer was not found");
				}
				// If we got something back, continue as normal.
				else
				{
					displayList.setListData(bank.getAllCustomers().toArray());
					resetTextFields();

					// Show information about the deleted customer
					JList confirm = new JList();
					confirm.setListData(action.toArray());
					JOptionPane.showMessageDialog(rootPane, confirm);
				}
			}
			// Change customer name
			else if (e.getSource().equals(okChangeCustomerName))
			{
				boolean action = bank.changeCustomerName(field1.getText(), field2.getText(), field3.getText());
				displayList.setListData(bank.getAllCustomers().toArray());
				resetTextFields();
				confirmBoolean(action);
			}
			// Create savings account
			else if (e.getSource().equals(okCreateSavingsAccount))
			{
				// Creating accounts is a little different because it will return an int value
				// instead of boolean.
				int action = bank.createSavingsAccount(field1.getText());
				displayList.setListData(bank.getCustomer(field1.getText()).toArray());
				displayList.setBorder(BorderFactory.createTitledBorder("Customer info"));
				resetTextFields();
				confirmAccount(action);
			}
			// Create credit account
			else if (e.getSource().equals(okCreateCreditAccount))
			{
				int action = bank.createCreditAccount(field1.getText());
				displayList.setListData(bank.getCustomer(field1.getText()).toArray());
				displayList.setBorder(BorderFactory.createTitledBorder("Customer info"));
				resetTextFields();
				confirmAccount(action);
			}
			// View customer accounts
			else if (e.getSource().equals(okViewCustomerAccounts))
			{
				// If this is true, we didn't find the customer.
				if (bank.getCustomer(field1.getText()) == null)
				{
					JOptionPane.showMessageDialog(rootPane, "No customer was found");
				}
				// We found the customer, continue.
				else
				{
					displayList.setListData(bank.getCustomer(field1.getText()).toArray());
					displayList.setBorder(BorderFactory.createTitledBorder("Customer info"));
					resetTextFields();
				}
			}
			// Find account
			else if (e.getSource().equals(okFindAccount))
			{
				if (bank.getAccount(field1.getText(), Integer.valueOf(field2.getText())) == null)
				{
					JOptionPane.showMessageDialog(rootPane, "No account was found");
				}
				else
				{
					String info = bank.getAccount(field1.getText(), Integer.valueOf(field2.getText()));
					String[] arr = { info };
					displayList.setListData(arr);
					displayList.setBorder(BorderFactory.createTitledBorder("Account information"));
					resetTextFields();
				}
			}
			// Deposit
			else if (e.getSource().equals(okDeposit))
			{
				String pNo = field1.getText();
				int accountId = Integer.valueOf(field2.getText());
				double amount = Double.parseDouble(field3.getText());
				boolean action = bank.deposit(pNo, accountId, amount);
				displayList.setListData(bank.getCustomer(field1.getText()).toArray());
				displayList.setBorder(BorderFactory.createTitledBorder("Customer info"));
				resetTextFields();
				confirmBoolean(action);
			}
			// Withdrawal
			else if (e.getSource().equals(okWithdrawal))
			{
				String pNo = field1.getText();
				int accountId = Integer.valueOf(field2.getText());
				double amount = Double.parseDouble(field3.getText());
				boolean action = bank.withdraw(pNo, accountId, amount);
				displayList.setListData(bank.getCustomer(field1.getText()).toArray());
				displayList.setBorder(BorderFactory.createTitledBorder("Customer info"));
				resetTextFields();
				confirmBoolean(action);
			}
			// Close account
			else if (e.getSource().equals(okCloseAccount))
			{
				String pNo = field1.getText();
				int accountId = Integer.valueOf(field2.getText());
				// In this case we get a String back from closeAccount method
				String action = bank.closeAccount(pNo, accountId);
				displayList.setListData(bank.getCustomer(field1.getText()).toArray());
				displayList.setBorder(BorderFactory.createTitledBorder("Customer info"));
				resetTextFields();
				// Show string
				JOptionPane.showMessageDialog(rootPane, action);
			}
			// View transactions
			else if (e.getSource().equals(okViewTransactions))
			{
				String pNo = field1.getText();
				int accountId = Integer.valueOf(field2.getText());

				if (bank.getTransactions(pNo, accountId) == null)
				{
					JOptionPane.showMessageDialog(rootPane, "No transactions found");
				}
				else
				{
					displayList.setListData(bank.getTransactions(pNo, accountId).toArray());
					displayList.setBorder(BorderFactory.createTitledBorder("Account transactions"));
					resetTextFields();
				}
			}
		}
	}

	/**
	 * Shows an JOptionPane message with information if the action from the user was
	 * successful or not.
	 * 
	 * @param action, true = successful, false = unsuccessful.
	 */
	private void confirmBoolean(boolean action)
	{
		if (action == true)
		{
			JOptionPane.showMessageDialog(rootPane, "OK");
		}
		else if (action == false)
		{
			JOptionPane.showMessageDialog(rootPane, "Something went wrong");
		}
	}

	/**
	 * Shows an JOptionPane message with information about newly created account.
	 * 
	 * @param action, account number from user action if successful, otherwise -1 =
	 *                something was wrong.
	 */
	private void confirmAccount(int action)
	{
		if (action == -1)
		{
			JOptionPane.showMessageDialog(rootPane, "Something went wrong");
		}
		else
		{
			JOptionPane.showMessageDialog(rootPane, "OK, account number: " + action);
		}
	}

	/**
	 * Resets the textFields in center panel.
	 */
	private void resetTextFields()
	{
		field1.setText("");
		field2.setText("");
		field3.setText("");
	}

}
