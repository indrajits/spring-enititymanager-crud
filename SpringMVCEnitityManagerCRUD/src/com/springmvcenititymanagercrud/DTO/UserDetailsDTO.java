package com.springmvcenititymanagercrud.DTO;

import java.util.List;

public class UserDetailsDTO {
	
	private long user_counts;
	private List<UserDTO> userDTOList;
	
	public UserDetailsDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public UserDetailsDTO(long user_counts, List<UserDTO> userDTOList) {
		this.user_counts = user_counts;	
		this.userDTOList = userDTOList;
	}

	public long getUser_counts() {
		return user_counts;
	}

	public List<UserDTO> getUserDTOList() {
		return userDTOList;
	}

	public void setUser_counts(long user_counts) {
		this.user_counts = user_counts;
	}

	public void setUserDTOList(List<UserDTO> userDTOList) {
		this.userDTOList = userDTOList;
	}	
}
