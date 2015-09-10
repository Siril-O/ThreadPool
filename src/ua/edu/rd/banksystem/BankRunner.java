package ua.epam.rd.banksystem;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class BankRunner {

	private static final int THREAD_TRANSFERS_QUANTITY = 20;
	private static final int THREADS_QUANTITY = 2000;
	private static final int ACCOUNT_QUANTITY = 100;
	private static final int MAX_INITIAL_BALANCE = 1000;
	private static final int MAX_TRANSFER_SUM = 500;
	private static final int MIN_TRANSFER_SUM = 10;

	public static void main(String[] args) {

		Random random = new Random();
		List<Account> accounts = new LinkedList<Account>();
		Bank bank = new Bank(accounts);

		for (int i = 0; i < ACCOUNT_QUANTITY; i++) {
			accounts.add(new Account(random.nextInt(MAX_INITIAL_BALANCE), i));
		}

		int expectedSumm = calculateTotalBankSumm(bank);

		System.out.println(expectedSumm);

		List<Thread> threads = new LinkedList<>();

		for (int i = 0; i < THREADS_QUANTITY; i++) {

			Thread thread = new Thread() {
				@Override
				public void run() {
					for (int j = 0; j < THREAD_TRANSFERS_QUANTITY; j++) {

						int fromAccountIndex = random.nextInt(ACCOUNT_QUANTITY);
						int toAccountIndex = random.nextInt(ACCOUNT_QUANTITY);

						while (toAccountIndex == fromAccountIndex) {
							toAccountIndex = random.nextInt(ACCOUNT_QUANTITY);
						}

						Account from = accounts.get(fromAccountIndex);
						Account to = accounts.get(toAccountIndex);

						bank.transfer(
								from,
								to,
								MIN_TRANSFER_SUM
										+ random.nextInt(MAX_TRANSFER_SUM
												- MIN_TRANSFER_SUM));
					}
				}
			};
			threads.add(thread);
		}

		for (Thread thread : threads) {
			thread.start();
		}

		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		int realSumm = calculateTotalBankSumm(bank);
		System.out.println(realSumm);
		System.out.println("It works:" + (expectedSumm == realSumm));

	}

	private static int calculateTotalBankSumm(Bank bank) {
		List<Account> accounts = bank.getAccounts();

		int expectedSumm = 0;
		for (Account account : accounts) {
			expectedSumm += account.getBalance();
		}
		return expectedSumm;
	}
}
