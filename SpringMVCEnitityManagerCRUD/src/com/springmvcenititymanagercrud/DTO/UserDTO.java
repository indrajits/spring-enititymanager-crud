package com.springmvcenititymanagercrud.DTO;

public class UserDTO {
	private long user_sno;
	private String fname, lname, email, conatact, password;
	private CountryDTO country;
	private String dob;
	
	public UserDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public UserDTO(int user_sno, String fname, String lname, String email, String conatact, String password, CountryDTO country, String dob) {
		// TODO Auto-generated constructor stub
		this.user_sno = user_sno;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.conatact = conatact;
		this.password = password;
		this.country = country;
		this.dob = dob;
	}
	
	public long getUser_sno() {
		return user_sno;
	}
	public String getFname() {
		return fname;
	}
	public String getLname() {
		return lname;
	}
	public String getEmail() {
		return email;
	}
	public String getConatact() {
		return conatact;
	}
	public String getPassword() {
		return password;
	}
	public CountryDTO getCountry() {
		return country;
	}
	public String getDob() {
		return dob;
	}
	public void setUser_sno(long user_sno) {
		this.user_sno = user_sno;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setConatact(String conatact) {
		this.conatact = conatact;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setCountry(CountryDTO country) {
		this.country = country;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
}
