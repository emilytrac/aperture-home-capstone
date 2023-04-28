package com.emily.userservice.service;

import com.emily.userservice.entity.User;
import com.emily.userservice.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public User loginCheck(String userEmail, String userPassword) {
        try {
            /* checking to see that the user exists in the user database */
            // return user if it does exist
            return userDao.findByUserEmailAndUserPassword(userEmail, userPassword);
            // return null if no user exists
        } catch (Exception e) {
            // return null if an exception occurs
            return null;
        }
    }
}
