package com.aayush.Web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aayush.DAO.UserDAO;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;     
	
	private UserDAO userDAO;
	
	public void init()
	{
		userDAO = new UserDAO();
	}

	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		RequestDispatcher rs = null;
		
		if(email != null && password != null)
		{
			if(userDAO.isUserRegistered(email))
			{
				req.setAttribute("message", "Email already taken");
				rs = req.getRequestDispatcher("Register.jsp");
				rs.forward(req, res);
			}
			else if(userDAO.registerNewUser(email, password))
			{
				rs = req.getRequestDispatcher("Login.jsp");
				rs.forward(req, res);
			}
		}
		else
		{
			rs = req.getRequestDispatcher("Register.jsp");
			rs.forward(req, res);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
