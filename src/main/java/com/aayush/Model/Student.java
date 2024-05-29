package com.aayush.Model;

public class Student 
{
	private int id;
	private String fullName;
	private String email;
	private String branch;
	
	
	
	public Student(int id, String fullName, String email, String branch) 
	{
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.branch = branch;
	}
	
	
	public Student(String fullName, String email, String branch) 
	{
		this.fullName = fullName;
		this.email = email;
		this.branch = branch;
	}



	public Student() {
		super();
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}	
	
}
