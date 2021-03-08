package exceptions;

public class PaymentPeriodNegativeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PaymentPeriodNegativeException() {
		// TODO Auto-generated constructor stub
	}

	public PaymentPeriodNegativeException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public PaymentPeriodNegativeException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public PaymentPeriodNegativeException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public PaymentPeriodNegativeException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
