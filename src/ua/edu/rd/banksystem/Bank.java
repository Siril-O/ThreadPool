package ua.epam.rd.banksystem;

import java.util.ArrayList;
import java.util.List;

public class Bank {

	private List<Account> accounts;

	public Bank() {
		super();
		this.accounts = new ArrayList<>();
	}

	public Bank(List<Account> accounts) {
		super();
		this.accounts = accounts;
	}

	public void transfer(Account from, Account to, int amount) {
		if (from.getId() <= to.getId()) {
			tranferHelper(from, to, amount, from, to);
		} else {
			tranferHelper(from, to, amount, to, from);

		}
	}

	private void tranferHelper(Account from, Account to, int amount,
			Object firstMonitor, Object secondMonitor) {
		synchronized (firstMonitor) {
			synchronized (secondMonitor) {
				try {
					from.withdraw(amount);
					to.deposit(amount);
				} catch (InsufficientFundsException e) {
					// System.out.println("No money");
					return;
				}
				// System.out.println(Thread.currentThread().getName() + "From:"
				// + from + " To:" + to + " amount:" + amount);
			}
		}
	}

	/**
	 * @return the accounts
	 */
	public List<Account> getAccounts() {
		return accounts;
	}

	/**
	 * @param accounts
	 *            the accounts to set
	 */
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
}
