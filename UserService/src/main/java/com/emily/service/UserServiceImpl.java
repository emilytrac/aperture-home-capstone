package com.emily.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emily.entity.User;
import com.emily.persistence.UserDao;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	// checking to see that the user exists in the user database
	@Override
	public User loginCheck(String userEmail, String userPassword) {
		try {
			User user = userDao.findByUserEmailAndUserPassword(userEmail, userPassword);
			if (user != null)
				return user;
			return null;
		} catch (Exception e) {
			return null;
		}
	}

}
