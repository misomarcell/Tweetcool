package util;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserManager {

	SQLConnector sqlConnector = new SQLConnector();

	public UserManager(){
		try {
			sqlConnector.connectSQL();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addUser(String email, String password, String firstName, String lastName, String ip) {

		// Do the magic
		try {		
			sqlConnector.sendQuery("INSERT INTO users VALUES('0', " + 
			"'" + email + "', " + 
			"'" + password + "', " + 
			"'" + firstName + "', " + 
			"'" + lastName + "', " +
			"'" + ip + "');");

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.printf("New user added to database: %s ; %s", email, password);
	}

	public boolean userAlredadyExist(String email) throws SQLException {
		ResultSet rs = null;
		try {
			 rs = sqlConnector.getData("SELECT id FROM users WHERE email = '" + email +"';");
		} catch (Exception e1) {
			e1.printStackTrace();
		}			
		
		//Returns false if empty set returns
		return rs.next();
	}
	
	public boolean checkUserLogin(String email, String password)
	{
		ResultSet rs = null;
		try {
			 rs = sqlConnector.getData("SELECT password FROM users WHERE email = '" + email +"';");
		} catch (Exception e1) {
			e1.printStackTrace();
		}			
		
		try {			
			if (rs.next() && password.equals(rs.getString(1))){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
