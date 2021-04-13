package lunvik8;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Basic GUI class that implements interface for bank. Part of D0018D,
 * assignment 3.
 * 
 * @author Viktor Lundberg, lunvik-8
 * @version 1.0 (2021-04-13)
 */

public class GUI extends JFrame
{
	// To create an instance of BankLogic
	BankLogic bank;

	// Main method to start the GUI
	public static void main(String[] args)
	{
		// Driver code
		JFrame frame = new GUI();
	}

	// Window settings
	private static final int HEIGHT = 400;
	private static final int WEST_WIDTH = 240;
	private static final int CENTER_WIDTH = 240;
	private static final int EAST_WIDTH = 240;

	// To be reached by listener
	JPanel northPanel, westPanel, centerPanel, eastPanel, southPanel;
	JLabel currentPage;
	JButton home, createCust, deleteCust, listCust, changeCustName, createSavingsAcc, createCreditAcc, findAccount,
			deposit, withdrawal, closeAcc, viewTransactions, viewCustomerAccounts;
	JTextField field1, field2, field3;
	JList displayList;
	TitledBorder field1border, field2border, field3border;

	JButton okCreateCust, okDeleteCust, okChangeCustName, okCreateSavings, okCreateCredit, okFindAccount, okDeposit,
			okWithdrawal, okCloseAcc, okViewTransactions, okViewCustomerAccounts;

