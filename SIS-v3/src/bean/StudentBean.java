package bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.FIELD)
public class StudentBean {
	
	@XmlAttribute
	private String sid;
	@XmlAttribute
	private String name;
	@XmlAttribute
	private Integer credit_taken;
	@XmlAttribute
	private Integer credit_graduate;
	
	public StudentBean() {
		
	}
	
	public StudentBean(String sid, String name, Integer credit_taken, Integer credit_graduate) {
		this.sid = sid;
		this.name = name;
		this.credit_taken = credit_taken;
		this.credit_graduate = credit_graduate;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCredit_taken() {
		return credit_taken;
	}

	public void setCredit_taken(Integer credit_taken) {
		this.credit_taken = credit_taken;
	}

	public Integer getCredit_graduate() {
		return credit_graduate;
	}

	public void setCredit_graduate(Integer credit_graduate) {
		this.credit_graduate = credit_graduate;
	}

}
