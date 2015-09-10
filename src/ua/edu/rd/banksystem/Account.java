package ua.epam.rd.banksystem;

public class Account {

	private int balance;
	private int id;

	public Account(int balance, int id) {
		super();
		this.balance = balance;
		this.id = id;
	}

	public void withdraw(int amount) {
		if (balance < amount) {
			throw new InsufficientFundsException();
		}
		balance -= amount;
	}

	public void deposit(int amount) {
		balance += amount;
	}

	/**
	 * @return the balance
	 */
	public int getBalance() {
		return balance;
	}

	/**
	 * @param balance
	 *            the balance to set
	 */
	public void setBalance(int balance) {
		this.balance = balance;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Account [balance=" + balance + "]";
	}

}
