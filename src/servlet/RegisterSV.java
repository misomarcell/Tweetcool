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
		String email = "";
		String password = "";
		String password2 = "";
		String firstName = "";
		String lastName = "";
		UserManager userManager = new UserManager();
		
		email = request.getParameter("email");
		password = request.getParameter("pass");
		password2 = request.getParameter("pass2");	
		firstName = request.getParameter("first-name");
		lastName = request.getParameter("last-name");
			
		if (!password.equals(password2))
		{
			request.setAttribute("message", "Password not match.");
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			return;
		}
		password = DataMultitool.getMD5(password);	
		password2 = "";
		
		try {
			if(userManager.userAlredadyExist(email))
			{
				request.setAttribute("message", "This e-mail is already in use.");
				request.getRequestDispatcher("/register.jsp").forward(request, response);
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		userManager.addUser(email, password, firstName, lastName, request.getRemoteAddr());
	
		request.getRequestDispatcher("/register.jsp").forward(request, response);
	}
	

}
