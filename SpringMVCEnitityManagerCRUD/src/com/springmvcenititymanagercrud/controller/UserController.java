package com.springmvcenititymanagercrud.controller;

import java.text.ParseException;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springmvcenititymanagercrud.DTO.UserDTO;
import com.springmvcenititymanagercrud.DTO.UserDetailsDTO;
import com.springmvcenititymanagercrud.DTO.UserValidationDTO;
import com.springmvcenititymanagercrud.dao.UserDao;

@RestController
public class UserController {
	
	@Autowired
	private UserDao userDao;
	
	@GetMapping("/test")
	public ResponseEntity<String> redirectUrl(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.setAttribute("test", "check");
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "/SpringMVCEnitityManagerCRUD/login");
		return new ResponseEntity<String>(null, headers, HttpStatus.FOUND);
	}	
		
	@PostMapping("/validateUser")
	public ResponseEntity<String []> validateUser(@RequestBody UserValidationDTO userData, HttpServletRequest request, HttpServletResponse response) {
		String [] param = new String[2];
		String url = "/SpringMVCEnitityManagerCRUD/";
		if (userData.getUsername().equals("admin") && userData.getPassword().equals("admin123")) {
			
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(60*1);
			session.setAttribute("user", "admin");			
			param[0] = "success";
			
			Cookie[] cookies = request.getCookies();
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("spring-mvc-enitity-manager-crud-url")) {
					url = cookie.getValue();
					cookie.setValue(null);
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}
			param[1] = url;			
			return new ResponseEntity<String []>(param, HttpStatus.OK);
		} else {
			param[0] = "failed";
			param[1] = "/SpringMVCEnitityManagerCRUD/login";
			return new ResponseEntity<String []>(param, HttpStatus.OK);
		}
	}
	
	@GetMapping("/logout")
	public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		session.invalidate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "/SpringMVCEnitityManagerCRUD/login");
		return new ResponseEntity<String>(null, headers, HttpStatus.FOUND);
	}
	
	@GetMapping("/userList")
	public ResponseEntity<UserDetailsDTO> getUserList(HttpServletRequest request, HttpServletResponse response) {		
		UserDetailsDTO userDetails = null;
		HttpSession session = request.getSession();
		String user = (String) session.getAttribute("user");
		if (user != null && !(user.equals("")) && user.equals("admin")) {
			
				userDetails = this.userDao.getUserList();
				return new ResponseEntity<UserDetailsDTO>(userDetails, HttpStatus.OK);
		} else {
			String url = request.getRequestURL().toString();
			Cookie cookie = new Cookie("spring-mvc-enitity-manager-crud-url", url);
			cookie.setMaxAge(60*2); //2 mins
			response.addCookie(cookie);
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Location", "/SpringMVCEnitityManagerCRUD/login");	
			return new ResponseEntity<UserDetailsDTO>(userDetails, headers, HttpStatus.FOUND);
		}		
	}
	
	@PostMapping(value = "/get_users_by_page")
	public ResponseEntity<UserDetailsDTO> getUserListByPage(@RequestBody List<String> pageData, HttpServletRequest request) {
		UserDetailsDTO userDetails = null;
		
			userDetails = this.userDao.getUserList(Integer.parseInt(pageData.get(0)), Integer.parseInt(pageData.get(1)), pageData.get(2));
			return new ResponseEntity<UserDetailsDTO>(userDetails, HttpStatus.OK);
	}
	
	@PostMapping(value = "/filter_user_by_email")
	public ResponseEntity<UserDetailsDTO> filterUserByEmail(@RequestBody List<String> data, HttpServletRequest request) {
		UserDetailsDTO userDetails = null;
			userDetails = this.userDao.filterUserListByEmail(Integer.parseInt(data.get(0)), Integer.parseInt(data.get(1)), data.get(2), data.get(3));
			return new ResponseEntity<UserDetailsDTO>(userDetails, HttpStatus.OK);
	}
	
	@PostMapping(value = "/create_user")
	public ResponseEntity<List<UserDTO>> createUser(@RequestBody List<UserDTO> userList, HttpServletRequest request) {
		try {
			userList = this.userDao.createUser(userList);
			return new ResponseEntity<List<UserDTO>>(userList, HttpStatus.OK);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<List<UserDTO>>(userList, HttpStatus.CREATED);
		}
	}
	
	@PostMapping(value = "/update_user")
	public ResponseEntity<Boolean> updateUser(@RequestBody List<UserDTO> userList, HttpServletRequest request) {
		try {
			userDao.updateUser(userList);
			return new ResponseEntity<Boolean>(true, HttpStatus.ACCEPTED);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<Boolean>(false, HttpStatus.EXPECTATION_FAILED);
		}		
	}
	
	@PostMapping(value = "/delete_user")
	public ResponseEntity<List<Integer>> deleteUser(@RequestBody List<Integer> user_sno, HttpServletRequest request) {
		List<Integer> deleted_user_sno = null;
			deleted_user_sno = userDao.deleteUser(user_sno);
			return new ResponseEntity<List<Integer>>(deleted_user_sno, HttpStatus.OK);
	}
}
