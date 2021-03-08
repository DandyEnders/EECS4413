package model;

import java.util.Map;

import bean.EnrollmentBean;
import bean.StudentBean;
import dao.EnrollmentDAO;
import dao.StudentDAO;

public class SisModel {
	
	private StudentDAO studentDAO;
	private EnrollmentDAO enrollmentDAO;
	

	public SisModel() {
		try {
			this.studentDAO = new StudentDAO();
			this.enrollmentDAO = new EnrollmentDAO();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Map<String, StudentBean> retriveStudent(String namePrefix, String credit_taken) throws Exception {
		int credit = Integer.parseInt(credit_taken);
		
		// remove semicolon to prevent multiple execution
		namePrefix = namePrefix.replace(";", "");
		
		Map<String, StudentBean> result = this.studentDAO.retrieve(namePrefix, credit);
		
		return result;
		
	}
	
	public Map<String, EnrollmentBean> retriveEnrollment() throws Exception {
		Map<String, EnrollmentBean> result = this.enrollmentDAO.retrieve();
		
		return result;
		
	}
	
	public Map<String, EnrollmentBean> retriveEnrollment(Integer credit) throws Exception {
		Map<String, EnrollmentBean> result = this.enrollmentDAO.retrieveWithCredit(credit);
		
		return result;
		
	}

}
