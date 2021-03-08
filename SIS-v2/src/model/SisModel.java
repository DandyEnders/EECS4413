package model;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamResult;

import bean.EnrollmentBean;
import bean.ListWrapper;
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

	public String exportXML(String namePrefix, String credit_taken) throws Exception {
		int credit = Integer.parseInt(credit_taken);

		Map<String, StudentBean> result = this.studentDAO.retrieve(namePrefix, credit);

		List<StudentBean> studentbeanList = new ArrayList<StudentBean>();

		for (StudentBean sb : result.values()) {
			studentbeanList.add(sb);
		}

		ListWrapper lw = new ListWrapper(namePrefix, credit, studentbeanList);

		JAXBContext jc = JAXBContext.newInstance(lw.getClass());
		Marshaller marshaller = jc.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
		StringWriter sw = new StringWriter();
		sw.write("\n");
		marshaller.marshal(lw, new StreamResult(sw));

		return sw.toString();
	}

	public String exportJSON(String namePrefix, String credit_taken) throws Exception {
		int credit = Integer.parseInt(credit_taken);

		JsonObjectBuilder doc = Json.createObjectBuilder();
		doc.add("credit_taken", credit_taken).add("namePrefix", namePrefix);

		JsonArrayBuilder students = Json.createArrayBuilder();

		Map<String, StudentBean> studentMap = this.studentDAO.retrieve(namePrefix, credit);

		for (StudentBean sb : studentMap.values()) {
			JsonObjectBuilder student = Json.createObjectBuilder().add("name", sb.getName())
					.add("creditsTaken", sb.getCredit_taken()).add("creditsToGraduate", sb.getCredit_graduate());
			students.add(student);
		}

		doc.add("students", students);

		JsonObject value = doc.build();

		String result = value.toString();

		return result;
	}

}
