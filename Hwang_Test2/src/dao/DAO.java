package dao;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

abstract class DAO {
	
	protected DataSource ds;
	
	public DAO() throws ClassNotFoundException {
		DataSource ds = null;
		
		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch (NamingException e) {
			e.printStackTrace();
		}

		this.ds = ds;
	}

}
