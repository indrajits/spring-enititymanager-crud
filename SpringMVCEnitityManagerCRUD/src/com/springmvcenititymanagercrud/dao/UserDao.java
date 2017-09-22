package com.springmvcenititymanagercrud.dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import com.springmvcenititymanagercrud.DTO.UserDTO;
import com.springmvcenititymanagercrud.DTO.UserDetailsDTO;

public interface UserDao {
	public UserDetailsDTO getUserList();
	public List<UserDTO> createUser(List<UserDTO> userList) throws ParseException;
	public void updateUser(List<UserDTO> userList) throws ParseException;
	public List<Integer> deleteUser(List<Integer> user_sno);
	public UserDetailsDTO getUserList(int page_no, int page_size, String sortByIdOrder);
	public UserDetailsDTO filterUserListByEmail(int page_no, int page_size, String email, String sortByIdOrder);
}
