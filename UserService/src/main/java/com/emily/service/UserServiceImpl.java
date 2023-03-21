package com.emily.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emily.entity.User;
import com.emily.persistence.UserDao;

@Service
public class UserServiceImpl implements UserService {
	
	// creating an instance of the userDao to access JPA methods
	@Autowired
	private UserDao userDao;

	@Override
	public User loginCheck(String userEmail, String userPassword) {
		try {
			// checking to see that the user exists in the user database
			User user = userDao.findByUserEmailAndUserPassword(userEmail, userPassword);
			if (user != null)
				// return user if does exist
				return user;
			// return null if no user exists
			return null;
		} catch (Exception e) {
			// return null if an exception occurs
			return null;
		}
	}

}
