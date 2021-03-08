package ctrl;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Osap
 * 
 */
@WebServlet(name="/Osap", urlPatterns={"/Osap", "/Osap/*"})
public class Osap extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Osap() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		taskA(request, response);
//		taskB(request, response);
//		taskC(request, response);
//		taskD(request, response);
		taskE(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	/**
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void taskE(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		boolean isSubmitButtonHit = request.getParameter("calculate") != null;
		
		if (isSubmitButtonHit) {
			// fetch params from UI page
			String rawPrincipal = request.getParameter("principal");
			String rawAnnualInterestRate = request.getParameter("annualInterestRate");
			String rawPaymentPeriod = request.getParameter("paymentPeriod");
			String rawGracePeriod = request.getParameter("grace");
			
			// parse params
			Double principal = Double.parseDouble(rawPrincipal);
			Double annualInterestRate = Double.parseDouble(rawAnnualInterestRate) / 100;
			Double paymentPeriod = Double.parseDouble(rawPaymentPeriod);
			Boolean isGracePeriod = true;
			if (rawGracePeriod != null) {isGracePeriod = true;} else {isGracePeriod = false;}
			
			// get context init params
			ServletContext context = this.getServletContext();
			
			Double fixedInterest = Double.parseDouble(context.getInitParameter("fixedInterest")) / 100;
			Double gracePeriod = Double.parseDouble(context.getInitParameter("gracePeriod"));
			
			// calculate results
			Double totalInterestRate = fixedInterest + annualInterestRate;
					
			Double graceInterest = principal * ((totalInterestRate) / 12.0) * gracePeriod;
			
			// Interest calculation
			Double monthlyPaymentsWithoutGrace = ((annualInterestRate/12.0)*principal)/(1 - Math.pow((1 + (annualInterestRate/12.0)), -(paymentPeriod)));
			Double montlyPayments;
			if (isGracePeriod) {
				montlyPayments = monthlyPaymentsWithoutGrace + (graceInterest / gracePeriod);
			} else {
				montlyPayments = monthlyPaymentsWithoutGrace;
				graceInterest = 0.0;
			}
			
			request.setAttribute("gracePeriodInterest", String.format("$%.01f", graceInterest)); //persisted in the request	
			request.setAttribute("monthlyPayments", String.format("$%.01f", montlyPayments));
			request.setAttribute("calculationBasedOn", "Calculations are based on a fixed rate based on Prime Rate + 5%");
			
			request.getRequestDispatcher("/Results.jspx").forward(request, response);
		} else { // requested without submission
			request.getRequestDispatcher("/UI.jspx").forward(request, response);
		}
	}	
	
	/**
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 * 
	 * Content-Length = 756
	 * Content-Type = text/html;charset=ISO-8859-1
	 * 
	 * The advantage of using POST is that all parameters are hidden from users on the address line.
	 * 
	 * 
	 */
	private void taskD(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		boolean isSubmitButtonHit = request.getParameter("calculate") != null;
		
		if (isSubmitButtonHit) {
			// fetch params from UI page
			String rawPrincipal = request.getParameter("principal");
			String rawAnnualInterestRate = request.getParameter("annualInterestRate");
			String rawPaymentPeriod = request.getParameter("paymentPeriod");
			String rawGracePeriod = request.getParameter("grace");
			
			// parse params
			Double principal = Double.parseDouble(rawPrincipal);
			Double annualInterestRate = Double.parseDouble(rawAnnualInterestRate) / 100;
			Double paymentPeriod = Double.parseDouble(rawPaymentPeriod);
			Boolean isGracePeriod = true;
			if (rawGracePeriod != null) {isGracePeriod = true;} else {isGracePeriod = false;}
			
			// get context init params
			ServletContext context = this.getServletContext();
			
			Double fixedInterest = Double.parseDouble(context.getInitParameter("fixedInterest")) / 100;
			Double gracePeriod = Double.parseDouble(context.getInitParameter("gracePeriod"));
			
			// calculate results
			Double totalInterestRate = fixedInterest + annualInterestRate;
					
			Double graceInterest = principal * ((totalInterestRate) / 12.0) * gracePeriod;
			
			Double monthlyPaymentsWithoutGrace = ((annualInterestRate/12.0)*principal)/(1 - Math.pow((1 + (annualInterestRate/12.0)), -(paymentPeriod)));
			Double montlyPayments;
			if (isGracePeriod) {
				montlyPayments = monthlyPaymentsWithoutGrace + (graceInterest / gracePeriod);
			} else {
				montlyPayments = monthlyPaymentsWithoutGrace;
				graceInterest = 0.0;
			}
			
			request.setAttribute("gracePeriodInterest", String.format("$%.01f", graceInterest)); //persisted in the request	
			request.setAttribute("monthlyPayments", String.format("$%.01f", montlyPayments));
			request.setAttribute("calculationBasedOn", "Calculations are based on a fixed rate based on Prime Rate + 5%");
			
			request.getRequestDispatcher("/Results.jspx").forward(request, response);
		} else { // requested without submission
			request.getRequestDispatcher("/UI.jspx").forward(request, response);
		}
	}	

	/**
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void taskC(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		boolean isSubmitButtonHit = request.getParameter("calculate") != null;

		if (isSubmitButtonHit) {
			// fetch params from UI page
			String rawPrincipal = request.getParameter("principal");
			String rawAnnualInterestRate = request.getParameter("annualInterestRate");
			String rawPaymentPeriod = request.getParameter("paymentPeriod");
			String rawGracePeriod = request.getParameter("gracePeriod");
			
			// parse params
			Double principal = Double.parseDouble(rawPrincipal);
			Double annualInterestRate = Double.parseDouble(rawAnnualInterestRate) / 100;
			Double paymentPeriod = Double.parseDouble(rawPaymentPeriod);
			Boolean isGracePeriod = true;
			if (rawGracePeriod != null) {isGracePeriod = true;} else {isGracePeriod = false;}
			
			// get context init params
			ServletContext context = this.getServletContext();
			
			Double fixedInterest = Double.parseDouble(context.getInitParameter("fixedInterest")) / 100;
			Double gracePeriod = Double.parseDouble(context.getInitParameter("gracePeriod"));
			
			// calculate results
			Double totalInterestRate = fixedInterest + annualInterestRate;
					
			Double graceInterest = principal * ((totalInterestRate) / 12.0) * gracePeriod;
			
			Double monthlyPaymentsWithoutGrace = ((annualInterestRate/12.0)*principal)/(1 - Math.pow((1 + (annualInterestRate/12.0)), -(paymentPeriod)));
			Double montlyPayments;
			if (isGracePeriod) {
				montlyPayments = monthlyPaymentsWithoutGrace + (graceInterest / gracePeriod);
			} else {
				montlyPayments = monthlyPaymentsWithoutGrace;
			}
			
			request.setAttribute("gracePeriodInterest", String.format("$%.01f", graceInterest)); //persisted in the request	
			request.setAttribute("monthlyPayments", String.format("$%.01f", montlyPayments));
			request.setAttribute("calculationBasedOn", "Calculations are based on a fixed rate based on Prime Rate + 5%");
			
			request.getRequestDispatcher("/Results.jspx").forward(request, response);
		} else { // requested without submission
			request.getRequestDispatcher("/UI.jspx").forward(request, response);
		}
	}
	
	/**
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void taskB(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		boolean isSubmitButtonHit = request.getParameter("calculate") != null;

		if (isSubmitButtonHit) {
			String rawPrincipal = request.getParameter("principal");
			String rawAnnualInterestRate = request.getParameter("annualInterestRate");
			String rawPaymentPeriod = request.getParameter("paymentPeriod");
			String rawGracePeriod = request.getParameter("gracePeriod");
			
			request.setAttribute("gracePeriodInterest", String.format("$%.01f", rawPrincipal)); //persisted in the request	
			request.setAttribute("monthlyPayments", String.format("$%.01f", rawAnnualInterestRate));
			request.setAttribute("calculationBasedOn", "Calculations are based on a fixed rate based on Prime Rate + 5%");
			
			request.getRequestDispatcher("/Results.jspx").forward(request, response);
		} else { // requested without submission
			request.getRequestDispatcher("/UI.jspx").forward(request, response);
		}
	}

}
