package com.qualitybazar.service;

import com.qualitybazar.exception.UserException;
import com.qualitybazar.model.User;

public interface UserService {
	
	public User findUserById(Long userId)throws UserException;
	
	public User findUserProfileByJwt(String jwt)throws UserException;
	

}
