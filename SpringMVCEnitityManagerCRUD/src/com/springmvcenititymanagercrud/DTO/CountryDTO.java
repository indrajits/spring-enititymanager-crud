package com.springmvcenititymanagercrud.DTO;

public class CountryDTO {
	private int country_code;
	private String country_name;
	
	public CountryDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public CountryDTO(int country_code, String country_name) {
		this.country_code = country_code;
		this.country_name = country_name;
	}
	
	public int getCountry_code() {
		return country_code;
	}
	public String getCountry_name() {
		return country_name;
	}
	public void setCountry_code(int country_code) {
		this.country_code = country_code;
	}
	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}
}
