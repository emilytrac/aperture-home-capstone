package com.emily.service;

import com.emily.entity.User;

public interface UserService {
	
	// method to check a user exists to allow login
	public User loginCheck(String userEmail, String userPassword);

}
