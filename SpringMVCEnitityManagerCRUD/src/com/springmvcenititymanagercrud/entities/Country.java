package com.springmvcenititymanagercrud.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(name = "GET_COUNTRY_DETAILS", procedureName = "SP_GET_COUNTRY_DETAILS", resultClasses = Country.class)
})

@Entity
@Table(name = "country")
public class Country implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 47962539691519849L;
	private int country_code;
	private String country_name;
	private Set<User> userList;
	
	public Country() {
		// TODO Auto-generated constructor stub
	}
	
	public Country(int country_code, String country_name) {
		// TODO Auto-generated constructor stub
		this.country_code = country_code;
		this.country_name = country_name;
	}
	
	@Id
	@GeneratedValue
	@Column(name = "country_code")
	public int getCountry_code() {
		return country_code;
	}

	@Column(name = "country_name")
	public String getCountry_name() {
		return country_name;
	}

	@OneToMany(mappedBy = "countryModel", cascade = CascadeType.ALL)
	public Set<User> getUserList() {
		return userList;
	}	

	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}

	public void setUserList(Set<User> userList) {
		this.userList = userList;
	}
	
	public void setCountry_code(int country_code) {
		this.country_code = country_code;
	}

}
