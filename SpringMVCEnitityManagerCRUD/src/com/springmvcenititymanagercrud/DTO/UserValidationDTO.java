package com.springmvcenititymanagercrud.DTO;

public class UserValidationDTO {
	private String username, password;
	
	public UserValidationDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public UserValidationDTO(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
