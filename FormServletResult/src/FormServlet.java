

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FormServlet
 */
@WebServlet("/FormServlet")
public class FormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String FNAME = "firstName";
	private static final String LNAME = "lastName";
	private static final String EMAIL = "email";
 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// these are samples of how to persist(save/store) data that you get from the browser
		request.setAttribute(FNAME, request.getParameter(FNAME));//persisted in the request	
		request.getSession().setAttribute(LNAME, request.getParameter(LNAME));//persisted in the session
		this.getServletContext().setAttribute(EMAIL, request.getParameter(EMAIL));//persisted in the application 
		
		//this is how you pass the control to another artifact ( html, jsp, other servlet, etc..). Note that you pass also the request and response objects.
		request.getRequestDispatcher("/ServletResult.jspx").forward(request, response);//dispatch the control to a jsp for display..

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// do all computations in doGet
		doGet(request, response);
	}

}
