package com.emily.userservice.service;

import com.emily.userservice.entity.User;

public interface UserService {
    // method to check a user exists to allow login
    User loginCheck(String userEmail, String userPassword);
}
