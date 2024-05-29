package com.aayush.Web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aayush.DAO.StudentDAO;
import com.aayush.Model.Student;


@WebServlet("/")
public class StudentServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private StudentDAO studentDAO;
	
	public void init()
	{
		studentDAO = new StudentDAO();
	}
       
    
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		String action  = req.getServletPath();
		
		switch (action)
		{
			case "/new":
				showNewForm(req, res);
				break;
			
			case "/insert":
				insertNewStudent(req, res);
				break;
				
			case "/delete":
				deleteStudent(req, res);
				break;
				
			case "/edit":
				showEditForm(req, res);
				break;
				
			case "/update":
				updateStudent(req, res);
				break;
				
			default:
				listStudents(req, res);
				break;
				
		}
	}

	
	private void listStudents(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		List<Student> students = studentDAO.getAllStudents();
		req.setAttribute("studentList", students);
		RequestDispatcher rs = req.getRequestDispatcher("StudentList.jsp");
		rs.forward(req, res);
	}


	private void updateStudent(HttpServletRequest req, HttpServletResponse res) throws IOException 
	{
		int id = Integer.parseInt(req.getParameter("id"));
		String fullName = req.getParameter("fullName");
		String email = req.getParameter("email");
		String branch = req.getParameter("branch");
		Student student = new Student(id, fullName, email, branch);
		
		studentDAO.updateStudent(student);
		res.sendRedirect("list");
	}


	private void showEditForm(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		int id = Integer.parseInt(req.getParameter("id"));
		Student existingStudent = studentDAO.getStudentById(id);
		req.setAttribute("student", existingStudent);
		RequestDispatcher rs = req.getRequestDispatcher("NewStudentForm.jsp");
		rs.forward(req, res);		
	}


	private void deleteStudent(HttpServletRequest req, HttpServletResponse res) throws IOException 
	{
		int id = Integer.parseInt(req.getParameter("id"));
		studentDAO.deleteStudentById(id);
		res.sendRedirect("list");
	}


	private void insertNewStudent(HttpServletRequest req, HttpServletResponse res) throws IOException 
	{
		String fullName = req.getParameter("fullName");
		String email = req.getParameter("email");
		String branch = req.getParameter("branch");
		Student student = new Student(fullName, email, branch);
		studentDAO.insertStudent(student);
		res.sendRedirect("list");
	}


	private void showNewForm(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{		
		RequestDispatcher rs = req.getRequestDispatcher("NewStudentForm.jsp");
		rs.forward(req, res);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
