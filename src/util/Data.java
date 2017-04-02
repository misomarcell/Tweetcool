package util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class Data {

	
	private static Data singleton = new Data( );	
	private Data() { }
	private Map<String, String> sessions = new HashMap<>();
	
	
	public static Data newInstance( ) {
		return singleton;
	}
	
	public void addSession(String sessionID, String email)
	{
		sessions.put(sessionID, email);
	}
	
	public Map<String, String> getSessions()
	{
		return sessions;
	}
	
	public Boolean isUserLoggedIn(HttpServletRequest request)
	{
		UserManager um = new UserManager();
		if (sessions.containsKey(um.getCookieValue("sessionID", request)))
		{
			return true;
		}
		
		return false;
	}
	
	public String getEmailBySessionID(String sessionID)
	{
		return sessions.get(sessionID);
	}
}
