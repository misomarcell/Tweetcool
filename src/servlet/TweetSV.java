package servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tweets.Tweet;
import users.User;
import util.Data;
import util.SQLConnector;
import util.UserManager;


public class TweetSV extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private UserManager userManager = new UserManager();
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TweetSV() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.request = request;
		Data data = Data.newInstance();
		
		//Check if user still logged in
		if (!data.isUserLoggedIn(request)) {
			response.sendRedirect("./login");
			return;
		}

		User user = userManager.getUserBySessionID(userManager.getCookieValue("sessionID", request));

		request.setAttribute("name", user.getFirstName());

		if (request.getParameter("delete") != null) {
			deleteTweet(request.getParameter("delete"));
		}
		
		if (request.getParameter("author") != null) {
			String authorID = request.getParameter("author").toString();
			
			request.setAttribute("tweets", getTweetsByAuthor(authorID, 10));
			request.setAttribute("message", "<div class=\"message info\">Showing tweets from: " + userManager.getUserByID(authorID).getFullName() + 
					"<span style=\"float: right;\"><a href=\"./\">Vissza</a></span></div>");
		} else {
			request.setAttribute("tweets", getTweets(10));
		}

		request.getRequestDispatcher("/tweets.jsp").forward(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.request = request;
		User user = userManager.getUserBySessionID(userManager.getCookieValue("sessionID", request));

		request.setCharacterEncoding("UTF-8");
		if (!request.getParameter("tweet").equals("")) {
			String content = request.getParameter("tweet");
			Tweet tweet = new Tweet(user.getId(), clearContent(content));
			tweet.save();
		}

		request.setAttribute("name", user.getFirstName());
		request.setAttribute("tweets", getTweets(10));

		request.getRequestDispatcher("/tweets.jsp").forward(request, response);
	}

	public String clearContent(String content) {
		String result = content;
		result = result.replace("<", "&lt;");

		return result;
	}


	public String getTweets(int count) {
		SQLConnector sqlConnector = new SQLConnector();
		ResultSet rs = null;

		try {
			rs = sqlConnector.getData("SELECT * FROM tweets ORDER BY date DESC;");
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		return getTweetsByResultSet(rs, 10);
	}

	public String getTweetsByAuthor(String authorID, int TweetCount) {
		SQLConnector sqlConnector = new SQLConnector();
		ResultSet rs = null;
		
		try {
			rs = sqlConnector.getData("SELECT * FROM tweets WHERE author = '" + authorID + "' ORDER BY date DESC;");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		return getTweetsByResultSet(rs, 10);
	}

	public String getTweetsByResultSet(ResultSet rs, int tweetCount) {
		String result = "";
		
		try {
			int i = 0;
			while (rs.next()) {
				result += getFormedTweet(userManager.getUserByID(rs.getString(2)).getFullName(), rs.getString(4),
						rs.getString(3), rs.getString(2), rs.getString(1));

				i++;
				if (i >= tweetCount) {
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	private String getFormedTweet(String authorName, String content, String date, String authorID, String tweetID) {
		String deleteString = "<span class=\"delete-tweet\">" +
				"<a href=\"?delete=" + tweetID + "\"><i class=\"fa fa-trash-o\" aria-hidden=\"true\"></i></a>" +
	            "</span>";
				
		UserManager um = new UserManager();
		if (!authorID.equals(um.getUserBySessionID(um.getCookieValue("sessionID", request)).getId()))
		{
			deleteString = "";
		}
		
		String result = "<div class=\"post main-container\">" +
							"<div class=\"headline\">" +
								"<span class=\"author\">" +
									"<a href=\"?author=" + authorID +"\">" +
										authorName +
									"</a>"  +
								"</span> - " +
								"<span class=\"date\">" +
									 date +
								"</span>" +
								deleteString +
							"</div>" +
							"<div class=\"post-text\">" +
								content +
							"</div>" +
						"</div>";	
		return result;
	}

	private void deleteTweet(String tweetID) {		
		
		if (!userManager.getCurrentUser(request).getId().equals(Tweet.getAuthorById(tweetID)))
		{
			System.out.println(userManager.getCurrentUser(request).getFullName() + " attempted to delete a tweet(" + tweetID + ") from someone else.");
			request.setAttribute("message", "<div class=\"message error\">ERROR: Selected tweet isn't yours!</div>");
			return;
		}
		
		SQLConnector sqlConnector = new SQLConnector();
		sqlConnector.sendQuery("DELETE FROM tweets WHERE id = '" + tweetID + "';");
		sqlConnector.disconnectSQL();
		System.out.println("Tweet removed: " + tweetID);
	}
}
