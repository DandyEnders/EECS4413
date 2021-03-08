package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import bean.EnrollmentBean;
import bean.StudentBean;

public class EnrollmentDAO extends DAO {
	
	public EnrollmentDAO() throws ClassNotFoundException {
		super();
	}
	
	public Map<String, EnrollmentBean> retrieve() throws SQLException {
		String query = "select * from enrollment";
		Map<String, EnrollmentBean> rv = new HashMap<String, EnrollmentBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while (r.next()) {
			String cid = r.getString("CID");
			String sid = r.getString("SID");
			Integer credit = r.getInt("CREDIT");
			
			if (rv.containsKey(cid)) {
				rv.get(cid).addStudent(sid);
			} else {
				rv.put(cid, new EnrollmentBean(cid, sid, credit));
			}
			
		}
		r.close();
		p.close();
		con.close();
		return rv;
	}
}
