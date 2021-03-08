package view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import bean.EnrollmentBean;
import bean.StudentBean;
import model.SisModel;

public class View {

	public View() {
		// TODO Auto-generated constructor stub
	}

	public String studentAsHTML(SisModel model, String namePrefix, String credit_taken) throws Exception {
		String html = "";
		Map<String, StudentBean> hmap = null;

		hmap = model.retriveStudent(namePrefix, credit_taken);


		html = "<table border=\"1\"> <thead> <td>Student Name </td> <td>Credits taken </td> <td>Credits toGraduate </td> </thead>";
		Iterator iterator = hmap.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry me = (Map.Entry) iterator.next();
			StudentBean sb = (StudentBean) me.getValue();
			html += "<tr>" + "<td>" + sb.getName() + "</td>" + "<td>" + sb.getCredit_taken() + "</td>" + "<td>"
					+ sb.getCredit_graduate() + "</td>" + "</tr>";
		}
		html += "</thead></table>";
		return html;
	}
	
	public String enrollmentAsHTML(SisModel model) throws Exception {
		String html = "";
		Map<String, EnrollmentBean> hmap = null;

		hmap = model.retriveEnrollment();


		html = "<table border=\"1\"> <thead> <td>Course Name </td> <td>Student ID</td> <td>Course Credit</td> </thead>";
		Iterator iterator = hmap.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry me = (Map.Entry) iterator.next();
			EnrollmentBean sb = (EnrollmentBean) me.getValue();
			ArrayList<String> studentList = sb.getStudent();
			for (String sid : studentList) {
				html += "<tr>" + "<td>" + sb.getCid() + "</td>" + "<td>" + sid + "</td>" + "<td>"
						+ sb.getCredit() + "</td>" + "</tr>";
			}
			
		}
		html += "</thead></table>";
		return html;
	}

}
