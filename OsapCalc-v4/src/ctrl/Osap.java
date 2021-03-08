package ctrl;

import java.io.IOException;
import java.io.PrintWriter;
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
		
		System.out.println("Get request.");
		
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
		HttpSession session = request.getSession();
		
		ServletContext context = this.getServletContext();
		
		context.setAttribute("resultLegendName", context.getInitParameter("resultLegendName"));
		context.setAttribute("UILegnedName", context.getInitParameter("UILegnedName"));
		
		if (request.getPathInfo() != null) {
			System.out.println( request.getPathInfo().indexOf("Ajax"));
			System.out.println("button " + request.getParameter("calculate") != null);
		}
		
		if (isSubmitButtonHit) {
			// fetch params from UI page
			String rawPrincipal = request.getParameter("principal");
			String rawAnnualInterestRate = request.getParameter("annualInterestRate");
			String rawPaymentPeriod = request.getParameter("paymentPeriod");
			String rawGrace = request.getParameter("grace");
			
			// get context init params
			
			
			String rawFixedInterest = context.getInitParameter("fixedInterest");
			String rawGracePeriod = context.getInitParameter("gracePeriod");

			Loan loan = (Loan) context.getAttribute("mLoan");
			
			Double graceInterest = 0.0;
			Double montlyPayments = 0.0;
			try {
				graceInterest = loan.computeGraceInterest(rawPrincipal, rawGracePeriod, rawAnnualInterestRate, rawFixedInterest);
				montlyPayments  = loan.computeMonthlyPayment(rawPrincipal, rawAnnualInterestRate, rawPaymentPeriod, rawGrace, rawGracePeriod, rawFixedInterest);
				
				System.out.println("Button press.");
				
				if (request.getPathInfo() != null && request.getPathInfo().indexOf("Ajax") >= 0) {
					response.setContentType("application/json");
					PrintWriter out = response.getWriter();
					out.printf("Grace Period Interest: $%.2f<br/>Monthly payment: $%.2f", graceInterest, montlyPayments);
					out.flush();
				} else {
					
					request.setAttribute("gracePeriodInterest", graceInterest); //persisted in the request	
					request.setAttribute("monthlyPayments", montlyPayments);
					request.setAttribute("calculationBasedOn", "Calculations are based on a fixed rate based on Prime Rate + 5%");
					
					session.setAttribute("principal", rawPrincipal);
					session.setAttribute("annualInterestRate", rawAnnualInterestRate);
					session.setAttribute("paymentPeriod", rawPaymentPeriod);
					session.setAttribute("grace", rawGrace);
					
					request.setAttribute("principal", rawPrincipal);
					request.setAttribute("annualInterestRate", rawAnnualInterestRate);
					request.setAttribute("paymentPeriod", rawPaymentPeriod);
					request.setAttribute("grace", rawGrace);
					
					request.getRequestDispatcher("/Results.jspx").forward(request, response);
				}
				
				
			} catch (Exception e) {
				if (request.getPathInfo() != null && request.getPathInfo().indexOf("Ajax") >= 0) {
					response.setContentType("application/json");
					PrintWriter out = response.getWriter();
					out.printf("%s", e.getMessage());
					out.flush();
				} else {
					request.setAttribute("errormsg", e.getMessage());
					
					request.setAttribute("principal", rawPrincipal);
					request.setAttribute("annualInterestRate", rawAnnualInterestRate);
					request.setAttribute("paymentPeriod", rawPaymentPeriod);
					request.setAttribute("grace", rawGrace);
					
					request.getRequestDispatcher("/UI.jspx").forward(request, response);
				}
			}

			
			
		} else { // requested without submission
			
			
			String rawPrincipal = (String) session.getAttribute("principal");
			String rawAnnualInterestRate = (String) session.getAttribute("annualInterestRate");
			String rawPaymentPeriod = (String) session.getAttribute("paymentPeriod");
			String rawGracePeriod = (String) session.getAttribute("grace");
			
			request.setAttribute("principal", rawPrincipal);
			request.setAttribute("annualInterestRate", rawAnnualInterestRate);
			request.setAttribute("paymentPeriod", rawPaymentPeriod);
			request.setAttribute("grace", rawGracePeriod);
			
			request.getRequestDispatcher("/UI.jspx").forward(request, response);
		}
	}	
	

}
