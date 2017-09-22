package com.springmvcenititymanagercrud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springmvcenititymanagercrud.DTO.CountryDTO;
import com.springmvcenititymanagercrud.dao.CountryDao;

@RestController
public class CountryController {
	
	@Autowired
	private CountryDao countryDao;
	
	@PostMapping("/countryList")
	public ResponseEntity<List<CountryDTO>> getCountryList() {
		List<CountryDTO> countryList = null;
			
			countryList = countryDao.getCountryList();
			return new ResponseEntity<List<CountryDTO>>(countryList, HttpStatus.OK);
		
		
	}
}
