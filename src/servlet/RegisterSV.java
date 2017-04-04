package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DataMultitool;
import util.UserManager;

public class RegisterSV extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserManager userManager = new UserManager();
		String email = "";
		String password = "";
		String password2 = "";
		String firstName = "";
		String lastName = "";		
		
		request.setCharacterEncoding("UTF-8");
		email = request.getParameter("email");
		password = request.getParameter("pass");
		password2 = request.getParameter("pass2");	
		firstName = request.getParameter("first-name");
		lastName = request.getParameter("last-name");
		
		//Check for empty fields
		if (email.equals("") || password.equals("") || password2.equals("") || firstName.equals("") || lastName.equals("")){
			request.setAttribute("message", "<div class=\"message error\">One or more field is epty.</div>");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		
		//Check if given passwords are equals
		if (!password.equals(password2))
		{
			request.setAttribute("message", "<div class=\"message error\">Password not match.</div>");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		//Hide password
		password = DataMultitool.getMD5(password);	
		password2 = null;
		
		//Check if user already exist
		try {
			if(userManager.userAlredadyExist(email))
			{
				request.setAttribute("message", "<div class=\"message error\">This e-mail is already in use.</div>");
				request.getRequestDispatcher("/index.jsp").forward(request, response);
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//Add user to the DB
		userManager.addUserToDB(email, password, firstName, lastName, request.getRemoteAddr());
		
		request.setAttribute("message", "<div class=\"message success\">Register successfull, now you can log in.</div>");	
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
	

}