	// Constructor
	public GUI()
	{
		bank = new BankLogic();

		// för test. tas bort sen.
		bank.createCustomer("Henrik", "Rydstrom", "870101-0101");
		bank.createCreditAccount("870101-0101");
		bank.createSavingsAccount("870101-0101");

		this.setLayout(new BorderLayout());
		buildMenu();
		buildNorth();
		buildWest();
		buildCenter();
		buildEast();
		buildSouth();
		this.setTitle("Bank");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	/**
	 * Menu for file handling
	 */
	private void buildMenu()
	{
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		JMenu importMenu = new JMenu("Import ...");
		fileMenu.add(importMenu);
		JMenu exportMenu = new JMenu("Export ...");
		fileMenu.add(exportMenu);

		JMenuItem customerImport = new JMenuItem("Customer with accounts");
		importMenu.add(customerImport);
		JMenuItem customerExport = new JMenuItem("Customer with accounts");
		exportMenu.add(customerExport);
		JMenuItem transactionItem = new JMenuItem("Transactions for account");
		exportMenu.add(transactionItem);
	}

	/**
	 * Top center, display current page
	 */
	private void buildNorth()
	{
		northPanel = new JPanel();
		this.add(northPanel, BorderLayout.NORTH);

		currentPage = new JLabel("Home");
		northPanel.add(currentPage);
		northPanel.setVisible(true);
	}

	/**
	 * West. Menu with buttons, all our functions.
	 */
	private void buildWest()
	{
		westPanel = new JPanel();
		westPanel.setLayout(new GridLayout(14, 1));
		westPanel.setPreferredSize(new Dimension(WEST_WIDTH, HEIGHT));
		this.add(westPanel, BorderLayout.WEST);

		MenuListener al = new MenuListener(); // ActionListener

		// Buttons
		home = new JButton("Home");
		westPanel.add(home);
		home.addActionListener(al);

		listCust = new JButton("View all existing customers");
		westPanel.add(listCust);
		listCust.addActionListener(al);

		createCust = new JButton("Create new customer");
		westPanel.add(createCust);
		createCust.addActionListener(al);

		deleteCust = new JButton("Delete an existing customer");
		westPanel.add(deleteCust);
		deleteCust.addActionListener(al);

		JLabel handleCust = new JLabel("             Handle customers");
		westPanel.add(handleCust);

		changeCustName = new JButton("Change customer name");
		westPanel.add(changeCustName);
		changeCustName.addActionListener(al);

		createSavingsAcc = new JButton("Create savings account");
		westPanel.add(createSavingsAcc);
		createSavingsAcc.addActionListener(al);

		createCreditAcc = new JButton("Create credit account");
		westPanel.add(createCreditAcc);
		createCreditAcc.addActionListener(al);

		viewCustomerAccounts = new JButton("View customer accounts");
		westPanel.add(viewCustomerAccounts);
		viewCustomerAccounts.addActionListener(al);

		findAccount = new JButton("Find account");
		westPanel.add(findAccount);
		findAccount.addActionListener(al);

		deposit = new JButton("Deposit");
		westPanel.add(deposit);
		deposit.addActionListener(al);

		withdrawal = new JButton("Withdrawal");
		westPanel.add(withdrawal);
		withdrawal.addActionListener(al);

		closeAcc = new JButton("Close account");
		westPanel.add(closeAcc);
		closeAcc.addActionListener(al);

		viewTransactions = new JButton("View transactions");
		westPanel.add(viewTransactions);
		viewTransactions.addActionListener(al);

		westPanel.setVisible(true);
	}

	private void buildCenter()
	{
		centerPanel = new JPanel();
		centerPanel.setPreferredSize(new Dimension(CENTER_WIDTH, HEIGHT));
		this.add(centerPanel, BorderLayout.CENTER);

		// Fields, will be used for all functions and manipulated each time.
		field1 = new JTextField(14);
		field1border = BorderFactory.createTitledBorder("field1:");
		field1.setBorder(field1border);
		centerPanel.add(field1);
		field1.setVisible(false);

		field2 = new JTextField(14);
		field2border = BorderFactory.createTitledBorder("field2:");
		field2.setBorder(field2border);
		centerPanel.add(field2);
		field2.setVisible(false);

		field3 = new JTextField(14);
		field3border = BorderFactory.createTitledBorder("field3:");
		field3.setBorder(field3border);
		centerPanel.add(field3);
		field3.setVisible(false);

		OkListener al = new OkListener(); // ActionListener

		// OK buttons need to be different depending on which function we are using
		// because it will call to different logic functions.
		okCreateCust = new JButton("okCreateCust");
		okCreateCust.addActionListener(al);
		centerPanel.add(okCreateCust);
		okCreateCust.setVisible(false);

		okDeleteCust = new JButton("okDeleteCust");
		okDeleteCust.addActionListener(al);
		centerPanel.add(okDeleteCust);
		okDeleteCust.setVisible(false);

		okChangeCustName = new JButton("okChangeCustName");
		okChangeCustName.addActionListener(al);
		centerPanel.add(okChangeCustName);
		okChangeCustName.setVisible(false);

		okCreateSavings = new JButton("okCreateSavings");
		okCreateSavings.addActionListener(al);
		centerPanel.add(okCreateSavings);
		okCreateSavings.setVisible(false);

		okCreateCredit = new JButton("okCreateCredit");
		okCreateCredit.addActionListener(al);
		centerPanel.add(okCreateCredit);
		okCreateCredit.setVisible(false);

		okViewCustomerAccounts = new JButton("okViewCustomerAccounts");
		okViewCustomerAccounts.addActionListener(al);
		centerPanel.add(okViewCustomerAccounts);
		okViewCustomerAccounts.setVisible(false);

		okFindAccount = new JButton("okFindAccount");
		okFindAccount.addActionListener(al);
		centerPanel.add(okFindAccount);
		okFindAccount.setVisible(false);

		okDeposit = new JButton("okDeposit");
		okDeposit.addActionListener(al);
		centerPanel.add(okDeposit);
		okDeposit.setVisible(false);

		okWithdrawal = new JButton("okWithdrawal");
		okWithdrawal.addActionListener(al);
		centerPanel.add(okWithdrawal);
		okWithdrawal.setVisible(false);

		okCloseAcc = new JButton("okCloseAcc");
		okCloseAcc.addActionListener(al);
		centerPanel.add(okCloseAcc);
		okCloseAcc.setVisible(false);

		okViewTransactions = new JButton("okViewTransactions");
		okViewTransactions.addActionListener(al);
		centerPanel.add(okViewTransactions);
		okViewTransactions.setVisible(false);

		centerPanel.setVisible(true);
	}

	/**
	 * Shows banks current customers
	 */
	private void buildEast()
	{
		eastPanel = new JPanel();
		eastPanel.setPreferredSize(new Dimension(EAST_WIDTH, HEIGHT));
		this.add(eastPanel, BorderLayout.EAST);

		displayList = new JList();
		displayList.setListData(bank.getAllCustomers().toArray());
		displayList.setBorder(BorderFactory.createTitledBorder("Customers"));
		JScrollPane custScroll = new JScrollPane(displayList);
		custScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		custScroll.setVisible(true);
		custScroll.setPreferredSize(new Dimension(EAST_WIDTH, HEIGHT - 100));
		eastPanel.add(custScroll);

		eastPanel.setVisible(true);
	}

	/**
	 * Shows program version
	 */
	private void buildSouth()
	{
		southPanel = new JPanel();
		this.add(southPanel, BorderLayout.SOUTH);

		JLabel version = new JLabel("Bank 1.0 by Viktor Lundberg");
		southPanel.add(version);
		southPanel.setVisible(true);
	}

	private void hideFields()
	{
		field1.setVisible(false);
		field2.setVisible(false);
		field3.setVisible(false);
		okCreateCust.setVisible(false);
		okDeleteCust.setVisible(false);
		okChangeCustName.setVisible(false);
		okCreateSavings.setVisible(false);
		okCreateCredit.setVisible(false);
		okViewCustomerAccounts.setVisible(false);
		okFindAccount.setVisible(false);
		okDeposit.setVisible(false);
		okWithdrawal.setVisible(false);
		okCloseAcc.setVisible(false);
		okViewTransactions.setVisible(false);
	}

	// inbyggd lyssnarklass
	public class MenuListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			String buttonText = e.getActionCommand();

			if (buttonText.equals("Home") || buttonText.equals("View all existing customers"))
			{
				currentPage.setText(buttonText);
				hideFields();
				displayList.setListData(bank.getAllCustomers().toArray());
				displayList.setBorder(BorderFactory.createTitledBorder("Customers"));
			}
			else if (buttonText.equals("Create new customer"))
			{
				currentPage.setText(buttonText);
				hideFields();
				field1.setBorder(BorderFactory.createTitledBorder("Name"));
				field1.setVisible(true);
				field2.setBorder(BorderFactory.createTitledBorder("Surname"));
				field2.setVisible(true);
				field3.setBorder(BorderFactory.createTitledBorder("Personal number"));
				field3.setVisible(true);

				okCreateCust.setVisible(true);
			}
			else if (buttonText.equals("Delete an existing customer"))
			{
				currentPage.setText(buttonText);
				hideFields();
				field1.setBorder(BorderFactory.createTitledBorder("Personal number"));
				field1.setVisible(true);

				okDeleteCust.setVisible(true);
			}
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
				okChangeCustName.setVisible(true);
			}
			else if (buttonText.equals("Create savings account"))
			{
				currentPage.setText(buttonText);
				hideFields();
				field1.setBorder(BorderFactory.createTitledBorder("Personal number"));
				field1.setVisible(true);
				okCreateSavings.setVisible(true);
			}
			else if (buttonText.equals("Create credit account"))
			{
				currentPage.setText(buttonText);
				hideFields();
				field1.setBorder(BorderFactory.createTitledBorder("Personal number"));
				field1.setVisible(true);
				okCreateCredit.setVisible(true);
			}
			else if (buttonText.equals("View customer accounts"))
			{
				currentPage.setText(buttonText);
				hideFields();
				field1.setBorder(BorderFactory.createTitledBorder("Personal number"));
				field1.setVisible(true);
				okViewCustomerAccounts.setVisible(true);
			}
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
			else if (buttonText.equals("Close account"))
			{
				currentPage.setText(buttonText);
				hideFields();
				field1.setBorder(BorderFactory.createTitledBorder("Personal number"));
				field1.setVisible(true);
				field2.setBorder(BorderFactory.createTitledBorder("Account number"));
				field2.setVisible(true);
				okCloseAcc.setVisible(true);
			}
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

