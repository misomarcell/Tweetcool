package tweets;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import util.SQLConnector;

public class Tweet {

	private String author;
	private String date;
	private String content;
	private SQLConnector sqlConnector = null;

	public Tweet(String author, String content) {

		super();
		this.author = author;
		this.date = getCurrentDate().toString();
		this.content = content;
		sqlConnector = new SQLConnector();
	}

	private String getCurrentDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();

		return dateFormat.format(date);
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

}
