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

/**
 * Servlet implementation class Osap
 * 
 * Test it with
 * http://localhost:8080/OsapCalc-v1/Osap/aSamplePath?foo=bar
 * http://localhost:8080/OsapCalc-v1/Osap/Exception
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
		String helloWorld = "Hello, World!\n";
		
		String clientIP = request.getRemoteAddr();
		String clientPort = request.getRemotePort() + "";
		String httpProtocol = request.getProtocol();
		String method = request.getMethod();
		String queryString = request.getQueryString();
		String queryParam = "";
		
		Map<String, String[]> map = request.getParameterMap();
		
		for (String key: map.keySet()) {
			queryParam += key + "=" + map.get(key)[0] + ", ";
		}
		// if there are parameters, remove last ", "
		if (!queryParam.isEmpty()) {
			queryParam = queryParam.substring(0, queryParam.length() - 2);
		}
		
		String requestURI = request.getRequestURI();
		String requestServletPath = request.getPathInfo();
		
		String strClientIP = "Client IP: " + clientIP + "\n"; 
		String strClientPort = "Client Port: " + clientPort + "\n";
		String strHttpProtocol = "Client Protocol: " + httpProtocol + "\n";
		String strMethod = "Client Method: " + method + "\n";
		String strQueryString = "Query String: " + queryString + "\n";
		String strQueryParam = "Query Param: " + queryParam + "\n";
		String strRequestURI = "Request URI: " + requestURI + "\n";
		String strRequestServletPath = "Reuqest Servlet Path: " + requestServletPath + "\n";
		
		
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
		
		resOut.write("---- Info from context object ----\n");
		
		ServletContext context = this.getServletContext();
		
		String applicantName = context.getInitParameter("applicantName");
		String applicationName = context.getInitParameter("applicationName");
//		Double principal = Double.parseDouble(getInitParameter("principal"));
		
		String strApplicationName = "Application Name=" + applicationName + "\n";
		String strContextPath = "Context Path=" + context.getContextPath() + "\n";
		String strRealPath = "Real Path of Osap\nservlet=" + context.getRealPath("Osap") + "\n";
		String strApplicantName = "Applicant Name=" + applicantName + "\n";
		
		resOut.write(strApplicationName);
		resOut.write(strContextPath);
		resOut.write(strRealPath);
		resOut.write(strApplicantName);
		
		System.out.println("Hello, Got a GET request from Osap!");
		
		if (requestServletPath.equals("/Exception")) {
			throw new ServletException("Exception raised!");
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
