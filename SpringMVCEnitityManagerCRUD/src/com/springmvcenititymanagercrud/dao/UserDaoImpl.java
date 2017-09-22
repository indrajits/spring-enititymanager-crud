package com.springmvcenititymanagercrud.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springmvcenititymanagercrud.DTO.CountryDTO;
import com.springmvcenititymanagercrud.DTO.UserDTO;
import com.springmvcenititymanagercrud.DTO.UserDetailsDTO;
import com.springmvcenititymanagercrud.entities.Country;
import com.springmvcenititymanagercrud.entities.User;

@Repository
public class UserDaoImpl implements UserDao {

	//private EntityManager em;
	private List<UserDTO> userList = null;
	private UserDetailsDTO userDetailsDTO = null;
	
	public UserDaoImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@PersistenceContext
	private EntityManager em;
	
	/*Session session = em.unwrap(Session.class);*/
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public UserDetailsDTO getUserList() {
		// TODO Auto-generated method stub
		StoredProcedureQuery query = this.em.createNamedStoredProcedureQuery("GET_USER_DETAILS");
		
		List<User> users = query.getResultList();
		
		userList = new ArrayList<UserDTO>();	
		
		for (User user : users) {
			UserDTO obj = new UserDTO();
			obj.setUser_sno(user.getUser_sno());
			obj.setFname(user.getFname());
			obj.setLname(user.getLname());
			obj.setEmail(user.getEmail());
			obj.setConatact(user.getConatact());
			
			CountryDTO objC = new CountryDTO();
			objC.setCountry_code(user.getCountryModel().getCountry_code());
			objC.setCountry_name(user.getCountryModel().getCountry_name());
			
			obj.setCountry(objC);
			obj.setDob(new SimpleDateFormat("yyyy-MM-dd").format(user.getDob()));
			obj.setPassword(user.getPassword());
			
			userList.add(obj);
		}	
		
		userDetailsDTO = new UserDetailsDTO((Integer) query.getOutputParameterValue("count_users"), userList);
		
		return userDetailsDTO;
	}

	@Override
	@Transactional
	public List<UserDTO> createUser(List<UserDTO> userList) throws ParseException {
		// TODO Auto-generated method stub
		StoredProcedureQuery query = this.em.createNamedStoredProcedureQuery("CREATE_NEW_USER");
		
		for (UserDTO userDTO : userList) {
			
			query.setParameter("fname", userDTO.getFname());
			query.setParameter("lname", userDTO.getLname());
			query.setParameter("email", userDTO.getEmail());
			query.setParameter("conatact", userDTO.getConatact());
			query.setParameter("country", userDTO.getCountry().getCountry_code());
			
			Date dob = new SimpleDateFormat("yyyy-MM-dd").parse(userDTO.getDob());
			query.setParameter("dob", new java.sql.Date(dob.getTime()));
			query.setParameter("passwd", userDTO.getPassword());
			
				userDTO.setUser_sno((Integer) query.getOutputParameterValue("user_sno"));
		}			
		return userList;		
	}

	@Override
	@Transactional
	public void updateUser(List<UserDTO> userList) throws ParseException {
		// TODO Auto-generated method stub
		em.setFlushMode(FlushModeType.COMMIT);
        int batchSize = 20;
        
        int count = 0;
		User user = null;
		Country country = null;
        
        for (UserDTO userDTO : userList) {
			country = new Country();
			country.setCountry_code(userDTO.getCountry().getCountry_code());
			country.setCountry_name(userDTO.getCountry().getCountry_name());
			
			user = new User();
			user.setUser_sno(userDTO.getUser_sno());
			user.setFname(userDTO.getFname());
			user.setLname(userDTO.getLname());
			user.setEmail(userDTO.getEmail());
			user.setCountryModel(country);
			user.setPassword(userDTO.getPassword());
			user.setDob(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(userDTO.getDob()).getTime()));
			
			System.out.println(userDTO.getDob());
			
			em.merge(user);
			count++;
			if (count % batchSize == 0) {
				em.flush();
				em.clear();
			}		
		}
        
