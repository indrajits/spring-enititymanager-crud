package com.springmvcenititymanagercrud.dao;

import java.util.List;

import com.springmvcenititymanagercrud.DTO.CountryDTO;

public interface CountryDao {
	public List<CountryDTO> getCountryList();
}
