package com.springmvcenititymanagercrud.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springmvcenititymanagercrud.DTO.CountryDTO;
import com.springmvcenititymanagercrud.entities.Country;

@Repository
public class CountryDaoImpl implements CountryDao {

	@PersistenceContext
	private EntityManager em;
	
	public CountryDaoImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<CountryDTO> getCountryList() {
		// TODO Auto-generated method stub
		StoredProcedureQuery query = this.em.createNamedStoredProcedureQuery("GET_COUNTRY_DETAILS");
		List<Country> countries = query.getResultList();
		
		List<CountryDTO> countryList = new ArrayList<CountryDTO>();
		for (Country country : countries) {
			countryList.add(new CountryDTO(country.getCountry_code(), country.getCountry_name()));
		}
		return countryList;
	}

}
