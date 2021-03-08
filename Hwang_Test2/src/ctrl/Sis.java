package ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.EnrollmentBean;
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
		context.setAttribute(modelName, new SisModel());
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
		
		HttpSession session = request.getSession();
		
		boolean isS1Credit = request.getPathInfo() != null && request.getPathInfo().indexOf("S1") >= 0 && request.getParameter("credit") != null;
		boolean isS2 = request.getPathInfo() != null && request.getPathInfo().indexOf("S2") >= 0;
		boolean isS2Report = isS2 && request.getPathInfo().indexOf("Ajax") >= 0;
		
		
		
		boolean isAjaxRequest = request.getPathInfo() != null && request.getPathInfo().indexOf("Ajax") >= 0 && ! isS2Report ;
		
		if (isAjaxRequest) {
			String namePrefix = request.getParameter("nameprefix");
			String minimumCreditTaken = request.getParameter("minimumCreditTaken");
			
			String html;
			PrintWriter out = response.getWriter();
			try {
				html = sisView.studentAsHTML(sisModel, namePrefix, minimumCreditTaken);
				out = response.getWriter();
				out.printf(html);
			} catch (Exception e) {
				out.printf("Error! " + e.getMessage());
			} finally {
				out.flush();
			}
			
		} else {
			if (isS1Credit) {
				Integer credit = Integer.parseInt(request.getParameter("credit"));
				request.setAttribute("credit", credit);
				try {
					
					Map<String, EnrollmentBean> enrollment = null;
					
					enrollment = sisModel.retriveEnrollment(credit);
					
					for (Map.Entry me : enrollment.entrySet()) {
						EnrollmentBean course = (EnrollmentBean) me.getValue();
						for (String sid : course.getStudent()) {
							System.out.println("\t" + course.getCid() + ",\t" + sid + ",\t" + course.getCredit() + "\t ");
						}
						
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			} else if (isS2Report) {	
				Integer credit = Integer.parseInt(request.getParameter("credit"));
				request.setAttribute("credit", credit);
				PrintWriter out = response.getWriter();
				try {
					String html = sisView.enrollmentAsHTML(sisModel, credit);
					out = response.getWriter();
					out.printf(html);
				} catch (Exception e) {
					out.printf("Error! " + e.getMessage());
				} finally {
					out.flush();
				}
				
			} else if (isS2) {
				request.getRequestDispatcher("/S2.html").forward(request, response);
				
			} else {
				request.getRequestDispatcher("/SisApp.jspx").forward(request, response);
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
