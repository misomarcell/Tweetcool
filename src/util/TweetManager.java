package util;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import tweets.Tweet;

public class TweetManager {

	SQLConnector sqlConnector = new SQLConnector();
	UserManager userManager = new UserManager();
	HttpServletRequest request;
	
	public TweetManager(HttpServletRequest request)
	{
		this.request = request;
	}
	
	public void save(Tweet tweet) {
		try {
			sqlConnector.sendQuery("INSERT INTO tweets VALUES('0', " + "'" + tweet.getAuthor() + "', " + "'"
					+ tweet.getDate() + "', " + "'" + tweet.getContent() + "');");

		} catch (Exception e) {
			System.err.println("ERROR! (TweetManager) save - " + e.getMessage());
		}
	}

	public String getAuthorByTweetId(String tweetID) {
		String authorID = null;
		SQLConnector sqlConnector = new SQLConnector();
		ResultSet rs = sqlConnector.getData("SELECT author FROM tweets WHERE id = '" + tweetID + "'");
		try {
			if (rs.next()) {
				authorID = rs.getString(1);
			} else {
				return null;
			}

		} catch (SQLException e) {
			System.err.println("ERROR! getAuthorByTweetId - " + e.getMessage());
		}

		return authorID;
	}

	public String getTweets(int count) {
		ResultSet rs = null;

		try {
			rs = sqlConnector.getData("SELECT * FROM tweets ORDER BY date DESC;");
		} catch (Exception e) {
			System.err.println("ERROR! getTweets - " + e.getMessage());
		}

		return getFormetTweets(rs, 10);
	}

	public String getTweetsByAuthor(String authorID, int TweetCount) {
		ResultSet rs = null;

		try {
			rs = sqlConnector.getData("SELECT * FROM tweets WHERE author = '" + authorID + "' ORDER BY date DESC;");
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		return getFormetTweets(rs, 10);
	}
	
	public Tweet getTweetByID(String tweetID) {
		ResultSet rs = null;

		try {
			rs = sqlConnector.getData("SELECT * FROM tweets WHERE id = '" + tweetID + "';");
			rs.next();
		} catch (SQLException e) {
			System.err.println("ERROR! getTweetByID - " + e.getMessage());
		}
		Tweet result = getTweetByResultSet(rs);

			
		return result;
	}

	public String getFormetTweets(ResultSet rs, int tweetCount) {
		String result = "";

		try {
			int i = 0;
			while (rs.next()) {
				result += formTweetHTML(getTweetByResultSet(rs), request);

				i++;
				if (i >= tweetCount) {
					break;
				}
			}
		} catch (SQLException e) {
			System.err.println("ERROR! getFormetTweets - " + e.getMessage());
		}

		return result;
	}
	
	public Tweet getTweetByResultSet(ResultSet rs)
	{
		Tweet tweet = null;
		
		try {
			tweet = new Tweet(rs.getString(1), rs.getString(2), rs.getString(3),rs.getString(4));
		} catch (SQLException e) {
			System.err.println("ERROR! getTweetByResultSet - " + e.getMessage());
		}
		
		return tweet;
	}

	private String formTweetHTML(Tweet tweet, HttpServletRequest request) {
		String deleteIcon = "<span class=\"delete-tweet\">" + "<a href=\"?delete=" + tweet.getId()
				+ "\"><i class=\"fa fa-trash-o\" aria-hidden=\"true\"></i></a>" + "</span>";

		if (!tweet.getAuthor().equals(userManager.getCurrentUser(request).getId())) {
			deleteIcon = "";
		}

		String result = "<div class=\"post main-container\">" + "<div class=\"headline\">" + "<span class=\"author\">"
				+ "<a href=\"?author=" + tweet.getAuthor() + "\">" + userManager.getUserByID(tweet.getAuthor()).getFullName() + "</a>"
				+ "</span> - " + "<span class=\"date\">" + tweet.getDate() + "</span>" + deleteIcon + "</div>"
				+ "<div class=\"post-text\">" + tweet.getContent() + "</div>" + "</div>";
		return result;
	}

	public void deleteTweet(Tweet tweet) {
		sqlConnector.sendQuery("DELETE FROM tweets WHERE id = '" + tweet.getId()+ "';");
		System.out.println("Tweet removed: " + tweet.getId());
	}
}
