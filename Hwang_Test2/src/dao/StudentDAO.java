package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import bean.StudentBean;

public class StudentDAO extends DAO{

	public StudentDAO() throws ClassNotFoundException {
		super();
	}

	public Map<String, StudentBean> retrieve(String namePrefix, int credit_taken) throws SQLException {
		String query = "select * from STUDENTS where SURNAME like '%" + namePrefix + "%' and CREDIT_TAKEN >= " + credit_taken;
		Map<String, StudentBean> rv = new HashMap<String, StudentBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
	
		while (r.next()) {
			String name = r.getString("GIVENNAME") + ", " + r.getString("SURNAME");
			String cseID = r.getString("SID");
			int r_credit_taken = r.getInt("CREDIT_TAKEN");
			int credit_graduate = r.getInt("CREDIT_GRADUATE");
			rv.put(cseID, new StudentBean(cseID, name, r_credit_taken, credit_graduate));
		}
		
		r.close();
		p.close();
		con.close();
		return rv;
	}

}