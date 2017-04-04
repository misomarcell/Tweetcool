package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tweets.Tweet;
import users.User;
import util.Data;
import util.TweetManager;
import util.UserManager;

public class TweetSV extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UserManager userManager = new UserManager();
	TweetManager tweetManager;

	public TweetSV() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Data data = Data.newInstance();
		tweetManager = new TweetManager(request);
		
		// Check if user still logged in
		if (!data.isUserLoggedIn(request)) {
			response.sendRedirect("./login");
			return;
		}

		User user = userManager.getUserBySessionID(userManager.getCookieValue("sessionID", request));

		request.setAttribute("name", user.getFirstName());

		if (request.getParameter("delete") != null) {
			requestDeleteTweet(tweetManager.getTweetByID(request.getParameter("delete")), request);
		}

		if (request.getParameter("author") != null) {
			String authorID = request.getParameter("author").toString();

			request.setAttribute("tweets", tweetManager.getTweetsByAuthor(authorID, 10));
			request.setAttribute("message",
					"<div class=\"message info\">Showing tweets from: "
							+ userManager.getUserByID(authorID).getFullName()
							+ "<span style=\"float: right;\"><a href=\"./tweets\">Vissza</a></span></div>");
		} else {
			request.setAttribute("tweets", tweetManager.getTweets(10));
		}

		request.getRequestDispatcher("/tweets.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User user = userManager.getUserBySessionID(userManager.getCookieValue("sessionID", request));
		tweetManager = new TweetManager(request);
		
		request.setCharacterEncoding("UTF-8");
		if (!request.getParameter("tweet").equals("")) {
			String content = request.getParameter("tweet");
			Tweet tweet = new Tweet(user.getId(), clearContent(content));
			tweetManager.save(tweet);
		}

		request.setAttribute("name", user.getFirstName());
		request.setAttribute("tweets", tweetManager.getTweets(10));

		request.getRequestDispatcher("/tweets.jsp").forward(request, response);
	}

	public String clearContent(String content) {
		String result = content;
		result = result.replace("<", "&lt;");

		return result;
	}

	private void requestDeleteTweet(Tweet tweet, HttpServletRequest request) {
		if (!userManager.getCurrentUser(request).getId().equals(tweet.getAuthor())) {
			System.out.println(userManager.getCurrentUser(request).getFullName() + " attempted to delete a tweet("
					+ tweet.getId() + ") from someone else.");
			request.setAttribute("message", "<div class=\"message error\">ERROR: Selected tweet isn't yours!</div>");
			return;
		}
		tweetManager.deleteTweet(tweet);
	}
}
