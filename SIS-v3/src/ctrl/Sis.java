package ctrl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.SisModel;
import view.View;

/**
 * Servlet implementation class Sis
 */
@WebServlet(name="/Sis", urlPatterns={"/Sis", "/Sis/*"})
public class Sis extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String modelName = "sisModel";
	private static final String viewName = "sisView";
    

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ServletContext context = getServletContext();
		// Instantiate Loan object
		
		SisModel sis;
		try {
			sis = SisModel.getInstance();
			context.setAttribute(modelName, sis);
		} catch (ClassNotFoundException e) {
			throw new ServletException("Class Not Found" + e);
		}
		
		
		context.setAttribute(viewName, new View());
	}
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sis() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = request.getServletContext();
		SisModel sisModel = (SisModel) context.getAttribute(modelName);
		View sisView = (View) context.getAttribute(viewName);
		
		Boolean isAjaxRequest = request.getPathInfo() != null && request.getPathInfo().indexOf("Ajax") >= 0 ;
		
		Boolean isXMLRequest = request.getParameter("report") != null && request.getParameter("report").equals("XML");
		
		Boolean isJSONRequest = request.getParameter("report") != null && request.getParameter("report").equals("JSON");
		
		System.out.println(request.getPathInfo() + " " + request.getParameter("report"));
		
		String namePrefix = request.getParameter("nameprefix");
		String minimumCreditTaken = request.getParameter("minimumCreditTaken");
		
		if(isAjaxRequest || isXMLRequest || isJSONRequest) {
			String outStr = "";
			PrintWriter out = response.getWriter();
			try {
				if (isAjaxRequest) {
					outStr = sisView.studentAsHTML(sisModel, namePrefix, minimumCreditTaken);
				} else if (isXMLRequest) {
					outStr = sisModel.exportXML(namePrefix, minimumCreditTaken);
				} else if (isJSONRequest) {
					outStr = sisModel.exportJSON(namePrefix, minimumCreditTaken);
				}
				
				out = response.getWriter();
				out.printf(outStr);
			} catch (Exception e) {
				out.printf("Error! " + e.getMessage());
				e.printStackTrace();
			} finally {
				out.flush();
			}
		} else {
			request.getRequestDispatcher("/SisApp.jspx").forward(request, response);
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
