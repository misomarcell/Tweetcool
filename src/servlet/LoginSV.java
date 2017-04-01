package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DataMultitool;
import util.UserManager;

/**
 * Servlet implementation class LoginSV
 */
@WebServlet("/LoginSV")
public class LoginSV extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginSV() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = "";
		String password = "";

		email = request.getParameter("email");
		password = request.getParameter("pass");
		password = DataMultitool.getMD5(password);

		UserManager userManager = new UserManager();
		if (userManager.checkUserLogin(email, password)) {
			request.setAttribute("message", "Success!");
		} else {
			request.setAttribute("message", "Incorrect email/password");
		}

		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

}
