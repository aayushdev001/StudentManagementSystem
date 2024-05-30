package com.aayush.Web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aayush.DAO.UserDAO;

@WebServlet("/login")
public class LoginServlet extends HttpServlet 
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
		
		if(email != null && password != null && isRegistered(email))
		{
			if(checkLogin(email, password))
			{
				res.sendRedirect(req.getContextPath() + "/v1");
			}
			else
			{
				req.setAttribute("message", "You entered wrong password");
				rs = req.getRequestDispatcher("Login.jsp");
				rs.forward(req, res);
			}
		}
		else if(email != null && password != null)
		{
			req.setAttribute("message", "You are not registered. Please register before log in");
			rs = req.getRequestDispatcher("Login.jsp");
			rs.forward(req, res);
		}
		else
		{
			rs = req.getRequestDispatcher("Login.jsp");
			rs.forward(req, res);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		doGet(request, response);
	}
	
	private boolean isRegistered(String email)
	{
		return userDAO.isUserRegistered(email);
	}
	private boolean checkLogin(String email, String password)
	{
		return userDAO.loginUser(email, password);
	}

}
