package com.example.parserxmlexample.ennity;

public class Employee {

	private String id;
	private String name;
	private String department;
	private String type;
	private String email;

	public Employee() {
	}

	public Employee(String id, String name, String department, String type,
			String email) {
		super();
		this.id = id;
		this.name = name;
		this.department = department;
		this.type = type;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
