package ctrl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Analytics
 */
/* this servlet 
		 * 1. check if there is attribute in the context called "count" 
		 * 2. if yes, then it writes it back to the client
*/  
@WebServlet("/ParametersCount")
public class ParametersCount extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
     
    /**
     * @see HttpServlet#HttpServlet()
     */
    
	public ParametersCount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		response.setContentType("text/plain");
		//we report the count attribute..
		response.getWriter().append("firstName parameter was sent: ");
		PrintWriter wr = response.getWriter();
		 if (this.getServletContext().getAttribute("count")!=null)
		   wr.write(this.getServletContext().getAttribute("count").toString());
		 wr.write("times");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
