

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Hello
 */
@WebServlet("/Hello")
public class Hello extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String NAME="name";
	private static final String COMM="comm";
	private static final String AJAX="ajax";
	private static final String FETCH="fetch";
		
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Hello() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		System.out.println("got a request");//for debugging
	    response.getWriter().append("Hi, ").append(request.getParameter(NAME));

	    if (request.getParameter(COMM)==null)//form submission
		    response.getWriter().append(". This is synchronous call" );
		else{//if the comm parameter is set, then is an async call
		
		if (request.getParameter(COMM).equals(AJAX)) {//Ajax call
			System.out.println("got the Ajax request");//for debugging
			response.getWriter().append(". This is Ajax!");	
		}
		
		if (request.getParameter(COMM).equals(FETCH)) {//fetch call
			System.out.println("got the Fetch request");//for debugging
			response.getWriter().append(". This is Fetch!");	
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
