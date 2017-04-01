package util;

import java.sql.*;

public class SQLConnector {

	Connection conn;

	public void connectSQL() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/tweetcool", "admin", "admin");
		System.out.println("New SQL connection!");
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
