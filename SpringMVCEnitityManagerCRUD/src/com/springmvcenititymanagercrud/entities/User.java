package com.springmvcenititymanagercrud.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(name = "GET_USER_DETAILS", procedureName = "SP_GET_USER_DETAILS", resultClasses = User.class,
								parameters = {
									@StoredProcedureParameter(mode = ParameterMode.OUT, type = Integer.class, name = "count_users")
								}),
	@NamedStoredProcedureQuery(name = "GET_USER_DETAILS_BY_PAGE", procedureName = "SP_GET_USER_DETAILS_BY_PAGE", resultClasses = User.class,
								parameters = {
									@StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class, name = "start"),
									@StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class, name = "size"),
									@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "sortById"),
									@StoredProcedureParameter(mode = ParameterMode.OUT, type = Integer.class, name = "count_users")
								}),
	@NamedStoredProcedureQuery(name = "CREATE_NEW_USER", procedureName = "SP_CREATE_NEW_USER",
								parameters = {
									@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "fname"),
									@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "lname"),
									@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "email"),
									@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "conatact"),
									@StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class, name = "country"),
									@StoredProcedureParameter(mode = ParameterMode.IN, type = Date.class, name = "dob"),
									@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "passwd"),
									@StoredProcedureParameter(mode = ParameterMode.OUT, type = Integer.class, name = "user_sno")
								}),
	@NamedStoredProcedureQuery(name = "DELETE_USER", procedureName = "SP_DELETE_USER",
								parameters = {
									@StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class, name = "user_sno")
								})
})

@Entity
@Table(name = "users")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1909936324169205961L;
	private long user_sno;
	private String fname, lname, email, conatact, password;
	private Country countryModel;
	private Date dob;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(int user_sno, String fname, String lname, String email, String conatact, Country countryModel, String password, Date dob) {
		// TODO Auto-generated constructor stub
		this.user_sno = user_sno;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.conatact = conatact;
		this.countryModel = countryModel;
		this.password = password;
		this.dob = dob;
	}

	@Id
	@GeneratedValue
	@Column(name = "user_sno")
	public long getUser_sno() {
		return user_sno;
	}
	@Column(name = "fname")
	public String getFname() {
		return fname;
	}
	@Column(name = "lname")
	public String getLname() {
		return lname;
	}
	@Column(name = "email")
	public String getEmail() {
		return email;
	}
	@Column(name = "conatact")
	public String getConatact() {
		return conatact;
	}
	@Column(name = "password")
	public String getPassword() {
		return password;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "country")
	public Country getCountryModel() {
		return countryModel;
	}
	@Column(name = "dob")
	public Date getDob() {
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
	public void setCountryModel(Country countryModel) {
		this.countryModel = countryModel;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}

}
