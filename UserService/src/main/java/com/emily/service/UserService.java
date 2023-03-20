package com.emily.service;

import com.emily.entity.User;

public interface UserService {
	
	User loginCheck(String userEmail, String userPassword);

}
