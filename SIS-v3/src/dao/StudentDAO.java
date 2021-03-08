package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;

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
	
	
	public int restInsert(String sid, String givenName, String surName, int creditTake, int creditGraduate) throws SQLException, NamingException {
		String preparedStatement = "insert into students values(?,?,?,?,?)";
		
		Connection con = this.ds.getConnection();
		PreparedStatement stmt = con.prepareStatement(preparedStatement);
		stmt.setString(1, sid);
		stmt.setString(2, givenName);
		stmt.setString(3, surName);
		stmt.setInt(4, creditTake);
		stmt.setInt(5, creditGraduate);
		
		int result = stmt.executeUpdate();
		
		stmt.close();
		con.close();
		
		return result;
	}
	
	public int restDelete(String sid) throws SQLException, NamingException{
		String preparedStatement = "delete from students where sid=?";
		Connection con = this.ds.getConnection();
		PreparedStatement stmt = con.prepareStatement(preparedStatement);
		stmt.setString(1, sid);
		
		int result = stmt.executeUpdate();
		
		stmt.close();
		con.close();
		
		return result;
	}

}