        em.flush();
		em.clear();
	}

	@Override
	@Transactional	
	public List<Integer> deleteUser(List<Integer> user_sno) {
		// TODO Auto-generated method stub
		StoredProcedureQuery query = this.em.createNamedStoredProcedureQuery("DELETE_USER");
		List<Integer> deleted_user_sno = new ArrayList<Integer>();
		
		for (Integer integer : user_sno) {
			query.setParameter("user_sno", integer);	
			
			int res = query.executeUpdate();
			if(res > 0)
				deleted_user_sno.add(integer);
		}		
		return deleted_user_sno;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public UserDetailsDTO getUserList(int page_no, int page_size,
			String sortByIdOrder) {
		// TODO Auto-generated method stub		
		int start = (page_no-1) * page_size;
		
		StoredProcedureQuery query = this.em.createNamedStoredProcedureQuery("GET_USER_DETAILS_BY_PAGE");
		query.setParameter("start", start);
		query.setParameter("size", page_size);
		query.setParameter("sortById", sortByIdOrder);
		
		List<User> users = query.getResultList();
		
		userList = new ArrayList<UserDTO>();	
		
		for (User user : users) {
			UserDTO obj = new UserDTO();
			obj.setUser_sno(user.getUser_sno());
			obj.setFname(user.getFname());
			obj.setLname(user.getLname());
			obj.setEmail(user.getEmail());
			obj.setConatact(user.getConatact());
			
			CountryDTO objC = new CountryDTO();
			objC.setCountry_code(user.getCountryModel().getCountry_code());
			objC.setCountry_name(user.getCountryModel().getCountry_name());
			
			obj.setCountry(objC);
			obj.setDob(new SimpleDateFormat("yyyy-MM-dd").format(user.getDob()));
			obj.setPassword(user.getPassword());
			
			userList.add(obj);
		}	
		
		userDetailsDTO = new UserDetailsDTO((Integer) query.getOutputParameterValue("count_users"), userList);
		
		return userDetailsDTO;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public UserDetailsDTO filterUserListByEmail(int page_no, int page_size,
			String email, String sortByIdOrder) {
		// TODO Auto-generated method stub
		
		int start = (page_no-1) * page_size;
		
		/****************************** Using Query ******************************/
		/*String sql = "SELECT u" +
						" FROM User u" +
						" WHERE u.email LIKE :email" +
						" ORDER BY u.user_sno " + sortByIdOrder;
		
		Query query = this.em.createQuery(sql)
								.setParameter("email", email + "%")
								.setFirstResult(start)
								.setMaxResults(page_size);
		
		List<User> users = query.getResultList();
		
		userList = new ArrayList<UserDTO>();	
		
		for (User user : users) {
			UserDTO obj = new UserDTO();
			obj.setUser_sno(user.getUser_sno());
			obj.setFname(user.getFname());
			obj.setLname(user.getLname());
			obj.setEmail(user.getEmail());
			obj.setConatact(user.getConatact());
			
			CountryDTO objC = new CountryDTO();
			objC.setCountry_code(user.getCountryModel().getCountry_code());
			objC.setCountry_name(user.getCountryModel().getCountry_name());
			
			obj.setCountry(objC);
			obj.setDob(new SimpleDateFormat("yyyy-MM-dd").format(user.getDob()));
			obj.setPassword(user.getPassword());
			
			userList.add(obj);
		}	
		
		sql = "SELECT u" +
				" FROM User u" +
				" WHERE u.email LIKE :email";
		query = this.em.createQuery(sql).setParameter("email", email + "%");
		users = query.getResultList();
		
		userDetailsDTO = new UserDetailsDTO(users.size(), userList);*/
			
		/****************************** Using Criteria ******************************/
		CriteriaBuilder critBuilder = this.em.getCriteriaBuilder(); 
		CriteriaQuery<User> crit = critBuilder.createQuery(User.class); 
		
		Root<User> userRoot = crit.from(User.class);
		Expression<String> exp = userRoot.get("email");
		crit.where(critBuilder.like(exp, email + "%"));
		
		if (sortByIdOrder.equals("asc"))
			crit.orderBy(critBuilder.asc(userRoot.get("user_sno")));
		else
			crit.orderBy(critBuilder.desc(userRoot.get("user_sno")));		
		
		Query query = this.em.createQuery(crit)
						.setFirstResult(start)
						.setMaxResults(page_size);
		
		List<User> users = query.getResultList();
		
		userList = new ArrayList<UserDTO>();	
		
		for (User user : users) {
			UserDTO obj = new UserDTO();
			obj.setUser_sno(user.getUser_sno());
			obj.setFname(user.getFname());
			obj.setLname(user.getLname());
			obj.setEmail(user.getEmail());
			obj.setConatact(user.getConatact());
			
			CountryDTO objC = new CountryDTO();
			objC.setCountry_code(user.getCountryModel().getCountry_code());
			objC.setCountry_name(user.getCountryModel().getCountry_name());
			
			obj.setCountry(objC);
			obj.setDob(new SimpleDateFormat("yyyy-MM-dd").format(user.getDob()));
			obj.setPassword(user.getPassword());
			
			userList.add(obj);
		}
		
		CriteriaQuery<Long> cq = critBuilder.createQuery(Long.class);
		cq.select(critBuilder.count(cq.from(User.class)));
		cq.where(critBuilder.like(exp, email + "%"));
		
		Long count = this.em.createQuery(cq).getSingleResult();		
		userDetailsDTO = new UserDetailsDTO(count, userList);
		
		return userDetailsDTO;
	}
}
