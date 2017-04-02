package tweets;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import util.SQLConnector;

public class Tweet {

	private String id;
	private String author;
	private String date;
	private String content;
	private SQLConnector sqlConnector = new SQLConnector();

	public Tweet(String author, String content) {
		super();
		this.author = author;
		this.date = getCurrentDate().toString();
		this.content = content;	
	}

	private String getCurrentDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();

		return dateFormat.format(date);
	}

	public String getId() {
		return id;
	}
	
	public String getAuthor() {
		return author;
	}

	public String getDate() {
		return date;
	}

	public String getContent() {
		return content;
	}

	public void save() {
		try {
			sqlConnector.sendQuery("INSERT INTO tweets VALUES('0', " + "'" + author + "', " + "'" + date + "', " + "'"
					+ content + "');");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getAuthorById(String tweetID)
	{
		String authorID = null;
		SQLConnector sqlConnector = new SQLConnector();
		ResultSet rs = sqlConnector.getData("SELECT author FROM tweets WHERE id = '" + tweetID + "'");
		try {
			if(rs.next())
			{
				authorID = rs.getString(1);
			}else{
				return null;
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return authorID;
		
	}
}
