package util;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import users.User;

public class UserManager {

	private SQLConnector sqlConnector = new SQLConnector();
	
	public void addUserToDB(String email, String password, String firstName, String lastName, String ip) {
		try {
			sqlConnector.sendQuery("INSERT INTO users VALUES('0', " + "'" + email + "', " + "'" + password + "', " + "'"
					+ firstName + "', " + "'" + lastName + "', " + "'" + ip + "');");

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.printf("New user added to database: %s ; %s", email, password);
	}

	public User getUserByEmail(String email) {
		ResultSet rs = sqlConnector.getData("SELECT * FROM users WHERE email='" + email + "'");
		return createUserByResultSet(rs);
	}

	public User getUserBySessionID(String sessionID) {
		Data data = Data.newInstance();
		String email = data.getEmailBySessionID(sessionID);
		ResultSet rs = null;

		try {
			rs = sqlConnector.getData("SELECT * FROM users WHERE email = '" + email + "';");
			return createUserByResultSet(rs);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return null;
	}

	//This is the only function what actually creates a User
	public User createUserByResultSet(ResultSet rs) {
		try {
			rs.next();
			return new User(rs.getString(1), rs.getString(2), rs.getString(4), rs.getString(5), rs.getString(6));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public User getUserByID(String id) {
		try {
			ResultSet rs = sqlConnector.getData("SELECT * FROM users WHERE id = '" + id + "';");
			return createUserByResultSet(rs);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		return null;
	}

	public boolean userAlredadyExist(String email) throws SQLException {
		ResultSet rs = null;
		try {
			rs = sqlConnector.getData("SELECT id FROM users WHERE email = '" + email + "';");
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		// Returns false if Set is empty
		return rs.next();

	}

	public boolean checkUserLogin(String email, String password) {
		ResultSet rs = null;
		try {
			rs = sqlConnector.getData("SELECT password FROM users WHERE email = '" + email + "';");
			if (rs.next() && password.equals(rs.getString(1))) {
				return true;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return false;
	}

	public User getCurrentUser(HttpServletRequest request)
	{
		return getUserBySessionID(getCookieValue("sessionID", request));
	}
	
	public String getCookieValue(String cookieName, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				if (cookie.getName().equals(cookieName)) {
					return cookie.getValue();
				}
			}
			return null;
		}
		return null;
	}
}
