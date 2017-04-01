package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DataMultitool;
import util.UserManager;

/**
 * Servlet implementation class RegisterSV
 */
@WebServlet("/RegisterSV")
public class RegisterSV extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterSV() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserManager userManager = new UserManager();
		String email = "";
		String password = "";
		String password2 = "";
		String firstName = "";
		String lastName = "";		
		
		email = request.getParameter("email");
		password = request.getParameter("pass");
		password2 = request.getParameter("pass2");	
		firstName = request.getParameter("first-name");
		lastName = request.getParameter("last-name");
		
		//Check for empty fields
		if (email.equals("") || password.equals("") || password2.equals("") || firstName.equals("") || lastName.equals("")){
			request.setAttribute("message", "<div class=\"error\">One or more field is epty.</div>");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		
		//Check if given passwords are equals
		if (!password.equals(password2))
		{
			request.setAttribute("message", "<div class=\"error\">Password not match.</div>");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		//Hide password
		password = DataMultitool.getMD5(password);	
		password2 = "";
		
		try {
			if(userManager.userAlredadyExist(email))
			{
				request.setAttribute("message", "<div class=\"error\">This e-mail is already in use.</div>");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		userManager.addUser(email, password, firstName, lastName, request.getRemoteAddr());
		
		request.setAttribute("message", "<div class=\"success\">Register successfull</div>");	
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}
	

}
