package com.aayush.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDAO 
{
	private static final String url = "jdbc:mysql://localhost:3306/student_management_system";
    private static final String username = "root";
    private static final String password = "qwerty";
    
	private static final String REGISTER_NEW_USER = "INSERT INTO users (email, password) VALUES (?, ?)";
	private static final String FIND_REGISTERED_USER = "SELECT * FROM users WHERE email = ?";
	private static final String LOGIN_USER = "SELECT * FROM users WHERE email = ? AND password = ?";
	
	
	private Connection getConnection()
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
	
	public boolean registerNewUser(String email, String password)
	{
		try(Connection connection = getConnection();)
		{
			PreparedStatement preparedStatement = connection.prepareStatement(REGISTER_NEW_USER);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			int affectedRows = preparedStatement.executeUpdate();
			if(affectedRows > 0)
			{
				return true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean isUserRegistered(String email)
	{
		try(Connection connection = getConnection();)
		{
			PreparedStatement preparedStatement = connection.prepareStatement(FIND_REGISTERED_USER);
			preparedStatement.setString(1, email);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
			{
				return true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean loginUser(String email, String password)
	{
		try(Connection connection = getConnection();)
		{
			PreparedStatement preparedStatement = connection.prepareStatement(LOGIN_USER);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
			{
				return true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return false;
	}
	
	
}
