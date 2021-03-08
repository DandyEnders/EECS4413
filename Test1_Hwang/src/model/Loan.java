package model;

import exceptions.AnnualInterestNegativeException;
import exceptions.PaymentPeriodNegativeException;
import exceptions.PrincipalNegativeException;

public class Loan {

	public Loan() {
		
	}
	
	public double computeMonthlyPayment(String rawPrincipal, String rawAnnualInterest, String rawPaymentPeriod, String rawGrace, String rawGracePeriod, String rawFixedInterest, boolean isYork) throws Exception{
		Double montlyPayments = 0.0;
		Double gracePeriod = 0.0;
		
		try {
			gracePeriod = Double.parseDouble(rawGracePeriod);
		} catch(NullPointerException e) {
			throw new Exception("You must type in number in grade period input!");
		}
		
		if (gracePeriod <= 0) {
			throw new AnnualInterestNegativeException("The grace priod must be positive!");
		}
		
		Boolean isGracePeriod = true;
		if (rawGrace != null) {isGracePeriod = true;} else {isGracePeriod = false;}
		
		Double monthlyPaymentsWithoutGrace = computePayment(rawPrincipal, rawAnnualInterest, rawPaymentPeriod, rawGrace, rawGracePeriod, rawFixedInterest, isYork);
		Double graceInterest = computeGraceInterest(rawPrincipal, rawGracePeriod, rawAnnualInterest, rawFixedInterest);
		
		if (isGracePeriod) {
			montlyPayments = monthlyPaymentsWithoutGrace + (graceInterest / gracePeriod);
		} else {
			montlyPayments = monthlyPaymentsWithoutGrace;
		}
		
		return montlyPayments;
	}
	
	public double computePayment(String rawPrincipal, String rawAnnualInterest, String rawPaymentPeriod, String rawGrace, String rawGracePeriod, String rawFixedInterest, boolean isYork) throws Exception{		
		
		Double principal = 0.0;
		Double annualInterestRate = 0.0;
		Double paymentPeriod = 0.0;
		try {
			principal = Double.parseDouble(rawPrincipal);
		} catch(NullPointerException | NumberFormatException e) {
			throw new Exception("You must type in number in principal input!");
		}
		
		try {
			annualInterestRate = Double.parseDouble(rawAnnualInterest) / 100;
			if (isYork) {
				annualInterestRate -= 0.005;
			}
		} catch(NullPointerException | NumberFormatException e) {
			throw new Exception("You must type in number in annual interest rate input!");
		}
		
		try {
			paymentPeriod = Double.parseDouble(rawPaymentPeriod);
		} catch(NullPointerException | NumberFormatException e) {
			throw new Exception("You must type in number in payment period input!");
		}
		
		
		if (principal < 0) {
			throw new Exception("The principal must not be negative!");
		}
		
		if (annualInterestRate < 0) {
			throw new Exception("The annual interest must not be negative!");
		}
		 
		if (paymentPeriod <= 0) {
			throw new Exception("The payment period must be positive!");
		}
		
		Double monthlyPaymentsWithoutGrace = ((annualInterestRate/12.0)*principal)/(1 - Math.pow((1 + (annualInterestRate/12.0)), -(paymentPeriod)));
		
		return monthlyPaymentsWithoutGrace;
		
	}
	
	public double computeGraceInterest(String rawPrincipal, String rawGracePeriod, String rawAnnualInterest, String rawFixedInterest) throws Exception{
		Double fixedInterest = Double.parseDouble(rawFixedInterest) / 100;
		
		Double principal = 0.0;
		Double annualInterestRate = 0.0;
		Double gracePeriod = 0.0;
		try {
			principal = Double.parseDouble(rawPrincipal);
		} catch(NullPointerException | NumberFormatException e) {
			throw new Exception("You must type in number in principal input!");
		}
		
		try {
			annualInterestRate = Double.parseDouble(rawAnnualInterest) / 100;
		} catch(NullPointerException | NumberFormatException e) {
			throw new Exception("You must type in number in annual interest rate input!");
		}
		
		try {
			gracePeriod = Double.parseDouble(rawGracePeriod);
		} catch(NullPointerException | NumberFormatException e) {
			throw new Exception("You must type in number in grade period input!");
		}
		
		
		if (principal < 0) {
			throw new Exception("The principal must not be negative!");
		}
		
		if (annualInterestRate < 0) {
			throw new Exception("The annual interest must not be negative!");
		}
		
		if (gracePeriod <= 0) {
			throw new Exception("The grace priod must be positive!");
		}
		
		Double totalInterestRate = fixedInterest + annualInterestRate;
		
		Double graceInterest = principal * ((totalInterestRate) / 12.0) * gracePeriod;
		return graceInterest;
		
	}
	
	public void validateStudentNumberUniversity(String studentNumber, String university) throws Exception {
		Integer intStudentNumber;
		try {
			intStudentNumber = Integer.parseInt(studentNumber);
		} catch(NullPointerException | NumberFormatException e) {
			System.out.println("Student number missing!");
			throw new Exception("You must type in student number in student number input!");
		}
		
		if (intStudentNumber < 0 || intStudentNumber >= 10000) {
			System.out.println("Student number invalid!: " + intStudentNumber);
			throw new Exception("Student number must be between [0, 10000)!");
		}
		
		if (university == null) {
			throw new Exception("You must type in University name!");
		} else {
			if (university.length() == 0) {
				throw new Exception("You must type in University name!");
			}
		}
		
		
		
	}
}
