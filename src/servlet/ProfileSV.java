package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import users.User;
import util.TweetManager;
import util.UserManager;

public class ProfileSV extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserManager um = new UserManager();
		TweetManager tm = new TweetManager(request);

		if (request.getParameter("delete") != null) {
			tm.requestDeleteTweet(tm.getTweetByID(request.getParameter("delete")), request);
		}
		
		if (request.getParameter("user") != null) {
			String userID = request.getParameter("user").toString();
			User user = um.getUserByID(userID);

			String friend = "block";
			if (um.getCurrentUser(request).getId().equals(userID))
			{
				friend = "none";
			}
			
			
			request.setAttribute("avatar", "./images/default.png");
			request.setAttribute("name", user.getFullName());
			request.setAttribute("email", user.getEmail());
			request.setAttribute("email", user.getEmail());
			request.setAttribute("friend", friend);
			request.setAttribute("firstname", user.getFirstName());

			request.setAttribute("tweets", tm.getTweetsByAuthor(user.getId(), 10));
		} else {
			if (um.getCurrentUser(request) != null) {
				response.sendRedirect("./profile?user=" + um.getCurrentUser(request).getId());
			} else {
				response.sendRedirect("./login");
			}
			return;
		}

		request.getRequestDispatcher("/profile.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
