package com.aayush.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aayush.Model.Student;

public class StudentDAO 
{
	
	private static final String url = "jdbc:mysql://localhost:3306/student_management_system";
    private static final String username = "root";
    private static final String password = "qwerty";
    
    private static final String INSERT_STUDENT = "INSERT INTO students" + "  (id, fullName, email, branch) VALUES "
			+ " (?, ?, ?, ?);";
    private static final String GET_LAST_STUDENT_ID = "SELECT id FROM students ORDER BY id DESC LIMIT 1";
	private static final String SELECT_STUDENT_BY_ID = "select id, fullName, email, branch from students where id =?";
	private static final String SELECT_ALL_STUDENTS = "select * from students";
	private static final String DELETE_STUDENTS_BY_ID = "delete from students where id = ?;";
	private static final String UPDATE_STUDENTS_BY_ID = "update students set fullName = ?, email= ?, branch =? where id = ?;";
	
	private Connection getConnection() throws ClassNotFoundException
	{
		Connection connection = null;
		
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		return connection;
	}
	
	public void insertStudent(Student student)
	{
		try(Connection connection = getConnection();)
		{
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT);
			preparedStatement.setInt(1, getLastStudentId()+1);
			preparedStatement.setString(2, student.getFullName());
			preparedStatement.setString(3, student.getEmail());
			preparedStatement.setString(4, student.getBranch());
			preparedStatement.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getLastStudentId()
	{
		try(Connection connection = getConnection();)
		{
			PreparedStatement preparedStatement = connection.prepareStatement(GET_LAST_STUDENT_ID);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
			{
				return resultSet.getInt("id");
			}
			else 
			{
				return 0;
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	@SuppressWarnings("null")
	public Student getStudentById(int id)
	{
		Student student = null;
		
		try(Connection connection = getConnection();)
		{
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_BY_ID);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
			{
				student = new Student();
				student.setFullName(resultSet.getString("fullName"));
				student.setBranch(resultSet.getString("branch"));
				student.setEmail(resultSet.getString("email"));
				student.setId(resultSet.getInt("id"));
			}
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return student;
	}
	
	public List<Student> getAllStudents()
	{
		List<Student> students = new ArrayList<>();
		
		try(Connection connection = getConnection();)
		{
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENTS);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				Student student = new Student();
				student.setFullName(resultSet.getString("fullName"));
				student.setBranch(resultSet.getString("branch"));
				student.setEmail(resultSet.getString("email"));
				student.setId(resultSet.getInt("id"));
				students.add(student);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return students;
	}
	
	public boolean deleteStudentById(int id)
	{
		boolean rowDeleted = false;
		try(Connection connection = getConnection())
		{
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STUDENTS_BY_ID);
			preparedStatement.setInt(1, id);
			rowDeleted = preparedStatement.executeUpdate() > 0;			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowDeleted;
	}
	
	public boolean updateStudent(Student student)
	{
		boolean rowUpdated = false;
		try(Connection connection = getConnection();)
		{
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STUDENTS_BY_ID);
			preparedStatement.setString(1, student.getFullName());
			preparedStatement.setString(2, student.getEmail());
			preparedStatement.setString(3, student.getBranch());
			preparedStatement.setInt(4, student.getId());
			rowUpdated = preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return rowUpdated;
	}
}
