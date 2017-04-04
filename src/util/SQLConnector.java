package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLConnector {

	Connection conn;

	public SQLConnector(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/tweetcool", "admin", "admin");
			System.out.println("New SQL connection!");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void disconnect()
	{
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void sendQuery(String query) {	
		try {
			Statement stmt = conn.createStatement();
			System.out.println("Sending Query: " + query);
			stmt.executeUpdate(query);
			
		} catch (Exception e) {
			System.err.println("sendQuery - Got an exception: ");
			System.err.println(e.getMessage());
		}
	}

	public ResultSet getData(String query) {
		ResultSet rs = null;
		try {
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
		} catch (Exception e) {
			System.err.println("getData - Got an exception: ");
			System.err.println(e.getMessage());
		}
		
		return rs;
	}	
}
