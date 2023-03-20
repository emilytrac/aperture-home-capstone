package com.emily.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.emily.entity.User;
import com.emily.service.UserService;

@RestController
public class UserResource {
	
	@Autowired
	private UserService userService;
	
	// GET method to check that the user exists
	@GetMapping(path = "/users/{userEmail}/{userPassword}", produces = MediaType.APPLICATION_JSON_VALUE)
	public User loginCheckResource(@PathVariable("userEmail") String userEmail, @PathVariable("userPassword") String userPassword) {
			return userService.loginCheck(userEmail, userPassword);
	}

}