	public class OkListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource().equals(okCreateCust))
			{
				boolean action = bank.createCustomer(field1.getText(), field2.getText(), field3.getText());
				displayList.setListData(bank.getAllCustomers().toArray());
				field1.setText("");
				field2.setText("");
				field3.setText("");
				confirmBoolean(action);
			}
			else if (e.getSource().equals(okDeleteCust))
			{
				ArrayList<String> action = bank.deleteCustomer(field1.getText());
				displayList.setListData(bank.getAllCustomers().toArray());
				field1.setText("");

				JList confirm = new JList();
				confirm.setListData(action.toArray());
				JOptionPane.showMessageDialog(rootPane, confirm);
			}
			else if (e.getSource().equals(okChangeCustName))
			{
				boolean action = bank.changeCustomerName(field1.getText(), field2.getText(), field3.getText());
				displayList.setListData(bank.getAllCustomers().toArray());
				field1.setText("");
				field2.setText("");
				field3.setText("");
				confirmBoolean(action);
			}
			else if (e.getSource().equals(okCreateSavings))
			{
				int action = bank.createSavingsAccount(field1.getText());
				displayList.setListData(bank.getCustomer(field1.getText()).toArray());
				displayList.setBorder(BorderFactory.createTitledBorder("Customer info"));
				field1.setText("");
				confirmAccount(action);

			}
			else if (e.getSource().equals(okCreateCredit))
			{
				int action = bank.createCreditAccount(field1.getText());
				displayList.setListData(bank.getCustomer(field1.getText()).toArray());
				displayList.setBorder(BorderFactory.createTitledBorder("Customer info"));
				field1.setText("");
				confirmAccount(action);
			}
			else if (e.getSource().equals(okViewCustomerAccounts))
			{
				if (bank.getCustomer(field1.getText()) == null)
				{
					JOptionPane.showMessageDialog(rootPane, "No customer was found");
				}
				else
				{
					displayList.setListData(bank.getCustomer(field1.getText()).toArray());
					displayList.setBorder(BorderFactory.createTitledBorder("Customer info"));
					field1.setText("");
				}
			}
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
					field1.setText("");
					field2.setText("");
				}
			}
			else if (e.getSource().equals(okDeposit))
			{
				String pNo = field1.getText();
				int accountId = Integer.valueOf(field2.getText());
				double amount = Double.parseDouble(field3.getText());
				boolean action = bank.deposit(pNo, accountId, amount);
				displayList.setListData(bank.getCustomer(field1.getText()).toArray());
				displayList.setBorder(BorderFactory.createTitledBorder("Customer info"));
				field1.setText("");
				field2.setText("");
				field3.setText("");
				confirmBoolean(action);
			}
			else if (e.getSource().equals(okWithdrawal))
			{
				String pNo = field1.getText();
				int accountId = Integer.valueOf(field2.getText());
				double amount = Double.parseDouble(field3.getText());
				boolean action = bank.withdraw(pNo, accountId, amount);
				displayList.setListData(bank.getCustomer(field1.getText()).toArray());
				displayList.setBorder(BorderFactory.createTitledBorder("Customer info"));
				field1.setText("");
				field2.setText("");
				field3.setText("");
				confirmBoolean(action);
			}
			else if (e.getSource().equals(okCloseAcc))
			{
				String pNo = field1.getText();
				int accountId = Integer.valueOf(field2.getText());
				String action = bank.closeAccount(pNo, accountId);
				displayList.setListData(bank.getCustomer(field1.getText()).toArray());
				displayList.setBorder(BorderFactory.createTitledBorder("Customer info"));
				field1.setText("");
				field2.setText("");
				JOptionPane.showMessageDialog(rootPane, action);
			}
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
					field1.setText("");
					field2.setText("");
				}
			}
		}

	}

	private void confirmBoolean(boolean action)
	{

		if (action == true)
		{
			JOptionPane.showMessageDialog(rootPane, "Ok");
		}
		else if (action == false)
		{
			JOptionPane.showMessageDialog(rootPane, "Something went wrong");
		}
	}

	private void confirmAccount(int action)
	{
		if (action == -1)
		{
			JOptionPane.showMessageDialog(rootPane, "Something went wrong");
		}
		else
		{
			JOptionPane.showMessageDialog(rootPane, "Ok, account number: " + action);
		}
	}

}
