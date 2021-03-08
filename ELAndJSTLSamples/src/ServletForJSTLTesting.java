
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletForJSTLTesting
 */
@WebServlet("/ServletForJSTLTesting")
public class ServletForJSTLTesting extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletForJSTLTesting() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// save an array into the session
		String[] names = { "John", "Bob", "Cris" };
		request.getSession().setAttribute("names", names);

		// save a complex type into the session
		Car aCar = new Car("Tesla", 2013, "red");
		request.getSession().setAttribute("car", aCar);

		// save a financial number into the request
		double amount = 88888.8000;
		request.setAttribute("money", amount);

		// save several attributes with the same name in different scopes
		request.setAttribute("x", 1);
		request.getSession().setAttribute("x", 2);
		
		this.getServletContext().setAttribute("x", 3);

		// save the application name
		request.getSession().setAttribute("applicationName", "JSTL Examples");

		// now check JSTLPageSample.jspx how these attributes are read and displayed..

		request.getRequestDispatcher("/JSTLPageSample.jspx").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
