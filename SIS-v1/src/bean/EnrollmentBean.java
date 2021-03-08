package bean;

import java.util.ArrayList;

public class EnrollmentBean {
	
	private String cid;
	private ArrayList<String> students;
	private Integer credit;

	public EnrollmentBean(String cid, ArrayList<String> students, Integer credit) {
		super();
		this.cid = cid;
		this.students = students;
		this.credit = credit;
	}
	
	public EnrollmentBean(String cid, String student, Integer credit) {
		super();
		this.cid = cid;
		this.students = new ArrayList<String>();
		this.students.add(student);
		this.credit = credit;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public void addStudent(String sid) {
		this.students.add(sid);
	}
	
	public ArrayList<String> getStudent() {
		return this.students;
	}

	public Integer getCredit() {
		return credit;
	}

	public void setCredit(Integer credit) {
		this.credit = credit;
	}

	

}
