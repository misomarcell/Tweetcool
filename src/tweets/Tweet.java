package tweets;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Tweet {

	private String id;
	private String author;
	private String date;
	private String content;

	//Used for creatin a tweet after posting it
	public Tweet(String author, String content) {
		super();
		this.author = author;
		this.date = getCurrentDate().toString();
		this.content = content;	
	}
	
	
	//Used for creating a tweet object after reading it from db
	public Tweet(String id, String author, String date, String content)
	{
		this.id = id;
		this.author = author;
		this.date = date;
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

}
