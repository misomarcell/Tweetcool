package servlet;

import java.io.IOException;
import java.security.SecureRandom;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Data;
import util.DataMultitool;
import util.UserManager;

public class LoginSV extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Check if user logged in
		Data data = Data.newInstance();
		if (data.isUserLoggedIn(request)) {
			response.sendRedirect("./tweets");
			return;
		}
		
		request.setCharacterEncoding("UTF-8");
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = "";
		String password = "";

		request.setCharacterEncoding("UTF-8");
		email = request.getParameter("login-email");
		password = request.getParameter("login-pass");
		password = DataMultitool.getMD5(password);

		UserManager userManager = new UserManager();

		if (userManager.checkUserLogin(email, password)) {
			request.setAttribute("message", "Login successfull");
			generateSessionCookie(response, email);
			
			response.sendRedirect("./tweets");		
			return;
		} else {
			request.setAttribute("message", "<div class=\"message error\">Incorrect email/password</div>");
		}

		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	public void generateSessionCookie(HttpServletResponse response, String email) {
		final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		SecureRandom rnd = new SecureRandom();

		StringBuilder sb = new StringBuilder(16);
		for (int i = 0; i < 16; i++) {
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		}

		Cookie cookie = new Cookie("sessionID", sb.toString());
		response.addCookie(cookie);

		Data d = Data.newInstance();
		d.addSession(sb.toString(), email);
	}
}
