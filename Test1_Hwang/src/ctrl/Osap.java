package ctrl;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exceptions.AnnualInterestNegativeException;
import exceptions.PaymentPeriodNegativeException;
import exceptions.PrincipalNegativeException;
import model.Loan;

/**
 * Servlet implementation class Osap
 * 
 */
@WebServlet(name="/Osap", urlPatterns={"/Osap", "/Osap/*"})
public class Osap extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * 
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ServletContext context = getServletContext();
		// Instantiate Loan object
		context.setAttribute("mLoan", new Loan());
		context.setAttribute("resultLegendName", context.getInitParameter("resultLegendName"));
		context.setAttribute("UILegnedName", context.getInitParameter("UILegnedName"));
	}
	
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
		HttpSession session = request.getSession();
		ServletContext context = this.getServletContext();
		Loan loan = (Loan) context.getAttribute("mLoan");
		
		boolean isSubmitButtonHit = request.getParameter("calculate") != null;
		boolean isRecomputeButtonHit = request.getParameter("reCompute") != null;
		boolean isExtended = false;
		
		if (session.getAttribute("isExtended") != null) {
			if (session.getAttribute("isExtended").equals("true")) {
				isExtended = true;
			}
		}
		
		if (request.getParameter("p") != null) {
			if (request.getParameter("p").equals("Extended")) {
				isExtended = true;
			}
		}
		
		if (isExtended) {
			session.setAttribute("isExtended", "true");
		}else {
			session.setAttribute("isExtended", "false");
		}
		
		if (isSubmitButtonHit) {
			// fetch params from UI page
			String rawPrincipal = request.getParameter("principal");
			String rawAnnualInterestRate = request.getParameter("annualInterestRate");
			String rawPaymentPeriod = request.getParameter("paymentPeriod");
			String rawGrace = request.getParameter("grace");
			
			String rawStudentNumber = request.getParameter("studentNumber");
			String rawUniversity = request.getParameter("university");
			
			String rawFixedInterest = context.getInitParameter("fixedInterest");
			String rawGracePeriod = context.getInitParameter("gracePeriod");

			Double graceInterest = 0.0;
			Double montlyPayments = 0.0;
			
			
			try {
				// Is University == York University?
				Boolean isYork = false;
				if (isExtended) {
					loan.validateStudentNumberUniversity(rawStudentNumber, rawUniversity);
					if (rawUniversity.equals("York University")) {
						isYork = true;
					}
				}
				
				// If University = York University, deduct 0.5% down from interest
				graceInterest = loan.computeGraceInterest(rawPrincipal, rawGracePeriod, rawAnnualInterestRate, rawFixedInterest);
				montlyPayments  = loan.computeMonthlyPayment(rawPrincipal, rawAnnualInterestRate, rawPaymentPeriod, rawGrace, rawGracePeriod, rawFixedInterest, isYork);
				
				
				// set attributes for result page
				request.setAttribute("gracePeriodInterest", graceInterest); //persisted in the request	
				request.setAttribute("monthlyPayments", montlyPayments);
				request.setAttribute("calculationBasedOn", "Calculations are based on a fixed rate based on Prime Rate + 5%");
				
				session.setAttribute("principal", rawPrincipal);
				session.setAttribute("annualInterestRate", rawAnnualInterestRate);
				session.setAttribute("paymentPeriod", rawPaymentPeriod);
				session.setAttribute("grace", rawGrace);
				if (isExtended) {
					session.setAttribute("studentNumber", rawStudentNumber);
					session.setAttribute("university", rawUniversity);
					
					session.setAttribute("isExtended", "true");
					request.getRequestDispatcher("/ResultsExtended.jspx").forward(request, response);
				} else {
					request.getRequestDispatcher("/Results.jspx").forward(request, response);
				}
				
			// When error occurs, like out of parameter range.
			} catch (Exception e) {
				request.setAttribute("errormsg", e.getMessage());
				
				request.setAttribute("principal", rawPrincipal);
				request.setAttribute("annualInterestRate", rawAnnualInterestRate);
				request.setAttribute("paymentPeriod", rawPaymentPeriod);
				request.setAttribute("grace", rawGrace);
				
				if (isExtended) {
					request.setAttribute("studentNumber", rawStudentNumber);
					request.setAttribute("university", rawUniversity);
					
					session.setAttribute("isExtended", "true");
					request.getRequestDispatcher("/UIExtended.jspx").forward(request, response);
				} else {
					session.setAttribute("isExtended", "false");
					request.getRequestDispatcher("/UI.jspx").forward(request, response);
				}
				
				
			}

			
			
		} else { // Back to UI
			
			// When it is coming back from result
			if (isRecomputeButtonHit) {
				String rawPrincipal = (String) session.getAttribute("principal");
				String rawAnnualInterestRate = (String) session.getAttribute("annualInterestRate");
				String rawPaymentPeriod = (String) session.getAttribute("paymentPeriod");
				String rawGracePeriod = (String) session.getAttribute("grace");
				
				request.setAttribute("principal", rawPrincipal);
				request.setAttribute("annualInterestRate", rawAnnualInterestRate);
				request.setAttribute("paymentPeriod", rawPaymentPeriod);
				request.setAttribute("grace", rawGracePeriod);
				
				session.setAttribute("isExtended", "false");
				request.getRequestDispatcher("/UI.jspx").forward(request, response);
			
			// When it is fresh call
			} else {
				String rawPrincipal = (String) session.getAttribute("principal");
				String rawAnnualInterestRate = (String) session.getAttribute("annualInterestRate");
				String rawPaymentPeriod = (String) session.getAttribute("paymentPeriod");
				String rawGracePeriod = (String) session.getAttribute("grace");
				
				String rawStudentNumber = (String) session.getAttribute("studentNumber");
				String rawUniversity = (String) session.getAttribute("university");
				
				request.setAttribute("principal", rawPrincipal);
				request.setAttribute("annualInterestRate", rawAnnualInterestRate);
				request.setAttribute("paymentPeriod", rawPaymentPeriod);
				request.setAttribute("grace", rawGracePeriod);
				
				if (isExtended) {
					request.setAttribute("studentNumber", rawStudentNumber);
					request.setAttribute("university", rawUniversity);
					
					session.setAttribute("isExtended", "true");
					request.getRequestDispatcher("/UIExtended.jspx").forward(request, response);
				} else {
					session.setAttribute("isExtended", "false");
					request.getRequestDispatcher("/UI.jspx").forward(request, response);
				}
			}
			
			
			
			
		}
	}	
	

}
