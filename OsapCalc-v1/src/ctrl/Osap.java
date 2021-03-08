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
//		taskC(request, response);
//		taskD(request, response);
		taskE(request, response);
		
	}
	
	/**
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 * 
	 * Try
	 * http://localhost:8080/OsapCalc-v1/Osap?principal=10000&interest=10&period=24
	 * http://localhost:8080/OsapCalc-v1/Osap
	 */
	private void taskE(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String helloWorld = "Hello, World!\n";
		
		// String defs for first section
		String clientIP = request.getRemoteAddr();
		String clientPort = request.getRemotePort() + "";
		String httpProtocol = request.getProtocol();
		String method = request.getMethod();
		String queryString = request.getQueryString();
		String queryParam = "foo=" + request.getParameter("foo");
		String requestURI = request.getRequestURI();
		String requestServletPath = request.getPathInfo();
		
		// String defs for second section
		
		String strClientIP = "Client IP: " + clientIP + "\n"; 
		String strClientPort = "Client Port: " + clientPort + "\n";
		String strHttpProtocol = "Client Protocol: " + httpProtocol + "\n";
		String strMethod = "Client Method: " + method + "\n";
		String strQueryString = "Query String: " + queryString + "\n";
		String strQueryParam = "Query Param: " + queryParam + "\n";
		String strRequestURI = "Request URI: " + requestURI + "\n";
		String strRequestServletPath = "Reuqest Servlet Path: " + requestServletPath + "\n";
		
		String rawPrincipal = request.getParameter("principal");
		String rawPeriod = request.getParameter("period");
		String rawInterest = request.getParameter("interest");
		
		// String defs for third section
		
		ServletContext context = this.getServletContext();
		
		String applicantName = context.getInitParameter("applicantName");
		String applicationName = context.getInitParameter("applicationName");
		
		String strApplicationName = "Application Name=" + applicationName + "\n";
		String strContextPath = "Context Path=" + context.getContextPath() + "\n";
		String strRealPath = "Real Path of Osap\nservlet=" + context.getRealPath("Osap") + "\n";
		String strApplicantName = "Applicant Name=" + applicantName + "\n";
		
		Double principal = Double.parseDouble(context.getInitParameter("principal"));
		Double period = Double.parseDouble(context.getInitParameter("period"));
		Double interest = Double.parseDouble(context.getInitParameter("interest"));
		
		if (rawPrincipal != null) {principal = Double.parseDouble(rawPrincipal);}
		if (rawPeriod != null) {period = Double.parseDouble(rawPeriod);}
		if (rawInterest != null) {interest = Double.parseDouble(rawInterest) / 100;}
		
		Double monthlyPayments = ((interest/12.0)*principal)/(1 - Math.pow((1 + (interest/12.0)), -period));
		
		String strPrincipal = String.format("Principal=%.01f", principal);
		String strPeriod = String.format("Period=%.01f", period);
		String strInterest = String.format("Interest=%.01f", interest);
		
		String strBasedOn = "Based on " + strPrincipal + " " + strPeriod + " " + strInterest + "\n";
		String strMonthlyPayments = "Monthly payments: " + String.format("%.01f", monthlyPayments) + "\n";
		
		// Write output
		
		response.setContentType("text/plain");
		Writer resOut = response.getWriter();
		resOut.write(helloWorld);
		resOut.write(strClientIP);
		resOut.write(strClientPort);
		if (clientIP.equals("0:0:0:0:0:0:0:1")) {
			resOut.write("This IP has been flagged!\n");
		}
		resOut.write(strHttpProtocol);
		resOut.write(strMethod);
		resOut.write(strQueryString);
		resOut.write(strQueryParam);
		resOut.write(strRequestURI);
		resOut.write(strRequestServletPath);
		
		// if user entered all the params
		if (rawPrincipal != null && rawPeriod != null && rawInterest != null) {
			resOut.write("---- Info from context object ----\n");
			
			resOut.write(strApplicationName);
			resOut.write(strContextPath);
			resOut.write(strRealPath);
			resOut.write(strApplicantName);
			
			resOut.write("---- Monthly payments ----\n");
			
			resOut.write(strBasedOn);
			resOut.write(strMonthlyPayments);
			
			// Save the session
			
			HttpSession session = request.getSession(true);
			session.setAttribute("strApplicationName", strApplicationName);
			session.setAttribute("strApplicantName", strApplicantName);
			session.setAttribute("strPrincipal", strPrincipal);
			session.setAttribute("strPeriod", strPeriod);
			session.setAttribute("strInterest", strInterest);
			
		// if use did not enter all params (or partially)
		} else {
			resOut.write("---- Info from context object ----\n");
			
			resOut.write(strApplicationName);
			resOut.write(strContextPath);
			resOut.write(strRealPath);
			resOut.write(strApplicantName);
			
			HttpSession session = request.getSession();
			
			// If session already exists, use the information instead
			
			if (session.getAttribute("strApplicationName") != null) {
				resOut.write("---- Session info ----\n");
				
				resOut.write((String) session.getAttribute("strApplicationName"));
				resOut.write((String) session.getAttribute("strApplicantName"));
				resOut.write((String) session.getAttribute("strPrincipal") + "\n");
				resOut.write((String) session.getAttribute("strPeriod") + "\n");
				resOut.write((String) session.getAttribute("strInterest") + "\n");
				
			}
			
			resOut.write("---- Monthly payments ----\n");
			
			resOut.write(strBasedOn);
			resOut.write(strMonthlyPayments);
		}
		
		
		System.out.println("Hello, Got a GET request from Osap!");
		
		// To test exception page
		if (requestServletPath != null) {
			if (requestServletPath.equals("/Exception")) {
				throw new ServletException("Exception raised!");
			}
		}
		
	}	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
