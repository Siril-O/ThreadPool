package ua.epam.rd.banksystem;

public class InsufficientFundsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9168239333861115204L;

	public InsufficientFundsException() {
		super();
	}

	public InsufficientFundsException(String message) {
		super(message);
	}
}
